package net.jarlehansen.protobuf.javame.output;

import java.io.IOException;

import org.junit.Test;

public class CodedOutputStreamTest {

	@Test
	public void testValidByteArraySize() throws IOException {
		final String testString = "test";

		final byte[] validByteArray = new byte[CodedOutputStream.computeStringSize(1, testString)];
		final CodedOutputStream codedOutput = CodedOutputStream.newInstance(validByteArray);
		codedOutput.writeString(1, testString);

		codedOutput.checkNoSpaceLeft();
	}

	@Test(expected = IllegalStateException.class)
	public void testInvalidByteArraySize() throws IOException {
		final String testString = "test";

		// Creates a byte array that is larger than the actual input
		final byte[] invalidByteArray = new byte[CodedOutputStream.computeStringSize(1, testString) + 10];
		final CodedOutputStream codedOutput = CodedOutputStream.newInstance(invalidByteArray);
		codedOutput.writeString(1, testString);

		codedOutput.checkNoSpaceLeft();
	}
}
