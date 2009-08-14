package net.jarlehansen.protobuf.javame.input;

import java.io.IOException;
import java.io.InputStream;

import jmunit.framework.cldc11.TestCase;

public class DelimitedInputStreamTest extends TestCase {	
	private DelimitedInputStream delimitedInputStream;
	private int limit = 10;
	
	public void setUp() {
		InputStream inputStream = new InputStream() {
			
			public int read() throws IOException {
				return limit;
			}
		};
		
		delimitedInputStream = new DelimitedInputStream(inputStream, limit);
	}
	
	public void tearDown() {
		try {
			delimitedInputStream.close();
		} catch(IOException io) {
			io.printStackTrace();
		}
	}
	
	public DelimitedInputStreamTest() {
		super(3, DelimitedInputStreamTest.class.getName());
	}

	public void test(int testNumber) throws IOException {
		switch(testNumber) {
		case 0:
			testReadFromDelimitedInputStream();
			break;
		case 1:
			testReadMoreThanLimitFromDelimitedInputStream();
			break;
		case 2:
			testReadFromEmptyDelimitedInputStream();
			break;
		}
	}
	
	private void testReadFromDelimitedInputStream() throws IOException {
		assertEquals(limit, delimitedInputStream.read());
	}
	
	private void testReadMoreThanLimitFromDelimitedInputStream() throws IOException {
		assertEquals(limit, delimitedInputStream.read(new byte[15], 0, 15));
	}
	
	private void testReadFromEmptyDelimitedInputStream() throws IOException {
		assertEquals(limit, delimitedInputStream.read(new byte[limit], 0, limit));
		assertEquals(-1, delimitedInputStream.read(new byte[limit], 0, limit));
	}
}
