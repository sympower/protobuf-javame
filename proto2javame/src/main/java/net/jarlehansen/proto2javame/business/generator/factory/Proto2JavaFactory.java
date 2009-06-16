package net.jarlehansen.proto2javame.business.generator.factory;

import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilderImpl;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilder;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilderImpl;
import net.jarlehansen.proto2javame.io.sourceoutput.SourceFileWriter;
import net.jarlehansen.proto2javame.io.sourceoutput.SourceFileWriterImpl;

public enum Proto2JavaFactory {
	;
	
	public static ProtoObjectBuilder createProtoObjectBuilder() {
        return ProtoObjectBuilderImpl.newInstance();
	}
	
	public static JavaSourceCodeBuilder createJavaSourceCodeBuilder() {
		return JavaSourceCodeBuilderImpl.newInstance();
	}
	
	public static SourceFileWriter createSourceFileWriter() {
		return SourceFileWriterImpl.newInstance();
	}
}
