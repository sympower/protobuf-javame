package net.jarlehansen.proto2javame.generated.sourcecode.tests.bytearray;

import net.jarlehansen.proto2javame.generated.sourcecode.tests.LargeObjectConstants;
import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestLargeObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: May 14, 2009
 */
public class ReadAndWriteLargeByteArrayTest {

    @Test
    public void testWriteToNewByteArrayValidRequiredInput() throws IOException {
        final JunitTestLargeObject testObj = LargeObjectConstants.createTestObjOnlyRequiredFields();
        final byte[] data = testObj.toByteArray();

        System.out.println("TestObj required fields: " + testObj.toString());

        assertEquals(61, data.length);
    }

    @Test
    public void testWriteToNewByteArrayValidRequiredAndOptionalInput() throws IOException {
        final JunitTestLargeObject testObj = LargeObjectConstants.createTestObjRequiredAndOptionalFields();
        final byte[] data = testObj.toByteArray();

        System.out.println("TestObj required and optional fields: " + testObj.toString());

        assertEquals(119, data.length);
    }

    @Test
    public void testWriteToNewByteArrayValidInputAllFields() throws IOException {
        final JunitTestLargeObject testObj = LargeObjectConstants.createTestObjAllFields();
        final byte[] data = testObj.toByteArray();

        System.out.println("TestObj required and optional fields: " + testObj.toString());

        assertEquals(257, data.length);
    }

    @Test
    public void testWriteToByteArrayValidInputAllFields() throws IOException {
        final JunitTestLargeObject testObj = LargeObjectConstants.createTestObjAllFields();
        final byte[] data = new byte[257];
        testObj.writeTo(data);

        System.out.println("TestObj required and optional fields: " + testObj.toString());

        assertEquals(257, data.length);
    }
}
