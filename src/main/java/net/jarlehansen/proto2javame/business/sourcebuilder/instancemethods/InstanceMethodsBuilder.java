package net.jarlehansen.proto2javame.business.sourcebuilder.instancemethods;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface InstanceMethodsBuilder {
	public StringBuilder createPrivateAndProtectedMethods(final String className, final ProtoFileInput protoInput);
}
