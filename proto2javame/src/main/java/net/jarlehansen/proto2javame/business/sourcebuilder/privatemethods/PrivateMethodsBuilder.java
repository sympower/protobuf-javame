package net.jarlehansen.proto2javame.business.sourcebuilder.privatemethods;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface PrivateMethodsBuilder {
	public StringBuilder createPrivateMethods(final String className, final ProtoFileInput protoInput);
}
