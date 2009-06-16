package net.jarlehansen.proto2javame.io.protoinput.factory;

import net.jarlehansen.proto2javame.io.protoinput.fields.FieldParser;
import net.jarlehansen.proto2javame.io.protoinput.fields.FieldParserImpl;
import net.jarlehansen.proto2javame.io.protoinput.message.MessageParser;
import net.jarlehansen.proto2javame.io.protoinput.message.MessageParserImpl;
import net.jarlehansen.proto2javame.io.protoinput.options.OptionParser;
import net.jarlehansen.proto2javame.io.protoinput.options.OptionParserImpl;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ProtoParserFactory {
	;
	
	public static FieldParser createFieldParser() {
		return FieldParserImpl.newInstance();
	}
	
	public static MessageParser createMessageParser() {
		return MessageParserImpl.newInstance();
	}
	
	public static OptionParser createOptionParser() {
		return OptionParserImpl.newInstance();
	}
}
