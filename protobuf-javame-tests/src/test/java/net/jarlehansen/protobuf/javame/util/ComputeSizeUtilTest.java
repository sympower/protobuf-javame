package net.jarlehansen.protobuf.javame.util;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class ComputeSizeUtilTest {

	@Test
	public void testComputeIntSize() {
		final int intId = 5;
		final int intVal = 21125;
		
		final int size = ComputeSizeUtil.computeIntSize(intId, intVal);
		
		assertEquals(4, size);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testComputeListSizeInvalidDataType() {
		@SuppressWarnings("unchecked")
		final Vector list = new Vector();

		ComputeSizeUtil.computeListSize(1, 50, list);
	}
}
