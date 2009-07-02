package net.jarlehansen.protobuf.javame.inputandoutput;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.InputReaderImpl;
import net.jarlehansen.protobuf.javame.input.InvalidProtocolBufferException;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.output.OutputWriterImpl;
import net.jarlehansen.protobuf.javame.util.ComputeSizeUtil;


import jmunit.framework.cldc11.TestCase;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class InputAndOutputImplTest extends TestCase {
	
	/**
	 * The TestCase constructor takes two arguments: the number of tests to be
	 * run and the name of the test.
	 */
	public InputAndOutputImplTest() {
		super(8, InputAndOutputImplTest.class.getName());
	}
	
	public void test(int testNumber) throws IOException {
		switch(testNumber) {
		case 0:
			testWriteAndReadInt();
			break;
		case 1:
			testWriteAndReadString();
			break;
		case 2:
			testWriteAndReadStringInvalidValue();
			break;
		case 3:
			testWriteAndReadBoolean();
			break;
		case 4:
			testWriteAndReadDouble();
			break;
		case 5:
			testWriteAndReadFloat();
			break;
		case 6:
			testWriteAndReadLong();
			break;
		case 7:
			testWriteAndReadByteString();
			break;
		default:
			break;
		}
	}
	
	private void testWriteAndReadInt() throws IOException {
		final int intValue = 10050;
		final int intId = 1;
		
		final byte[] data = new byte[ComputeSizeUtil.computeIntSize(intId, intValue)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		outputWriter.writeInt(intId, intValue);
		outputWriter.writeData();
		
		final InputReader inputReader = new InputReaderImpl(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();
		
		assertEquals(intValue, inputReader.readInt(intId));
	}
	
	private void testWriteAndReadString() throws IOException {
		final String stringValue = "test string";
		final int intId = 1;
		
		final byte[] data = new byte[ComputeSizeUtil.computeStringSize(intId, stringValue)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		outputWriter.writeString(intId, stringValue);
		outputWriter.writeData();
		
		final InputReader inputReader = new InputReaderImpl(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();
		
		assertEquals(stringValue, inputReader.readString(intId));
	}
	
	private void testWriteAndReadStringInvalidValue() throws IOException {
		final int intValue = 123;
		final int intId = 1;
		
		final byte[] data = new byte[ComputeSizeUtil.computeIntSize(intId, intValue)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		outputWriter.writeInt(intId, intValue);
		outputWriter.writeData();
		
		final InputReader inputReader = new InputReaderImpl(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();
		try {
			inputReader.readString(intId);
			fail("Expected " + InvalidProtocolBufferException.class.getName());
		} catch(InvalidProtocolBufferException in) {
			// Exception expected
		}
	}
	
	private void testWriteAndReadBoolean() throws IOException {
		final boolean boolValue = true;
		final int intId = 1;
		
		final byte[] data = new byte[ComputeSizeUtil.computeBooleanSize(intId, boolValue)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		outputWriter.writeBoolean(intId, boolValue);
		outputWriter.writeData();
		
		final InputReader inputReader = new InputReaderImpl(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();
		
		assertEquals(boolValue, inputReader.readBoolean(intId));
	}
	
	private void testWriteAndReadDouble() throws IOException {
		final double doubleValue = 12.5;
		final int intId = 1;
		
		final byte[] data = new byte[ComputeSizeUtil.computeDoubleSize(intId, doubleValue)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		outputWriter.writeDouble(intId, doubleValue);
		outputWriter.writeData();
		
		final InputReader inputReader = new InputReaderImpl(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();
		
		assertEquals(doubleValue, inputReader.readDouble(intId));
	}
	
	private void testWriteAndReadFloat() throws IOException {
		final float floatValue = 12.5F;
		final int intId = 1;
		
		final byte[] data = new byte[ComputeSizeUtil.computeFloatSize(intId, floatValue)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		outputWriter.writeFloat(intId, floatValue);
		outputWriter.writeData();
		
		final InputReader inputReader = new InputReaderImpl(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();
		
		assertEquals(floatValue, inputReader.readFloat(intId));
	}
	
	private void testWriteAndReadLong() throws IOException {
		final long longValue = 124124124124124124L;
		final int intId = 1;
		
		final byte[] data = new byte[ComputeSizeUtil.computeLongSize(intId, longValue)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		outputWriter.writeLong(intId, longValue);
		outputWriter.writeData();
		
		final InputReader inputReader = new InputReaderImpl(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();
		
		assertEquals(longValue, inputReader.readLong(intId));
	}
	
	private void testWriteAndReadByteString() throws IOException {
		final ByteString byteValue = ByteString.copyFromUtf8("testing");
		final int intId = 1;
		
		final byte[] data = new byte[ComputeSizeUtil.computeByteStringSize(intId, byteValue)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		outputWriter.writeByteString(intId, byteValue);
		outputWriter.writeData();
		
		final InputReader inputReader = new InputReaderImpl(data, DefaultUnknownTagHandlerImpl.newInstance());
		inputReader.getNextFieldNumber();
		
		assertEquals(byteValue.toStringUtf8(), inputReader.readByteString(intId).toStringUtf8());
	}
}	
