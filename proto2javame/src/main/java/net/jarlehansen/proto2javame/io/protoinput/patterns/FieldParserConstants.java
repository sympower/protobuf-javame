package net.jarlehansen.proto2javame.io.protoinput.patterns;

import java.util.regex.Pattern;

import net.jarlehansen.proto2javame.io.protoinput.patterns.resource.ResoucePatternUtil;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum FieldParserConstants {
	;
	
	private final static String PRIVATE_PATTERN_FIELD = ResoucePatternUtil.FIELD_PARSER.getString("field.pattern");
	
	public static final Pattern PATTERN_FIELD = Pattern.compile(PRIVATE_PATTERN_FIELD);
}
