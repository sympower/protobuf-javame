package net.jarlehansen.proto2javame.business.generator;

import net.jarlehansen.proto2javame.business.generator.factory.MetaDataFactory;
import net.jarlehansen.proto2javame.business.generator.factory.Proto2JavaFactory;
import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilder;
import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilder;
import net.jarlehansen.proto2javame.io.sourceoutput.SourceFileWriter;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class CodeGeneratorImpl implements CodeGenerator {
	private CodeGeneratorImpl() {
	}
	
	public static CodeGenerator newInstance() {
		return new CodeGeneratorImpl();
	}
	
	public String generateJavaSourceCode(final String... inputValues) {
		// Create metaData object based on input arguments
		final InputMetaData metaData = MetaDataFactory.createInputMetaDataVo(inputValues);
		
		// Validate and parse the .proto-file and save the values in a ProtoFileInput object
		final ProtoObjectBuilder protoReader = Proto2JavaFactory.createProtoObjectBuilder();
		final ProtoFileInput protoInput = protoReader.validateAndSaveProtoData(metaData.getProtoLocation());
		
		// Create Java source code based on the ProtoFileInput object
		final JavaSourceCodeBuilder sourceBuilder = Proto2JavaFactory.createJavaSourceCodeBuilder();
		final JavaFileOutput javaOutput = sourceBuilder.createJavaSourceCode(protoInput);
		
		// Write .java-file to destination location
		final SourceFileWriter sourceWriter = Proto2JavaFactory.createSourceFileWriter();
		sourceWriter.writeJavaSourceFile(metaData, javaOutput);

        return javaOutput.getClassName();
	}
}
