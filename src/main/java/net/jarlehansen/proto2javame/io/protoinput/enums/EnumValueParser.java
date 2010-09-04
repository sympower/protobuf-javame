package net.jarlehansen.proto2javame.io.protoinput.enums;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.protoinput.AbstractProtoParser;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 1, 2010
 * Time: 10:07:34 PM
 */
public class EnumValueParser extends AbstractProtoParser {

    public EnumValueParser() {
    }

    /**
     * Valid input:
     * 1. BLUE = 1;
     */
    public void parseAndAddProtoFile(ProtoFileInput protoInput) {
        String idString = strings[2];

        // remove ;
        idString = idString.substring(0, idString.length() - 1);
        if(idString.matches("[0-9]++")) {
            // Can use current enum since we are sure the enum values directly follows the enum declaration
            protoInput.setCurrentEnumValue(new Integer(idString), strings[0]);
        } else {
            throw new ProtoFileValidationException("The enum id is not a valid number, content: " + line);
        }
    }
}
