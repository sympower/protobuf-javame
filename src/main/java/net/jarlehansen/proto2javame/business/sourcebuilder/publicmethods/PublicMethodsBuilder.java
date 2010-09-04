package net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface PublicMethodsBuilder {
	public StringBuilder createPublicMethods(final String className, final ProtoFileInput protoInput);
}
