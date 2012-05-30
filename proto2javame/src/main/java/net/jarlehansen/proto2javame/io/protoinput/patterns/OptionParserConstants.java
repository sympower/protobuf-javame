package net.jarlehansen.proto2javame.io.protoinput.patterns;

import java.util.regex.Pattern;

import net.jarlehansen.proto2javame.io.protoinput.patterns.resource.ResoucePatternUtil;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum OptionParserConstants {
	;
	private static final String PRIVATE_PATTERN_PACKAGE = ResoucePatternUtil.OPTION_PARSER.getString("option.package");
	private static final String PRIVATE_PATTERN_JAVA_PACKAGE = ResoucePatternUtil.OPTION_PARSER.getString("option.java.package");
	private static final String PRIVATE_JAVA_OUTER_CLASSNAME = ResoucePatternUtil.OPTION_PARSER.getString("option.java.outer.classname");
	
	public static final Pattern PATTERN_PACKAGE = Pattern.compile(PRIVATE_PATTERN_PACKAGE);
	public static final Pattern PATTERN_JAVA_PACKAGE = Pattern.compile(PRIVATE_PATTERN_JAVA_PACKAGE);
	public static final Pattern PATTERN_JAVA_OUTER_CLASSNAME = Pattern.compile(PRIVATE_JAVA_OUTER_CLASSNAME);
}
