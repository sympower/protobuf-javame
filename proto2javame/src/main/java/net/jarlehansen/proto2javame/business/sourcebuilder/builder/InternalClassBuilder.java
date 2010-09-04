package net.jarlehansen.proto2javame.business.sourcebuilder.builder;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface InternalClassBuilder {
	public StringBuilder createInternalClass(final String className, final ProtoFileInput protoInput);
}
