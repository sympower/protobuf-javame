package net.jarlehansen.proto2javame.io.protoinput.enums;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.protoinput.AbstractProtoParser;

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
