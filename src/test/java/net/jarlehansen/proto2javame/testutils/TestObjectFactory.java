package net.jarlehansen.proto2javame.testutils;

import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.builder.InternalClassBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.enums.EnumsBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.instancemethods.InstanceMethodsBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.main.MainClassBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods.PublicMethodsBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods.StaticMethodsBuilderImpl;
import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.domain.proto.FieldData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.domain.proto.ValidScopes;
import net.jarlehansen.proto2javame.domain.proto.ValidTypes;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilderImpl;
import net.jarlehansen.proto2javame.io.protoinput.enums.EnumParser;
import net.jarlehansen.proto2javame.io.protoinput.enums.EnumValueParser;
import net.jarlehansen.proto2javame.io.protoinput.factory.ProtoParserFactoryImpl;
import net.jarlehansen.proto2javame.io.protoinput.fields.FieldParser;
import net.jarlehansen.proto2javame.io.protoinput.message.MessageParser;
import net.jarlehansen.proto2javame.io.protoinput.options.OptionParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public enum TestObjectFactory {
    ;

    public static InputMetaData createInputMetaData() {
        return new InputMetaData(TestConstants.GENERATED_FOLDER, TestConstants.PROTO_FILE);
    }

    public static JavaFileOutput createJavaFileOutput() {
        return createJavaSourceCodeBuilder().createJavaSourceCode(createProtoObjectBuilder().validateAndSaveProtoData(TestConstants.PROTO_FILE)).get(0);
    }

    public static JavaSourceCodeBuilderImpl createJavaSourceCodeBuilder() {
        return new JavaSourceCodeBuilderImpl(new InternalClassBuilderImpl(), new MainClassBuilderImpl(), new EnumsBuilderImpl(), new InstanceMethodsBuilderImpl(),
                new PublicMethodsBuilderImpl(), new StaticMethodsBuilderImpl());
    }

    public static ProtoObjectBuilderImpl createProtoObjectBuilder() {
        return new ProtoObjectBuilderImpl(createProtoParserFactory());
    }

    public static ProtoParserFactoryImpl createProtoParserFactory() {
        return new ProtoParserFactoryImpl(new EnumParser(), new EnumValueParser(), new FieldParser(), new MessageParser(),
                new OptionParser());
    }

    public static List<ProtoFileInput> createTestProtoFileInputList() {
        final List<ProtoFileInput> protoFileList = new ArrayList<ProtoFileInput>();
        protoFileList.add(createTestProtoFileInput());
        return protoFileList;
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
