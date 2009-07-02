package net.jarlehansen.proto2javame.io.protoinput;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface ProtoObjectBuilder {
	public ProtoFileInput validateAndSaveProtoData(final String protoLocation);
}
