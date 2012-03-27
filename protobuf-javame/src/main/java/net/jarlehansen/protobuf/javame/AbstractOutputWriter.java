package net.jarlehansen.protobuf.javame;

import java.io.IOException;
import java.io.OutputStream;

import net.jarlehansen.protobuf.javame.output.OutputWriter;

public abstract class AbstractOutputWriter implements CustomListWriter {
	public byte[] toByteArray() throws IOException {
		final byte[] outputData = createByteArray();
		writeTo(outputData);

		return outputData;
	}

	public void writeTo(final byte[] data) throws IOException {
		final OutputWriter writer = new OutputWriter(data);
		writeFields(writer);
	}

	public void writeTo(final OutputStream outputStream) throws IOException {
		final byte[] outputData = createByteArray();

		final OutputWriter writer = new OutputWriter(outputData, outputStream);
		writeFields(writer);
		//http://code.google.com/p/protobuf-javame/issues/detail?id=9 [Output of writeTo(stream) is wrong]
		writer.writeData();
	}

	public void writeDelimitedTo(final OutputStream outputStream) throws IOException {
		final int dataSize = computeSize();
		final int delimitedSize = dataSize + ComputeSizeUtil.computeDelimitedIntSize(dataSize);		
		final byte[] outputData = new byte[delimitedSize];
		
		final OutputWriter writer = new OutputWriter(outputData, outputStream);

		writer.writeDelimitedSize(dataSize);
		writeFields(writer);
	}
	
	public byte[] createByteArray() {
		return new byte[computeSize()];
	}

	public abstract void writeFields(final OutputWriter writer) throws IOException;
	public abstract int computeSize();
}
