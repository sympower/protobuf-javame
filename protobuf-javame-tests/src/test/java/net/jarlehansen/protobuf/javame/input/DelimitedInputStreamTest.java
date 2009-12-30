package net.jarlehansen.protobuf.javame.input;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DelimitedInputStreamTest {	
	private DelimitedInputStream delimitedInputStream;
	private final int limit = 10;
	
	@Before
	public void setUp() {
		InputStream inputStream = new InputStream() {
			public int read() throws IOException {
				return limit;
			}
		};
		
		delimitedInputStream = new DelimitedInputStream(inputStream, limit);
	}
	
	@After
	public void tearDown() {
		try {
			delimitedInputStream.close();
		} catch(IOException io) {
			io.printStackTrace();
		}
	}

	@Test
	public void testReadFromDelimitedInputStream() throws IOException {
		assertEquals(limit, delimitedInputStream.read());
	}
	
	@Test
	public void testReadMoreThanLimitFromDelimitedInputStream() throws IOException {
		assertEquals(limit, delimitedInputStream.read(new byte[15], 0, 15));
	}
	
	@Test
	public void testReadFromEmptyDelimitedInputStream() throws IOException {
		assertEquals(limit, delimitedInputStream.read(new byte[limit], 0, limit));
		assertEquals(-1, delimitedInputStream.read(new byte[limit], 0, limit));
	}
}
