package net.jarlehansen.proto2javame.io.protoinput.message;

import java.util.StringTokenizer;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface MessageParser {
	public String parseMessageStart(final StringTokenizer stringTokens);
	public String parseMessageEnd(final StringTokenizer stringTokens);
}
