package net.jarlehansen.proto2javame.testutils;

import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilderImpl;
import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.domain.proto.FieldData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.domain.proto.ValidScopes;
import net.jarlehansen.proto2javame.domain.proto.ValidTypes;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilderImpl;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public enum TestObjectFactory {
	;

	public static InputMetaData createInputMetaData() {
		return new InputMetaData(TestConstants.GENERATED_FOLDER, TestConstants.PROTO_FILE);
	}

	public static JavaFileOutput createJavaFileOutput() {
		return JavaSourceCodeBuilderImpl.newInstance().createJavaSourceCode(
				ProtoObjectBuilderImpl.newInstance().validateAndSaveProtoData(TestConstants.PROTO_FILE));
	}

	public static ProtoFileInput createTestProtoFileInput() {
		final FieldData field = new FieldData();
		field.setId(1);
		field.setName("test");
		field.setScope(ValidScopes.REQUIRED);
		field.setType(ValidTypes.STRING);

		final ProtoFileInput protoInput = new ProtoFileInput();
		protoInput.setProtoClassName(TestConstants.PROTO_CLASS_NAME);
		protoInput.addFieldData(field);

		return protoInput;
	}
}
