package net.jarlehansen.proto2javame.io.protoinput.factory;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.protoinput.ProtoParser;
import net.jarlehansen.proto2javame.io.protoinput.patterns.EnumParserConstants;
import net.jarlehansen.proto2javame.io.protoinput.patterns.FieldParserConstants;
import net.jarlehansen.proto2javame.io.protoinput.patterns.MessageParserConstants;
import net.jarlehansen.proto2javame.io.protoinput.patterns.OptionParserConstants;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Aug 31, 2010
 * Time: 9:40:04 PM
 */
public class ProtoParserFactoryImpl implements ProtoParserFactory {
    final static ProtoParser NULL_PARSER = new ProtoParser() {
        @Override
		public void setParserLine(String line) {
        }

        @Override
		public void parseAndAddProtoFile(ProtoFileInput protoInput) {
        }
    };

    private final ProtoParser enumParser;
    private final ProtoParser enumValueParser;
    private final ProtoParser fieldParser;
    private final ProtoParser messageParser;
    private final ProtoParser optionParser;

    private boolean newProtoInput = false;

    private boolean hasJavaPackage = false;

    private boolean hasMessageStart = false;
    private boolean hasFields = false;
    private boolean hasMessageEnd = false;

    private boolean hasEnumStart = false;
    private boolean hasEnumValue = false;
    private boolean hasEnumEnd = false;

    @Inject
    public ProtoParserFactoryImpl(@Named("EnumParser") ProtoParser enumParser, @Named("EnumValueParser") ProtoParser enumValueParser,
                                  @Named("FieldParser") ProtoParser fieldParser, @Named("MessageParser") ProtoParser messageParser,
                                  @Named("OptionParser") ProtoParser optionParser) {
        this.enumParser = enumParser;
        this.enumValueParser = enumValueParser;
        this.fieldParser = fieldParser;
        this.messageParser = messageParser;
        this.optionParser = optionParser;
    }

    @Override
	public ProtoParser getProtoParser(final String line) {
        ProtoParser protoParser = NULL_PARSER;

        if (matchesPackagePattern(line)) {
            // DO NOTHING, NOT SUPPORTED
        } else if (matchesJavaPackagePattern(line)) {
            protoParser = optionParser;
        } else if (matchesJavaOuterClassnamePattern(line)) {
            // DO NOTHING, NOT SUPPORTED
        } else if(matchesJavaImportPattern(line)){
        	// DO NOTHING, NOT SUPPORTED
        } else if(matchesCplusLitePattern(line)){
        	// DO NOTHING, NOT SUPPORTED
        } else if(matchesJsonPattern(line)){
        	protoParser = optionParser;
        } else if (matchesMessageStartPattern(line)) {
            protoParser = messageParser;
        } else if (matchesMessageFieldPattern(line)) {
            protoParser = fieldParser;
        } else if (matchesEnumStartPattern(line)) {
            protoParser = enumParser;
        } else if (matchesEnumValuePattern(line)) {
            protoParser = enumValueParser;
        } else if (matchesEnumEndPattern(line)) {
            resetEnumValues();
        } else if (matchesMessageEndPattern(line)) {
            resetAll();
        } else if (line.trim().length() > 0) {
            throw new ProtoFileValidationException("The .proto-file is invalid, content: " + line);
        }

        protoParser.setParserLine(line);
        return protoParser;
    }

    private void resetEnumValues() {
        hasEnumStart = false;
        hasEnumValue = false;
        hasEnumEnd = false;
    }

    private void resetAll() {
        resetEnumValues();
        hasJavaPackage = false;
        hasMessageStart = false;
        hasFields = false;
        newProtoInput = true;
    }

    @Override
	public boolean getHasMessageEnd() {
        return hasMessageEnd;
    }

    @Override
	public boolean isNewProto() {
        final boolean retValue;
        if(newProtoInput) {
            retValue = true;
            newProtoInput = false;
        } else {
            retValue = false;
        }
        
        return retValue;
    }

    private boolean matchesPackagePattern(final String line) {
        return OptionParserConstants.PATTERN_PACKAGE.matcher(line).matches();
    }

    private boolean matchesJavaPackagePattern(final String line) {
        final boolean pattern;

        if (hasJavaPackage) {
            pattern = false;
        } else {
            pattern = OptionParserConstants.PATTERN_JAVA_PACKAGE.matcher(line).matches();
            hasJavaPackage = pattern;
        }

        return pattern;
    }

    private boolean matchesJavaOuterClassnamePattern(final String line) {
        return OptionParserConstants.PATTERN_JAVA_OUTER_CLASSNAME.matcher(line).matches();
    }
    
    private boolean matchesJavaImportPattern(final String line){
    	return OptionParserConstants.PATTERN_JAVA_IMPORT.matcher(line).matches();
    }
    
    private boolean matchesCplusLitePattern(final String line){
    	return OptionParserConstants.PATTERN_CPLUS_OPTIMIZE_FOR_LITE.matcher(line).matches();
    }
    
    private boolean matchesJsonPattern(final String line) {
    	return OptionParserConstants.PATTERN_JSON_OPTIMIZE_OPT.matcher(line).matches();
    }

    private boolean matchesMessageStartPattern(final String line) {
        final boolean pattern;

        if (hasMessageStart || hasFields) {
            pattern = false;
        } else {
            pattern = MessageParserConstants.PATTERN_MESSAGE_START.matcher(line).matches();
            hasMessageStart = pattern;

            // Reset message end only if new message begins
            if (hasMessageStart) {
                hasMessageEnd = false;
            }
        }

        return pattern;
    }

    private boolean matchesMessageFieldPattern(final String line) {
        final boolean pattern;

        if (hasMessageStart && !hasMessageEnd) {
            pattern = FieldParserConstants.PATTERN_FIELD.matcher(line).matches();

            if (!hasFields) {
                hasFields = pattern;
            }
        } else {
            pattern = false;
        }

        return pattern;
    }

    private boolean matchesEnumStartPattern(final String line) {
        final boolean pattern;

        if (!hasEnumStart) {
            pattern = EnumParserConstants.PATTERN_ENUM_START.matcher(line).matches();
            hasEnumStart = pattern;
        } else {
            pattern = false;
        }

        return pattern;
    }

    private boolean matchesEnumValuePattern(final String line) {
        final boolean pattern;

        if (hasEnumStart && !hasEnumEnd) {
            pattern = EnumParserConstants.PATTERN_ENUM_VALUE.matcher(line).matches();

            if (!hasEnumValue) {
                hasEnumValue = pattern;
            }
        } else {
            pattern = false;
        }

        return pattern;
    }

    private boolean matchesEnumEndPattern(final String line) {
        final boolean pattern;

        if (hasEnumStart && hasEnumValue && !hasEnumEnd) {
            pattern = EnumParserConstants.PATTERN_ENUM_END.matcher(line).matches();
            hasEnumEnd = pattern;
        } else {
            pattern = false;
        }

        return pattern;
    }

    // will only return true if it matches the regex and does have fields and
    // does not have a message end tag

    private boolean matchesMessageEndPattern(final String line) {
        final boolean pattern;

        if (hasMessageStart && hasFields && !hasMessageEnd) {
            pattern = MessageParserConstants.PATTERN_MESSAGE_END.matcher(line).matches();
            hasMessageEnd = pattern;
        } else {
            pattern = false;
        }

        return pattern;
    }
}
