package net.jarlehansen.protobuf.javame.input;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class DelimitedSizeUtil {
	
	private DelimitedSizeUtil() {
	}
	
	public static int readDelimitedSize(final InputStream input) throws IOException {
		return CodedInputStream.readDelimitedSize(input);
	}
}
