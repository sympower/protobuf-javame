package com.google.protobuf.javame;

import jmunit.framework.cldc11.TestCase;

import com.google.protobuf.javame.input.InputReader;
import com.google.protobuf.javame.output.OutputWriter;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class JavaMeProtoFactoryTest extends TestCase {

	/**
	 * The TestCase constructor takes two arguments: the number of tests to be
	 * run and the name of the test.
	 */
	public JavaMeProtoFactoryTest() {
		super(2, JavaMeProtoFactoryTest.class.getName());
	}
	
	
	public void test(int testNumber) {
		switch(testNumber) {
		case 0:
			testCreateInputUtilByteArray();
			break;
		case 1:
			testCreateOutputUtilByteArray();
			break;
		default:
			break;
		}
	}
	
	private void testCreateInputUtilByteArray() {
		assertTrue(JavaMeProtoFactory.createInputUtil(new byte[100]) instanceof InputReader);
	}
	
	private void testCreateOutputUtilByteArray() {
		assertTrue(JavaMeProtoFactory.createOutputUtil(new byte[100]) instanceof OutputWriter);
	}
}
