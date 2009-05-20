package net.jarlehansen.proto2javame.business.sourcebuilder.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class JavaSourceUtilTest {

	@Test
	public void testGetClassName() {
		assertEquals("TestMethod", JavaSourceCodeUtil.createClassName("testMethod"));
	}
}
