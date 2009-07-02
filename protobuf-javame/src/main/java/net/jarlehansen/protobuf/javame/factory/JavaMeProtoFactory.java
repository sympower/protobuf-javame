package net.jarlehansen.protobuf.javame.factory;

import java.io.InputStream;
import java.io.OutputStream;

import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.InputReaderImpl;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.output.OutputWriterImpl;


/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class JavaMeProtoFactory {
	
	private JavaMeProtoFactory() {
	}
	
	public static InputReader createInputUtil(final byte[] buffer, final UnknownTagHandler unknownTagHandler) {
		return new InputReaderImpl(buffer, unknownTagHandler);
	}
	
	public static InputReader createInputUtil(final InputStream input, final UnknownTagHandler unknownTagHandler) {
		return new InputReaderImpl(input, unknownTagHandler);
	}
	
	public static OutputWriter createOutputUtil(final byte[] dataHolder) {
		return new OutputWriterImpl(dataHolder);
	}
	
	public static OutputWriter createOutputUtil(final byte[] dataHolder, final OutputStream output) {
		return new OutputWriterImpl(dataHolder, output);
	}
}
