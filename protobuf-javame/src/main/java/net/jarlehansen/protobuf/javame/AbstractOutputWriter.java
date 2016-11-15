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

	public byte[] toDelimitedByteArray() throws IOException {
		final byte[] outputData = createDelimitedByteArray();
		writeDelimitedTo(outputData);
		return outputData;
	}

	public void writeTo(final byte[] data) throws IOException {
		writeTo(data, 0, data.length);
	}

	public void writeTo(final byte[] data, int offset, int len) throws IOException {
		final OutputWriter writer = new OutputWriter(data, offset, len);
		writeFields(writer);
	}

	public void writeTo(final OutputStream outputStream) throws IOException {
		final byte[] outputData = createByteArray();

		final OutputWriter writer = new OutputWriter(outputData, outputStream);
		writeFields(writer);
		//http://code.google.com/p/protobuf-javame/issues/detail?id=9 [Output of writeTo(stream) is wrong]
		writer.writeData();
	}

	public void writeDelimitedTo(final byte[] data) throws IOException {
		writeDelimitedTo(data, 0, data.length);
	}
	public void writeDelimitedTo(final byte[] data, int offset, int len) throws IOException {
		final OutputWriter writer = new OutputWriter(data, offset, len);
		writer.writeDelimitedSize(computeSize());
		writeFields(writer);
	}

	public void writeDelimitedTo(final OutputStream outputStream) throws IOException {
		final byte[] outputData = createDelimitedByteArray();
		
		final OutputWriter writer = new OutputWriter(outputData, outputStream);

		writer.writeDelimitedSize(computeSize());
		writeFields(writer);
		writer.writeData();
	}
	
	public byte[] createByteArray() {
		return new byte[computeSize()];
	}
	public byte[] createDelimitedByteArray() {
		return new byte[computeDelimitedSize()];
	}

	public abstract void writeFields(final OutputWriter writer) throws IOException;
	public int computeDelimitedSize() {
		final int dataSize = computeSize();
		return dataSize + ComputeSizeUtil.computeDelimitedIntSize(dataSize);
	}
	public abstract int computeSize();
}
