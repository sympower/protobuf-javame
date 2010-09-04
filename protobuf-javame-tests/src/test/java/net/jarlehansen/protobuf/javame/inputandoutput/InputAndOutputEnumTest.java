package net.jarlehansen.protobuf.javame.inputandoutput;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.generated.EnumTestObject;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.output.OutputWriter;

import org.junit.Test;

public class InputAndOutputEnumTest {
	
	@Test
	public void testWriteAndReadEnum() throws IOException {
		final int id = 1;
		final int enumValue = EnumTestObject.Color.BLUE;
		
		final byte[] data = new byte[ComputeSizeUtil.computeIntSize(id, enumValue)];
		final OutputWriter output = new OutputWriter(data);
		output.writeInt(id, enumValue);
		output.writeData();
		
		final InputReader input = new InputReader(data, DefaultUnknownTagHandlerImpl.newInstance());
		input.getNextFieldNumber();
		
		assertEquals(EnumTestObject.Color.BLUE, input.readInt(id));
	}
}
