package net.jarlehansen.protobuf.javame.extended.output;

import java.io.IOException;
import java.io.OutputStream;

import net.jarlehansen.protobuf.javame.factory.JavaMeProtoFactory;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.util.ComputeSizeUtil;

public abstract class AbstractOutputWriter {
	public byte[] toByteArray() throws IOException {
		final byte[] outputData = createByteArray();
		writeTo(outputData);

		return outputData;
	}

	public void writeTo(final byte[] data) throws IOException {
		final OutputWriter writer = JavaMeProtoFactory.createOutputUtil(data);
		writeFields(writer);
	}

	public void writeTo(final OutputStream outputStream) throws IOException {
		final byte[] outputData = createByteArray();

		final OutputWriter writer = JavaMeProtoFactory.createOutputUtil(outputData, outputStream);
		writeFields(writer);
	}

	public void writeDelimitedTo(final OutputStream outputStream) throws IOException {
		final int dataSize = computeSize();
		final int delimitedSize = dataSize + ComputeSizeUtil.computeDelimitedIntSize(dataSize);		
		final byte[] outputData = new byte[delimitedSize];
		
		final OutputWriter writer = JavaMeProtoFactory.createOutputUtil(outputData, outputStream);

		writer.writeDelimitedSize(dataSize);
		writeFields(writer);
	}
	
	public byte[] createByteArray() {
		return new byte[computeSize()];
	}

	protected abstract void writeFields(final OutputWriter writer) throws IOException;
	protected abstract int computeSize();
}
