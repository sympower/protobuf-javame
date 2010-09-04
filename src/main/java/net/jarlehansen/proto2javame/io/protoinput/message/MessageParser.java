package net.jarlehansen.proto2javame.io.protoinput.message;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.protoinput.AbstractProtoParser;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class MessageParser extends AbstractProtoParser {

	public MessageParser() {
	}

    /**
     * Valid input:
     * message Test {
     *
     */
    public void parseAndAddProtoFile(final ProtoFileInput protoInput) {
        // proto-message name token
		protoInput.setProtoClassName(strings[1]);
    }
}
