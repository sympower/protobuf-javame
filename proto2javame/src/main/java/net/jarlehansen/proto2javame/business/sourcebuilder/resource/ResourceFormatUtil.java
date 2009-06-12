package net.jarlehansen.proto2javame.business.sourcebuilder.resource;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author hansjar@gmail.com Jarle Hansen
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
    // Default line separator, this value will only be used in the String generation if the line.separator is null
    private static final String DEFAULT_LINE_SEPARATOR = "\n";


    private final ResourceBundle resource;
    // This should be private and final, but must be modifiable to be able to test it.
    String lineSeparator;

    private ResourceFormatUtil(final String baseName) {
        final Locale locale = new Locale(DEFAULT_RESOURCE);
        resource = ResourceBundle.getBundle(RESOURCE_LOCATION + baseName, locale);

        final String tmpLineSeparator = System.getProperty("line.separator");

        if (tmpLineSeparator == null) {
            System.err.println("The system property line.separator is null, using \\n");
            lineSeparator = DEFAULT_LINE_SEPARATOR;
        } else {
            lineSeparator = tmpLineSeparator;
        }
    }

    public String getString(final String key) {
        return replaceLineSeparators(resource.getString(key));
    }

    public String getString(final String key, final String... input) {
        final MessageFormat messageFormat = new MessageFormat(resource.getString(key));

        final StringBuffer buffer = new StringBuffer();
        messageFormat.format(input, buffer, null);

        return replaceLineSeparators(buffer.toString());
    }

    private String replaceLineSeparators(final String resourceString) {
        final String tmpResourceString;

        if (!lineSeparator.equals(DEFAULT_LINE_SEPARATOR)) {
            tmpResourceString = resourceString.replace("\n", lineSeparator);
        } else {
            tmpResourceString = resourceString;
        }

        return tmpResourceString;
    }
}
