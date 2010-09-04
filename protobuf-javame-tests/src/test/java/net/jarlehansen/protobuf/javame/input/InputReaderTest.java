package net.jarlehansen.protobuf.javame.input;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.generated.JunitTestObject;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;

import org.junit.Test;

public class InputReaderTest {

	@Test
	public void testInput() throws IOException {
		JunitTestObject testObj = JunitTestObject.newBuilder().setId(1).setLongNumber(124124124).setBytesObject(
				ByteString.copyFromUtf8("test")).setFloatObject(3123123).setAmount(124.124).build();
		byte[] data = new byte[testObj.toByteArray().length];

		UnknownTagHandler tagHandler = mock(UnknownTagHandler.class);
		
		InputReader inputReader = new InputReader(data, tagHandler);
		
		// Call getPreviousTagDataTypeAndReadContent 5 times since there are 5 required fields
		for(int i = 0; i < 5; i++) {
			inputReader.getPreviousTagDataTypeAndReadContent();
		}
		
		verify(tagHandler, times(5)).unknownTag(anyString());
		verifyNoMoreInteractions(tagHandler);
	}
}
