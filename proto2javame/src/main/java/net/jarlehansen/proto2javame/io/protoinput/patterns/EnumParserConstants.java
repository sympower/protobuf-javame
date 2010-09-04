package net.jarlehansen.proto2javame.io.protoinput.patterns;

import net.jarlehansen.proto2javame.io.protoinput.patterns.resource.ResoucePatternUtil;

import java.util.regex.Pattern;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 1, 2010
 * Time: 9:52:59 PM
 */
public enum EnumParserConstants {
    ;

	private final static String PRIVATE_PATTERN_ENUM_START = ResoucePatternUtil.ENUM_PARSER.getString("enum.start");
    private final static String PRIVATE_PATTERN_ENUM_VALUE = ResoucePatternUtil.ENUM_PARSER.getString("enum.field");
    private final static String PRIVATE_PATTERN_ENUM_END = ResoucePatternUtil.ENUM_PARSER.getString("enum.end");

	public static final Pattern PATTERN_ENUM_START = Pattern.compile(PRIVATE_PATTERN_ENUM_START);
    public static final Pattern PATTERN_ENUM_VALUE = Pattern.compile(PRIVATE_PATTERN_ENUM_VALUE);
    public static final Pattern PATTERN_ENUM_END = Pattern.compile(PRIVATE_PATTERN_ENUM_END);
}
