package net.jarlehansen.proto2javame.generated.sourcecode.tests.bytearray;

import net.jarlehansen.proto2javame.generated.sourcecode.tests.LargeObjectConstants;
import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestLargeObject;
import org.junit.Test;

import java.io.IOException;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Dec 28, 2009
 * Time: 5:57:24 PM
 */
public class ProtobufSerializationTest {

    @Test
    public void testProtobufSerializationAndDeserializationRequiredFields() throws IOException {
        JunitTestLargeObject testObj = LargeObjectConstants.onlyRequiredFields;
        byte[] data = testObj.toByteArray();

        JunitTestLargeObject testObj2 = JunitTestLargeObject.parseFrom(data);

        assertReflectionEquals(LargeObjectConstants.onlyRequiredFields, testObj2);
    }

    @Test
    public void testProtobufSerializationAndDeserializationRequiredAndOptionalFields() throws IOException {
        JunitTestLargeObject testObj = LargeObjectConstants.requiredAndOptionalFields;
        byte[] data = testObj.toByteArray();

        JunitTestLargeObject testObj2 = JunitTestLargeObject.parseFrom(data);

        assertReflectionEquals(LargeObjectConstants.requiredAndOptionalFields, testObj2);
    }

    @Test
    public void testProtobufSerializationAndDeserializationAllFields() throws IOException {
        JunitTestLargeObject testObj = LargeObjectConstants.allFields;
        byte[] data = testObj.toByteArray();

        JunitTestLargeObject testObj2 = JunitTestLargeObject.parseFrom(data);

        assertReflectionEquals(LargeObjectConstants.allFields, testObj2);
    }
}
