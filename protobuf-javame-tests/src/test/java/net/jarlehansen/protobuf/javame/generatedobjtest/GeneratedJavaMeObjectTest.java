package net.jarlehansen.protobuf.javame.generatedobjtest;

import java.io.IOException;
import java.util.Vector;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.generated.JunitTestObject;
import net.jarlehansen.protobuf.javame.generated.ListTestObject;


/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class GeneratedJavaMeObjectTest {
	
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

	@Before
	@SuppressWarnings("unchecked")
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
	
	@Test
	public void testWriteToNewArray() throws IOException {
		byte[] data = testObj.toByteArray();
		assertEquals(59, data.length);
	}
	
	@Test
	public void testWriteAndReadExistingArray() throws IOException {
		byte[] data = new byte[59];
		testObj.writeTo(data);
		
		assertEquals(id, JunitTestObject.parseFrom(data).getId());
	}
	
	@Test
	public void testParseFromByteArray() throws IOException {
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
	
	@Test
	public void testListObject() throws IOException {
		ListTestObject.Builder listTestObject = ListTestObject.newBuilder();
		
		listTestObject.addElementOne(123);
		listTestObject.addElementTwo(1242151251515L);
		listTestObject.addElementThree(114.124);
		listTestObject.addElementFour("test");
		listTestObject.addElementFive(ByteString.copyFromUtf8("test"));
		listTestObject.addElementSix(124124124124F);
		listTestObject.addElementSeven(true);
		
		assertEquals(37, listTestObject.build().toByteArray().length);
	}
}
