package net.jarlehansen.proto2javame.io.protoinput.enums;

import net.jarlehansen.proto2javame.domain.proto.*;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.protoinput.AbstractProtoParser;
import net.jarlehansen.proto2javame.io.protoinput.ProtoParser;

import java.util.StringTokenizer;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Aug 31, 2010
 * Time: 8:53:22 PM
 */
public class EnumParser extends AbstractProtoParser {

    public EnumParser() {
    }

    /**
     * Valid input:
     * enum Color {
     */
    public void parseAndAddProtoFile(final ProtoFileInput protoInput) {
        protoInput.setEnumIfAbsent(strings[1]);
    }
}
