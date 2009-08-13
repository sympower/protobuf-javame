package net.jarlehansen.proto2javame.business.sourcebuilder.privateandprotectedmethods;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface PrivateAndProtectedMethodsBuilder {
	public StringBuilder createPrivateAndProtectedMethods(final String className, final ProtoFileInput protoInput);
}
