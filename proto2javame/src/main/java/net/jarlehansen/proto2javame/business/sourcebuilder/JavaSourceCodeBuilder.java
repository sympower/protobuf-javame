package net.jarlehansen.proto2javame.business.sourcebuilder;

import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface JavaSourceCodeBuilder {
	public JavaFileOutput createJavaSourceCode(final ProtoFileInput protoInput) ;
}
