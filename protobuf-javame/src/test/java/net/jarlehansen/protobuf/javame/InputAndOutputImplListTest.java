package net.jarlehansen.protobuf.javame;

import java.io.IOException;
import java.util.Vector;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.InvalidProtocolBufferException;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.InputReaderImpl;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.output.OutputWriterImpl;
import net.jarlehansen.protobuf.javame.util.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.util.SupportedDataTypes;


import jmunit.framework.cldc11.TestCase;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class InputAndOutputImplListTest extends TestCase {
	private final static int FIELD_ID = 1;
	private Vector list;

	/**
	 * The TestCase constructor takes two arguments: the number of tests to be
	 * run and the name of the test.
	 */
	public InputAndOutputImplListTest() {
		super(8, InputAndOutputImplListTest.class.getName());
	}

	public void test(int testNumber) throws IOException {
		switch (testNumber) {
		case 0:
			testWriteAndReadIntList();
			break;
		case 1:
			testWriteAndReadStringList();
			break;
		 case 2:
			testWriteAndReadStringListInvalidValue();
			break;
		 case 3:
			testWriteAndReadBooleanList();
			break;
		 case 4:
			testWriteAndReadDoubleList();
			break;
		 case 5:
			testWriteAndReadFloatList();
			break;
		 case 6:
			testWriteAndReadLongList();
			break;
		 case 7:
			testWriteAndReadByteStringList();
			break;
		default:
			break;
		}
	}

	public void setUp() {
		list = new Vector();
	}

	private void testWriteAndReadIntList() throws IOException {
		final int intValue1 = 1241266623;
		final int intValue2 = 10050;

		list.addElement(new Integer(intValue1));
		list.addElement(new Integer(intValue2));

		final byte[] data = new byte[ComputeSizeUtil.computeListSize(FIELD_ID, SupportedDataTypes.DATA_TYPE_INT, list)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);

		outputWriter.writeList(FIELD_ID, SupportedDataTypes.DATA_TYPE_INT, list);
		outputWriter.writeData();

		final InputReader inputReader = new InputReaderImpl(data);

		assertEquals(intValue1, inputReader.readInt(inputReader.getNextFieldNumber()));
		assertEquals(intValue2, inputReader.readInt(inputReader.getNextFieldNumber()));
	}

	private void testWriteAndReadStringList() throws IOException {
		final String stringValue1 = "test string";
		final String stringValue2 = "test string2";

		list.addElement(stringValue1);
		list.addElement(stringValue2);

		final byte[] data = new byte[ComputeSizeUtil.computeListSize(FIELD_ID, SupportedDataTypes.DATA_TYPE_STRING,
				list)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);

		outputWriter.writeList(FIELD_ID, SupportedDataTypes.DATA_TYPE_STRING, list);
		outputWriter.writeData();

		final InputReader inputReader = new InputReaderImpl(data);

		assertEquals(stringValue1, inputReader.readString(inputReader.getNextFieldNumber()));
		assertEquals(stringValue2, inputReader.readString(inputReader.getNextFieldNumber()));
	}

	private void testWriteAndReadStringListInvalidValue() throws IOException {
		final int intValue1 = 123;
		final int intValue2 = 1812841924;
		
		list.addElement(new Integer(intValue1));
		list.addElement(new Integer(intValue2));
		
		final byte[] data = new byte[ComputeSizeUtil.computeListSize(FIELD_ID, SupportedDataTypes.DATA_TYPE_INT, list)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		
		outputWriter.writeList(FIELD_ID, SupportedDataTypes.DATA_TYPE_INT, list);
		outputWriter.writeData();

		final InputReader inputReader = new InputReaderImpl(data);
		inputReader.getNextFieldNumber();
		
		try {
			inputReader.readString(FIELD_ID);
			fail("Expected " + InvalidProtocolBufferException.class.getName());
		} catch (InvalidProtocolBufferException in) {
			// Exception expected
		}
	}

	private void testWriteAndReadBooleanList() throws IOException {
		final boolean boolValue1 = true;
		final boolean boolValue2 = false;
		
		list.addElement(new Boolean(boolValue1));
		list.addElement(new Boolean(boolValue2));
		
		final byte[] data = new byte[ComputeSizeUtil.computeListSize(FIELD_ID, SupportedDataTypes.DATA_TYPE_BOOLEAN, list)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		
		outputWriter.writeList(FIELD_ID, SupportedDataTypes.DATA_TYPE_BOOLEAN, list);
		outputWriter.writeData();

		final InputReader inputReader = new InputReaderImpl(data);

		assertEquals(boolValue1, inputReader.readBoolean(inputReader.getNextFieldNumber()));
		assertEquals(boolValue2, inputReader.readBoolean(inputReader.getNextFieldNumber()));
	}

	private void testWriteAndReadDoubleList() throws IOException {
		final double doubleValue1 = 12.5;
		final double doubleValue2 = 1241.135;
		
		list.addElement(new Double(doubleValue1));
		list.addElement(new Double(doubleValue2));
		
		final byte[] data = new byte[ComputeSizeUtil.computeListSize(FIELD_ID, SupportedDataTypes.DATA_TYPE_DOUBLE, list)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		
		outputWriter.writeList(FIELD_ID, SupportedDataTypes.DATA_TYPE_DOUBLE, list);
		outputWriter.writeData();

		final InputReader inputReader = new InputReaderImpl(data);
		
		assertEquals(doubleValue1, inputReader.readDouble(inputReader.getNextFieldNumber()));
		assertEquals(doubleValue2, inputReader.readDouble(inputReader.getNextFieldNumber()));
	}

	private void testWriteAndReadFloatList() throws IOException {
		final float floatValue1 = 12.5F;
		final float floatValue2 = 125.1241245F;
		
		list.addElement(new Float(floatValue1));
		list.addElement(new Float(floatValue2));
		
		final byte[] data = new byte[ComputeSizeUtil.computeListSize(FIELD_ID, SupportedDataTypes.DATA_TYPE_FLOAT, list)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		
		outputWriter.writeList(FIELD_ID, SupportedDataTypes.DATA_TYPE_FLOAT, list);
		outputWriter.writeData();

		final InputReader inputReader = new InputReaderImpl(data);

		assertEquals(floatValue1, inputReader.readFloat(inputReader.getNextFieldNumber()));
		assertEquals(floatValue2, inputReader.readFloat(inputReader.getNextFieldNumber()));
	}

	private void testWriteAndReadLongList() throws IOException {
		final long longValue1 = 124124124124124124L;
		final long longValue2 = 941724124L;

		list.addElement(new Long(longValue1));
		list.addElement(new Long(longValue2));
		
		final byte[] data = new byte[ComputeSizeUtil.computeListSize(FIELD_ID, SupportedDataTypes.DATA_TYPE_LONG, list)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		
		outputWriter.writeList(FIELD_ID, SupportedDataTypes.DATA_TYPE_LONG, list);
		outputWriter.writeData();

		final InputReader inputReader = new InputReaderImpl(data);

		assertEquals(longValue1, inputReader.readLong(inputReader.getNextFieldNumber()));
		assertEquals(longValue2, inputReader.readLong(inputReader.getNextFieldNumber()));
	}

	private void testWriteAndReadByteStringList() throws IOException {
		final ByteString byteValue1 = ByteString.copyFromUtf8("testing");
		final ByteString byteValue2 = ByteString.copyFromUtf8("testing testing2");

		list.addElement(byteValue1);
		list.addElement(byteValue2);
		
		final byte[] data = new byte[ComputeSizeUtil.computeListSize(FIELD_ID, SupportedDataTypes.DATA_TYPE_BYTESTRING, list)];
		final OutputWriter outputWriter = new OutputWriterImpl(data);
		
		outputWriter.writeList(FIELD_ID, SupportedDataTypes.DATA_TYPE_BYTESTRING, list);
		outputWriter.writeData();

		final InputReader inputReader = new InputReaderImpl(data);

		assertEquals(byteValue1.toStringUtf8(), inputReader.readByteString(inputReader.getNextFieldNumber()).toStringUtf8());
		assertEquals(byteValue2.toStringUtf8(), inputReader.readByteString(inputReader.getNextFieldNumber()).toStringUtf8());
	}
}
