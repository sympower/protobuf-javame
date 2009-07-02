package net.jarlehansen.proto2javame.io.protoinput.fields;

import java.util.StringTokenizer;

import net.jarlehansen.proto2javame.domain.proto.FieldData;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface FieldParser {
	public FieldData parseField(final StringTokenizer stringTokens);
}
