package net.jarlehansen.proto2javame.io.protoinput.util;

import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ProtoTagUtil {
	;
	
	private static final String PROTO_FILE = ".proto";
	
	public static void isValidProtoFile(final String protoLocation) {
		if(!protoLocation.endsWith(PROTO_FILE)) {
			throw new ProtoFileValidationException("The input file must end with .proto");
		}
	}
	
}
