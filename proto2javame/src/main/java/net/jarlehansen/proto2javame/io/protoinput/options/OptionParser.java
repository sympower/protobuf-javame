package net.jarlehansen.proto2javame.io.protoinput.options;

import java.util.StringTokenizer;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface OptionParser {
	public String parsePackage(final StringTokenizer stringTokens);
	public String parseJavaPackage(final StringTokenizer stringTokens);
	public String parseJavaOuterClassname(final StringTokenizer stringTokens);
}
