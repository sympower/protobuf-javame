package net.jarlehansen.protobuf.javame.inputandoutput;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.original.input.InvalidProtocolBufferException;
import net.jarlehansen.protobuf.javame.output.OutputWriter;

import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class InputAndOutputTest {

	@Test
	public void testWriteAndReadInt() throws IOException {
		final int intValue = 10050;
		final int intId = 1;

		final byte[] data = new byte[ComputeSizeUtil.computeIntSize(intId, intValue)];
		final OutputWriter outputWriter = new OutputWriter(data);
		outputWriter.writeInt(intId, intValue);
		outputWriter.writeData();

		final InputReader inputReader = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();

		assertEquals(intValue, inputReader.readInt(intId));
	}

	@Test
	public void testWriteAndReadString() throws IOException {
		final String stringValue = "test string";
		final int intId = 1;

		final byte[] data = new byte[ComputeSizeUtil.computeStringSize(intId, stringValue)];
		final OutputWriter outputWriter = new OutputWriter(data);
		outputWriter.writeString(intId, stringValue);
		outputWriter.writeData();

		final InputReader inputReader = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();

		assertEquals(stringValue, inputReader.readString(intId));
	}

	@Test(expected = InvalidProtocolBufferException.class)
	public void testWriteAndReadStringInvalidValue() throws IOException {
		final int intValue = 123;
		final int intId = 1;

		final byte[] data = new byte[ComputeSizeUtil.computeIntSize(intId, intValue)];
		final OutputWriter outputWriter = new OutputWriter(data);
		outputWriter.writeInt(intId, intValue);
		outputWriter.writeData();

		final InputReader inputReader = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();

		inputReader.readString(intId);

	}

	@Test
	public void testWriteAndReadBoolean() throws IOException {
		final boolean boolValue = true;
		final int intId = 1;

		final byte[] data = new byte[ComputeSizeUtil.computeBooleanSize(intId, boolValue)];
		final OutputWriter outputWriter = new OutputWriter(data);
		outputWriter.writeBoolean(intId, boolValue);
		outputWriter.writeData();

		final InputReader inputReader = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();

		assertEquals(boolValue, inputReader.readBoolean(intId));
	}

	@Test
	public void testWriteAndReadDouble() throws IOException {
		final double doubleValue = 12.5;
		final int intId = 1;

		final byte[] data = new byte[ComputeSizeUtil.computeDoubleSize(intId, doubleValue)];
		final OutputWriter outputWriter = new OutputWriter(data);
		outputWriter.writeDouble(intId, doubleValue);
		outputWriter.writeData();

		final InputReader inputReader = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();

		assertEquals(doubleValue, inputReader.readDouble(intId), 0);
	}

	@Test
	public void testWriteAndReadFloat() throws IOException {
		final float floatValue = 12.5F;
		final int intId = 1;

		final byte[] data = new byte[ComputeSizeUtil.computeFloatSize(intId, floatValue)];
		final OutputWriter outputWriter = new OutputWriter(data);
		outputWriter.writeFloat(intId, floatValue);
		outputWriter.writeData();

		final InputReader inputReader = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();

		assertEquals(floatValue, inputReader.readFloat(intId), 0);
	}

	@Test
	public void testWriteAndReadLong() throws IOException {
		final long longValue = 124124124124124124L;
		final int intId = 1;

		final byte[] data = new byte[ComputeSizeUtil.computeLongSize(intId, longValue)];
		final OutputWriter outputWriter = new OutputWriter(data);
		outputWriter.writeLong(intId, longValue);
		outputWriter.writeData();

		final InputReader inputReader = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();

		assertEquals(longValue, inputReader.readLong(intId));
	}

	@Test
	public void testWriteAndReadByteString() throws IOException {
		final ByteString byteValue = ByteString.copyFromUtf8("testing");
		final int intId = 1;

		final byte[] data = new byte[ComputeSizeUtil.computeByteStringSize(intId, byteValue)];
		final OutputWriter outputWriter = new OutputWriter(data);
		outputWriter.writeByteString(intId, byteValue);
		outputWriter.writeData();

		final InputReader inputReader = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();

		assertEquals(byteValue.toStringUtf8(), inputReader.readByteString(intId).toStringUtf8());
	}
}
