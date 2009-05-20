package net.jarlehansen.proto2javame.io.protoinput;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.protoinput.factory.ProtoParserFactory;
import net.jarlehansen.proto2javame.io.protoinput.fields.FieldParser;
import net.jarlehansen.proto2javame.io.protoinput.message.MessageParser;
import net.jarlehansen.proto2javame.io.protoinput.options.OptionParser;
import net.jarlehansen.proto2javame.io.protoinput.patterns.FieldParserConstants;
import net.jarlehansen.proto2javame.io.protoinput.patterns.MessageParserConstants;
import net.jarlehansen.proto2javame.io.protoinput.patterns.OptionParserConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class ProtoObjectBuilderImpl implements ProtoObjectBuilder {
    private final FieldParser fieldParser;
    private final MessageParser messageParser;
    private final OptionParser optionParser;

    private boolean hasJavaPackage = false;
    private boolean hasMessageStart = false;
    private boolean hasFields = false;
    private boolean hasMessageEnd = false;

    private int lineNumber = 1;

    private final ProtoFileInput protoInput = new ProtoFileInput();

    private ProtoObjectBuilderImpl() {
        fieldParser = ProtoParserFactory.createFieldParser();
        messageParser = ProtoParserFactory.createMessageParser();
        optionParser = ProtoParserFactory.createOptionParser();
    }

    public static ProtoObjectBuilderImpl newInstance() {
        return new ProtoObjectBuilderImpl();
    }

    /**
     * Validates and save data loaded from the .proto file. Reads each line in
     * the file, and calls parseLine().
     *
     * @return ProtoFileInput
     */
    public ProtoFileInput validateAndSaveProtoData(final String protoLocation) {
        ProtoTagUtil.isValidProtoFile(protoLocation);

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(protoLocation));
            String line = reader.readLine();

            while (line != null) {
                parseLine(line);

                line = reader.readLine();

                lineNumber++;
            }
        } catch (FileNotFoundException fnfe) {
            throw new ProtoFileValidationException("FileNotFoundException thrown in " + this.getClass().getName()
                    + ", this should never happen", fnfe);
        } catch (IOException io) {
            throw new ProtoFileValidationException("IOException thrown in " + this.getClass().getName()
                    + ", this should never happen", io);
        } catch (ProtoFileValidationException proto) {
            proto.setLineNumber(lineNumber);
            throw proto;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException io2) {
                // No serious problem if this fails
            }
        }

        // throws exception if hasMessageStart, hasFields or hasMessageEnd is
        // false
        checkProtoMessageParts();

        return protoInput;
    }

    private void parseLine(final String line) {
        final StringTokenizer stringTokens = new StringTokenizer(line, "[ ]+");

        if (matchesPackagePattern(line)) {
            optionParser.parsePackage(stringTokens);
        } else if (matchesJavaPackagePattern(line)) {
            protoInput.setPackageName(optionParser.parseJavaPackage(stringTokens));
        } else if (matchesJavaOuterClassnamePattern(line)) {
            optionParser.parseJavaOuterClassname(stringTokens);
        } else if (matchesMessageStartPattern(line)) {
            protoInput.setProtoClassName(messageParser.parseMessageStart(stringTokens));
        } else if (matchesMessageFieldPattern(line)) {
            protoInput.addFieldData(fieldParser.parseField(stringTokens));
        } else if (matchesMessageEndPattern(line)) {
            messageParser.parseMessageEnd(stringTokens);
        } else if (line.trim().length() > 0) {
            throw new ProtoFileValidationException("The .proto-file is invalid");
        }
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

            if (pattern) {
                hasJavaPackage = true;
            }
        }

        return pattern;
    }

    private boolean matchesJavaOuterClassnamePattern(final String line) {
        return OptionParserConstants.PATTERN_JAVA_OUTER_CLASSNAME.matcher(line).matches();
    }

    // will only return true if it matches the regex and does not already have a
    // message start tag
    private boolean matchesMessageStartPattern(final String line) {
        final boolean pattern;

        if (hasMessageStart) {
            pattern = false;
        } else {
            pattern = MessageParserConstants.PATTERN_MESSAGE_START.matcher(line).matches();

            if (pattern) {
                hasMessageStart = true;
            }
        }

        return pattern;
    }

    // will only return true if it matches the regex and does have a message
    // start tag
    private boolean matchesMessageFieldPattern(final String line) {
        final boolean pattern;

        if (hasMessageStart) {
            pattern = FieldParserConstants.PATTERN_FIELD.matcher(line).matches();

            if (pattern) {
                hasFields = true;
            }
        } else {
            pattern = false;
        }

        return pattern;
    }

    // will only return true if it matches the regex and does have fields and
    // does not have a message end tag
    private boolean matchesMessageEndPattern(final String line) {
        final boolean pattern;

        if (hasFields && !hasMessageEnd) {
            pattern = MessageParserConstants.PATTERN_MESSAGE_END.matcher(line).matches();

            if (pattern) {
                hasMessageEnd = true;
            }
        } else {
            pattern = false;
        }

        return pattern;
    }

    // only the message end will provide an additional message, all other cases
    // should be caught earlier than this check
    void checkProtoMessageParts() {
        if (!hasMessageStart || !hasFields || !hasMessageEnd) {
            String message = "";

            if (!hasMessageEnd) {
                message = ", the last } was not included";
            }

            final ProtoFileValidationException exception = new ProtoFileValidationException("Invalid .proto-file" + message);
            exception.setLineNumber(lineNumber);

            throw exception;
		}
	}
}
