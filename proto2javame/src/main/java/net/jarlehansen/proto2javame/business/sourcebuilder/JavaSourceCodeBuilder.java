package net.jarlehansen.proto2javame.business.sourcebuilder;

import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

import java.util.List;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface JavaSourceCodeBuilder {
	public List<JavaFileOutput> createJavaSourceCode(final List<ProtoFileInput> protoInputList);
}
