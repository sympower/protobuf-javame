package com.google.protobuf.javame;

import java.io.InputStream;
import java.io.OutputStream;

import com.google.protobuf.javame.input.InputReader;
import com.google.protobuf.javame.input.InputReaderImpl;
import com.google.protobuf.javame.output.OutputWriter;
import com.google.protobuf.javame.output.OutputWriterImpl;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class JavaMeProtoFactory {
	
	private JavaMeProtoFactory() {
	}
	
	public static InputReader createInputUtil(final byte[] buffer) {
		return new InputReaderImpl(buffer);
	}
	
	public static InputReader createInputUtil(final InputStream input) {
		return new InputReaderImpl(input);
	}
	
	public static OutputWriter createOutputUtil(final byte[] dataHolder) {
		return new OutputWriterImpl(dataHolder);
	}
	
	public static OutputWriter createOutputUtil(final byte[] dataHolder, final OutputStream output) {
		return new OutputWriterImpl(dataHolder, output);
	}
}
