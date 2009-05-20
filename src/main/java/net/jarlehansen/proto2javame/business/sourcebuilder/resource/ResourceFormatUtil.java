package net.jarlehansen.proto2javame.business.sourcebuilder.resource;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ResourceFormatUtil {
	COMMON("common/common"),
	BUILDER("builder"),
	MAIN("main"),
	PRIVATE_METHODS("privatemethods"),
	PUBLIC_METHODS("publicmethods"),
	STATIC_METHODS("staticmethods");
	
	// Locale used for proto-file
	private static final String DEFAULT_RESOURCE = "javame";
	// Path to resource file
	private static final String RESOURCE_LOCATION = "source_structure/";
	
	private final ResourceBundle resource;
	
	
	private ResourceFormatUtil(final String baseName) {
		final Locale locale = new Locale(DEFAULT_RESOURCE);
		resource = ResourceBundle.getBundle(RESOURCE_LOCATION + baseName, locale);
	}

	public String getString(final String key) {
		return resource.getString(key);
	}

	public String getString(final String key, final String... input) {
		final MessageFormat messageFormat = new MessageFormat(resource.getString(key));
		
		final StringBuffer buffer = new StringBuffer();
		messageFormat.format(input, buffer, null);
		
		return buffer.toString();
	}
}
