package net.jarlehansen.protobuf.javame.input;

import java.io.IOException;
import java.io.InputStream;

import net.jarlehansen.protobuf.javame.original.input.CodedInputStream;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class DelimitedSizeUtil {
	
	private DelimitedSizeUtil() {
	}
	
	public static int readDelimitedSize(final InputStream input) throws IOException {
		return CodedInputStream.readDelimitedSize(input);
	}
}
