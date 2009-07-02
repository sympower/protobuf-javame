package net.jarlehansen.protobuf.javame.output;

import java.io.IOException;

import jmunit.framework.cldc11.TestCase;

public class CodedOutputStreamTest extends TestCase {

	public CodedOutputStreamTest() {
		super(2, CodedOutputStreamTest.class.getName());
	}

	public void test(final int testNumber) throws IOException {
		switch (testNumber) {
		case 0:
			testValidByteArraySize();
			break;
		case 1:
			testInvalidByteArraySize();
		default:
			break;
		}
	}

	private void testValidByteArraySize() throws IOException {
		final String testString = "test";

		final byte[] validByteArray = new byte[CodedOutputStream.computeStringSize(1, testString)];
		final CodedOutputStream codedOutput = CodedOutputStream.newInstance(validByteArray);
		codedOutput.writeString(1, testString);

		try {
			codedOutput.checkNoSpaceLeft();
		} catch (IllegalStateException ise) {
			fail("The CodedOutputStream should not have any space left, expected IllegalStateException. Exception: "
					+ ise.getMessage());
		}
	}

	public void testInvalidByteArraySize() throws IOException {
		final String testString = "test";

		// Creates a byte array that is larger than the actual input
		final byte[] validByteArray = new byte[CodedOutputStream.computeStringSize(1, testString) + 10];
		final CodedOutputStream codedOutput = CodedOutputStream.newInstance(validByteArray);
		codedOutput.writeString(1, testString);

		try {
			codedOutput.checkNoSpaceLeft();
			fail("The CodedOutputStream should throw an IllegalStateException since the byte array is larger than the actual input");
		} catch (IllegalStateException ise) {
			// Expected
		}
	}
}
