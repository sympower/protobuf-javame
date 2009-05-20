package com.google.protobuf.javame.output;

import java.io.IOException;
import java.util.Vector;

import jmunit.framework.cldc11.TestCase;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class ComputeSizeUtilTest extends TestCase {
	/**
	 * The TestCase constructor takes two arguments: the number of tests to be
	 * run and the name of the test.
	 */
	public ComputeSizeUtilTest() {
		super(2, ComputeSizeUtilTest.class.getName());
	}

	public void test(int testNumber) throws IOException {
		switch (testNumber) {
		case 0:
			testComputeIntSize();
			break;
		case 1:
			testComputeListSizeInvalidDataType();
			break;
		default:
			break;
		}
	}
	
	private void testComputeIntSize() {
		final int intId = 5;
		final int intVal = 21125;
		
		final int size = ComputeSizeUtil.computeIntSize(intId, intVal);
		
		assertEquals(4, size);
	}
	
	private void testComputeListSizeInvalidDataType() {
		final Vector list = new Vector();
		
		try {
			ComputeSizeUtil.computeListSize(1, 50, list);
			fail("Expected " + IllegalArgumentException.class.getName());
		} catch(IllegalArgumentException iae) {
			// Exception expected
		}
		
	}
}
