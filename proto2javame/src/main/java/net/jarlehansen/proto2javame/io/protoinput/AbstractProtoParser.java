package net.jarlehansen.proto2javame.io.protoinput;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.protoinput.util.LineSplitterUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 1, 2010
 * Time: 9:27:24 PM
 */
public abstract class AbstractProtoParser implements ProtoParser {
    protected String line;
    protected String[] strings;

    public void setParserLine(final String line) {
        this.line = line;
        strings = LineSplitterUtil.split(line);
    }
    
    public abstract void parseAndAddProtoFile(final ProtoFileInput protoInput);
}
