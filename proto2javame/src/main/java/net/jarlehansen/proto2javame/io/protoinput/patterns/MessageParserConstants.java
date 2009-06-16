package net.jarlehansen.proto2javame.io.protoinput.patterns;

import java.util.regex.Pattern;

import net.jarlehansen.proto2javame.io.protoinput.patterns.resource.ResoucePatternUtil;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum MessageParserConstants {
	;
	private static final String PRIVATE_PATTERN_MESSAGE_START = ResoucePatternUtil.MESSAGE_PARSER.getString("message.start");
	private static final String PRIVATE_PATTERN_MESSAGE_END = ResoucePatternUtil.MESSAGE_PARSER.getString("message.end");
	
	public static final Pattern PATTERN_MESSAGE_START = Pattern.compile(PRIVATE_PATTERN_MESSAGE_START);
	public static final Pattern PATTERN_MESSAGE_END = Pattern.compile(PRIVATE_PATTERN_MESSAGE_END);
}
