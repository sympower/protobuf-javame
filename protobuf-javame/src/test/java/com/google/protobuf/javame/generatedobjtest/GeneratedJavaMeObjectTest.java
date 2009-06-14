package com.google.protobuf.javame.generatedobjtest;

import java.io.IOException;
import java.util.Vector;

import net.jarlehansen.protobuf.javame.ByteString;

import jmunit.framework.cldc11.TestCase;

import com.google.protobuf.javame.generated.JunitTestObject;
import com.google.protobuf.javame.generated.JunitTestObject.Builder;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class GeneratedJavaMeObjectTest extends TestCase {
	
	private static final boolean bool = true;
	private static final ByteString bytes = ByteString.copyFromUtf8("testing");
	private static final float floatobj = 123123F;
	private static final int id = 126;
	private static final long number = 1242424444221L;
	private static final String name = "test name";
	private static final double amount = 123213.123123;
	private static final String listElement1 = "test1";
	private static final String listElement2 = "test2";
	
	private JunitTestObject testObj;
	
	/**
	 * The TestCase constructor takes two arguments: the number of tests to be
	 * run and the name of the test.
	 */
	public GeneratedJavaMeObjectTest() {
		super(3, GeneratedJavaMeObjectTest.class.getName());
	}

	public void test(int testNumber) throws IOException {
		switch (testNumber) {
		case 0:
			testWriteToNewArray();
			break;
		case 1:
			testWriteAndReadExistingArray();
			break;
		case 2:
			testParseFromByteArray();
			break;
		default:
			break;
		}
	}
	
	public void setUp() {
		final Vector stringList = new Vector();
		stringList.addElement(listElement1);
		stringList.addElement(listElement2);
		
		final JunitTestObject.Builder testObjBuilder = JunitTestObject.newBuilder();
		testObjBuilder.setBoolObj(bool);
		testObjBuilder.setBytesObject(bytes);
		testObjBuilder.setFloatObject(floatobj);
		testObjBuilder.setId(id);
		testObjBuilder.setLongNumber(number);
		testObjBuilder.setName(name);
		testObjBuilder.setAmount(amount);
		testObjBuilder.addElementAddressList(listElement1);
		testObjBuilder.setAddressList(stringList);
		
		testObj = testObjBuilder.build();
	}
	
	private void testWriteToNewArray() throws IOException {
		byte[] data = testObj.toByteArray();
		assertEquals(59, data.length);
	}
	
	private void testWriteAndReadExistingArray() throws IOException {
		byte[] data = new byte[59];
		testObj.writeTo(data);
		
		assertEquals(id, JunitTestObject.parseFrom(data).getId());
	}
	
	private void testParseFromByteArray() throws IOException {
		byte[] data = testObj.toByteArray();
		
		final JunitTestObject newTestObj = JunitTestObject.parseFrom(data);
		
		assertEquals(id, newTestObj.getId());
		assertEquals(bool, newTestObj.getBoolObj());
		assertEquals(floatobj, newTestObj.getFloatObject());
		assertEquals(number, newTestObj.getLongNumber());
		assertEquals(listElement1, newTestObj.getAddressList().elementAt(0));
		assertEquals(listElement2, newTestObj.getAddressList().elementAt(1));
		assertEquals(bytes.toStringUtf8(), newTestObj.getBytesObject().toStringUtf8());
		assertEquals(name, newTestObj.getName());
	}
}
