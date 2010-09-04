package net.jarlehansen.proto2javame.business.sourcebuilder.main;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface MainClassBuilder {
	public StringBuilder createMainClass(final String className, final ProtoFileInput protoInput);
}
