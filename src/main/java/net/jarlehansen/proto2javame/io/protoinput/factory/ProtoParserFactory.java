package net.jarlehansen.proto2javame.io.protoinput.factory;

import net.jarlehansen.proto2javame.io.protoinput.ProtoParser;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Aug 31, 2010
 * Time: 10:09:00 PM
 */
public interface ProtoParserFactory {
    public ProtoParser getProtoParser(final String line);
    public boolean getHasMessageEnd();
    public boolean isNewProto();
}
