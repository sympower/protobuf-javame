package net.jarlehansen.proto2javame.io.protoinput.options;

import java.util.StringTokenizer;

import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.validator.ProtoFileValues;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class OptionParserImpl implements OptionParser {
	
	private OptionParserImpl() {
	}
	
	public static OptionParserImpl newInstance() {
		return new OptionParserImpl();
	}
	
	public String parsePackage(final StringTokenizer stringTokens) {
		return "NOT SUPPORTED";
	}
	
	public String parseJavaPackage(final StringTokenizer stringTokens) {
		// option token
		stringTokens.nextToken();
		// java_package token
		stringTokens.nextToken();
		// equals token
		stringTokens.nextToken();
		
		// removed "-chars
		final String tempPackageName = stringTokens.nextToken().replace("\"", "");

		final int endTagIndex = tempPackageName.indexOf(ProtoFileValues.END_FIELD_TAG.getTag());
		final String packageName;

		if (endTagIndex < 0) {
			if (stringTokens.hasMoreTokens() && ProtoFileValues.END_FIELD_TAG.getTag().equals(stringTokens.nextToken())) {
				packageName = tempPackageName;
			} else {
				throw new ProtoFileValidationException("The package tag was not valid, \";\" must be included");
			}
		} else {
			packageName = tempPackageName.substring(0, tempPackageName.indexOf(ProtoFileValues.END_FIELD_TAG.getTag()));
		}
		
		return packageName;
	}

	public String parseJavaOuterClassname(final StringTokenizer stringTokens) {
		return "NOT SUPPORTED";
	}
}
