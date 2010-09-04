package net.jarlehansen.proto2javame.io.protoinput;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Aug 31, 2010
 * Time: 9:25:47 PM
 */
public interface ProtoParser {
    public void setParserLine(final String line);
    public void parseAndAddProtoFile(final ProtoFileInput protoInput);
}
