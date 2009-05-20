package com.google.protobuf.javame.input;

import java.io.IOException;
import java.io.InputStream;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.WireFormat;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class InputReaderImpl implements InputReader {
	private final CodedInputStream codedInput;
	
	public InputReaderImpl(final byte[] buffer) {
		codedInput = CodedInputStream.newInstance(buffer);
	}
	
	public InputReaderImpl(final InputStream input) {
		codedInput = CodedInputStream.newInstance(input);
	}
	
	public int readInt(final int fieldNumber) throws IOException {
		return codedInput.readInt32();
	}
	
	public String readString(final int fieldNumber) throws IOException {
		return codedInput.readString();
	}
	
	public boolean readBoolean(final int fieldNumber) throws IOException {
		return codedInput.readBool();
	}
	
	public double readDouble(final int fieldNumber) throws IOException {
		return codedInput.readDouble();
	}
	
	public float readFloat(final int fieldNumber) throws IOException {
		return codedInput.readFloat();
	}
	
	public long readLong(final int fieldNumber) throws IOException {
		return codedInput.readInt64();
	}
	
	public ByteString readByteString(final int fieldNumber) throws IOException {
		return codedInput.readBytes();
	}
	
	public int getNextFieldNumber() throws IOException {
		return WireFormat.getTagFieldNumber(codedInput.readTag());
	}
}
