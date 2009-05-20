package net.jarlehansen.proto2javame.io.protoinput.message;

import java.util.StringTokenizer;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class MessageParserImpl implements MessageParser {
	
	private MessageParserImpl() {
	}
	
	public static MessageParserImpl newInstance() {
		return new MessageParserImpl();
	}
	
	public String parseMessageStart(final StringTokenizer stringTokens) {
		// message token
		stringTokens.nextToken();
		
		// proto-message name token
		return stringTokens.nextToken();
	}
	
	public String parseMessageEnd(final StringTokenizer stringTokens) {
		return "NOT SUPPORTED";
	}
}
