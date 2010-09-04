package net.jarlehansen.proto2javame.io.protoinput.fields;

import net.jarlehansen.proto2javame.domain.proto.*;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.protoinput.AbstractProtoParser;
import net.jarlehansen.proto2javame.io.validator.ProtoFileValues;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class FieldParser extends AbstractProtoParser {

    // Package protected for testing purposes
    ProtoFileInput protoInput;

    public FieldParser() {
        protoInput = new ProtoFileInput();
        protoInput.setProtoClassName("");
    }

    /**
     * Valid input:
     * repeated/optional/required string text = 1;
     */
    public void parseAndAddProtoFile(final ProtoFileInput protoInput) {
        this.protoInput = protoInput;

        final FieldData fieldData = new FieldData();
        fieldData.setScope(getScopeTag(strings[0]));

        if (fieldData.getScope() == ValidScopes.REPEATED) {
            fieldData.setListImpl(ListImplementation.VECTOR);
        }

        fieldData.setType(getTypeTag(strings[1]));
        fieldData.setName(strings[2]);
        fieldData.setId(getIdTag(strings[4]));

        protoInput.addFieldData(fieldData);
    }

    ValidScopes getScopeTag(final String token) {
        final ValidScopes scope;

        if (ProtoFileValues.REQUIRED_TAG.getTag().equals(token)) {
            scope = ValidScopes.REQUIRED;
        } else if (ProtoFileValues.OPTIONAL_TAG.getTag().equals(token)) {
            scope = ValidScopes.OPTIONAL;
        } else if (ProtoFileValues.REPEATED_TAG.getTag().equals(token)) {
            scope = ValidScopes.REPEATED;
        } else {
            throw new ProtoFileValidationException("The tag required, optional or repeated are mandatory for all variables, content: " + line);
        }

        return scope;
    }

    DataType getTypeTag(final String token) {
        DataType type = null;

        for (ValidTypes typeObj : ValidTypes.values()) {
            if (typeObj.getProtoType().equals(token)) {
                type = typeObj;
                break;
            }
        }

        // If its not a previously declared type and a current enum is active it must be an enum field
        if (type == null) {
            if(protoInput.getEnumData(token) != null) {
                type = ValidTypes.ENUM_VALUE;
            } else {
                type = new CustomType(token);
            }
        }

        return type;
    }

    int getIdTag(final String idString) {
        // remove ;
        final String tempId = idString.substring(0, idString.length() - 1);

        final int id;
        if (tempId.matches("[0-9]++")) {
            id = Integer.parseInt(tempId);
        } else {
            throw new ProtoFileValidationException("The field id is not a valid number, id: " + idString + ", content: " + line);
        }

        return id;
    }
}
