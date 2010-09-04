package net.jarlehansen.proto2javame.business.generator;

import com.google.inject.Inject;
import net.jarlehansen.proto2javame.business.generator.factory.MetaDataFactory;
import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilder;
import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilder;
import net.jarlehansen.proto2javame.io.sourceoutput.SourceFileWriter;

import java.util.List;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class CodeGeneratorImpl implements CodeGenerator {
    private final ProtoObjectBuilder protoObjectBuilder;
    private final JavaSourceCodeBuilder javaSourceCodeBuilder;
    private final SourceFileWriter sourceFileWriter;

    @Inject
	public CodeGeneratorImpl(final ProtoObjectBuilder protoObjectBuilder, final JavaSourceCodeBuilder javaSourceCodeBuilder,
                          final SourceFileWriter sourceFileWriter) {
        this.protoObjectBuilder = protoObjectBuilder;
        this.javaSourceCodeBuilder = javaSourceCodeBuilder;
        this.sourceFileWriter = sourceFileWriter;
	}
		
	public List<JavaFileOutput> generateJavaSourceCode(final String... inputValues) {
		// Create metaData object based on input arguments
		final InputMetaData metaData = MetaDataFactory.createInputMetaDataVo(inputValues);

		// Validate and parse the .proto-file and save the values in a ProtoFileInput object
		final List<ProtoFileInput> protoInputList = protoObjectBuilder.validateAndSaveProtoData(metaData.getProtoLocation());

		// Create Java source code based on the ProtoFileInput object
		final List<JavaFileOutput> javaOutputList = javaSourceCodeBuilder.createJavaSourceCode(protoInputList);

		// Write .java-file to destination location
		sourceFileWriter.writeJavaSourceFile(metaData, javaOutputList);

        return javaOutputList;
	}
}
