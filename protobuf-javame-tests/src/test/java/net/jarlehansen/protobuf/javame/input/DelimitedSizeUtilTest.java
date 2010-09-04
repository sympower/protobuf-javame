package net.jarlehansen.protobuf.javame.input;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class DelimitedSizeUtilTest {
	
	@Test
	public void testReadDelimitedSize() throws IOException {
		final InputStream input = mock(InputStream.class); 
		DelimitedSizeUtil.readDelimitedSize(input);
		
		verify(input, atLeastOnce()).read();
	}
}
