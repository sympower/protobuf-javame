package net.jarlehansen.proto2javame.io.protoinput;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

import java.util.List;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface ProtoObjectBuilder {
	public List<ProtoFileInput> validateAndSaveProtoData(final String protoLocation);
}
