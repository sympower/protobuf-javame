package net.jarlehansen.protobuf.javame.input;

import java.io.IOException;
import java.io.InputStream;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.original.WireFormat;
import net.jarlehansen.protobuf.javame.original.input.CodedInputStream;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class InputReader {
	private final UnknownTagHandler unknownTagHandler;

	private final CodedInputStream codedInput;
	private int previousTag = 0;

	public InputReader(final byte[] buffer, final UnknownTagHandler unknownTagHandler) {
		codedInput = CodedInputStream.newInstance(buffer);
		this.unknownTagHandler = unknownTagHandler;
	}

	public InputReader(final InputStream input, final UnknownTagHandler unknownTagHandler) {
		codedInput = CodedInputStream.newInstance(input);
		this.unknownTagHandler = unknownTagHandler;
	}
	
	public InputReader(final DelimitedInputStream input, final UnknownTagHandler unknownTagHandler) {
		codedInput = CodedInputStream.newInstance(input);
		this.unknownTagHandler = unknownTagHandler;
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

	public int readMessageStart() throws IOException {
		return codedInput.readMessageStart();
	}
	
	public int getNextFieldNumber() throws IOException {
		previousTag = codedInput.readTag();
		return WireFormat.getTagFieldNumber(previousTag);
	}

	public void getPreviousTagDataTypeAndReadContent() throws IOException {
		final int dataType = WireFormat.getTagWireType(previousTag);
		final StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("FieldNumber: ").append(WireFormat.getTagFieldNumber(previousTag)).append(" - ");

		switch (dataType) {
		case WireFormat.WIRETYPE_FIXED32:
			stringBuffer.append("float value: ").append(Float.toString(codedInput.readFloat()));
			break;
		case WireFormat.WIRETYPE_FIXED64:
			stringBuffer.append("double value: ").append(Double.toString(codedInput.readDouble()));
			break;
		case WireFormat.WIRETYPE_LENGTH_DELIMITED:
			stringBuffer.append("Length delimited (String or ByteString) value: ").append(codedInput.readString());
			break;
		case WireFormat.WIRETYPE_VARINT:
			stringBuffer.append("varint (long, int or boolean) value: ").append(codedInput.readRawVarint64());
			break;
		default:
			break;
		}

		unknownTagHandler.unknownTag(stringBuffer.toString());
	}
}
