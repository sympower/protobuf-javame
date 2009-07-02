package net.jarlehansen.proto2javame.io.protoinput.fields;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import net.jarlehansen.proto2javame.domain.proto.FieldData;
import net.jarlehansen.proto2javame.domain.proto.ListImplementation;
import net.jarlehansen.proto2javame.domain.proto.ValidScopes;
import net.jarlehansen.proto2javame.domain.proto.ValidTypes;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.validator.ProtoFileValues;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class FieldParserImpl implements FieldParser {
	final private Set<Integer> idList = new HashSet<Integer>();
	
	private FieldParserImpl() {
	}

	public static FieldParserImpl newInstance() {
		return new FieldParserImpl();
	}

	public FieldData parseField(final StringTokenizer stringTokens) {
		final FieldData fieldData = new FieldData();
		fieldData.setScope(getScopeTag(stringTokens.nextToken().trim()));

		if (fieldData.getScope() == ValidScopes.REPEATED) {
			fieldData.setListImpl(ListImplementation.VECTOR);
		}

		fieldData.setType(getTypeTag(stringTokens.nextToken()));
		fieldData.setName(stringTokens.nextToken());
		fieldData.setId(getIdTag(stringTokens));

		return fieldData;
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
			throw new ProtoFileValidationException("The tag required or optional are mandatory for all variables");
		}

		return scope;
	}

	ValidTypes getTypeTag(final String token) {
		ValidTypes type = null;

		for (ValidTypes typeObj : ValidTypes.values()) {
			if (typeObj.getProtoType().equals(token)) {
				type = typeObj;
				break;
			}
		}

		if (type == null) {
			throw new ProtoFileValidationException("Invalid type added for variable, value " + token);
		}

		return type;
	}

	int getIdTag(final StringTokenizer stringTokens) {
		// equals token
		stringTokens.nextToken();

		final String idString = stringTokens.nextToken().trim();
		final String tempId;

		if (idString.endsWith(ProtoFileValues.END_FIELD_TAG.getTag())) {
			tempId = idString.substring(0, idString.length() - 1);
		} else {
			final String nextToken = stringTokens.nextToken();

			if (nextToken.equals(ProtoFileValues.END_FIELD_TAG.getTag())) {
				tempId = idString;
			} else {
				throw new ProtoFileValidationException("Each variabel must end with ;");
			}
		}

		final int id = Integer.parseInt(tempId);
		validateId(id);
		
		
		return id;
	}
	
	private void validateId(final int id) {
		if(idList.contains(id)) {
			throw new ProtoFileValidationException("The id numbers must be unique, duplicate id: " + id);
		}
		
		idList.add(id);
	}

}
