package net.jarlehansen.protobuf.javame.inputandoutput;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.output.OutputWriter;

import org.junit.Test;

public class InputAndOutputNestedTest {
	
	@Test
	public void testtestWriteAndReadMessage() throws IOException {
		final int id = 1;
		final int messageSize = 132;
		final int value = 15;
		
		final byte[] data = new byte[ComputeSizeUtil.computeMessageSize(id, messageSize)];
		final OutputWriter output = new OutputWriter(data);
		output.writeMessage(id, messageSize);
		output.writeInt(id, value);
		output.writeData();
		
		final InputReader input = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		input.getNextFieldNumber();
		input.readMessageStart();
		input.getNextFieldNumber();
		
		assertEquals(value, input.readInt(id));
	}
}
