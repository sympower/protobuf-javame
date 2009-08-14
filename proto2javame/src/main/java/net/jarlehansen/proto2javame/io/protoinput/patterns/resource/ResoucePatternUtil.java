package net.jarlehansen.proto2javame.io.protoinput.patterns.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ResoucePatternUtil {
	FIELD_PARSER("field"),
	MESSAGE_PARSER("message"),
	OPTION_PARSER("option");
	
	// Locale used for proto-file
	private static final String DEFAULT_RESOURCE = "proto";
	// Path to resource file
	private static final String RESOURCE_LOCATION = "proto_patterns/";
	
	private final ResourceBundle resource;
	
	
	private ResoucePatternUtil(final String baseName) {
		final Locale locale = new Locale(DEFAULT_RESOURCE);
		resource = ResourceBundle.getBundle(RESOURCE_LOCATION + baseName, locale);
	}

	public String getString(final String key) {
		return resource.getString(key);
	}
}
