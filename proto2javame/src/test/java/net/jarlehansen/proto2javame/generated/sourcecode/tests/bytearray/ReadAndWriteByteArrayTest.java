package net.jarlehansen.proto2javame.generated.sourcecode.tests.bytearray;

import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestMainObject;
import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import net.jarlehansen.protobuf.javame.original.input.InvalidProtocolBufferException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class ReadAndWriteByteArrayTest {

    @Test
    public void testWriteToNewByteArrayValidInput() throws IOException {
        final JunitTestMainObject testObj = JunitTestMainObject.newBuilder().setId(123).build();
        final byte[] data = testObj.toByteArray();
        assertEquals(2, data.length);
    }

    @Test(expected = UninitializedMessageException.class)
    public void testWriteToNewByteArrayInvalidInput() throws IOException {
        // Does not add the mandatory id parameter
        final JunitTestMainObject testObj = JunitTestMainObject.newBuilder().build();
        testObj.toByteArray();
    }

    @Test
    public void testWriteToByteArray() throws IOException {
        final JunitTestMainObject testObj = JunitTestMainObject.newBuilder().setId(123).build();
        final byte[] data = new byte[2];
        testObj.writeTo(data);

        assertEquals(123, JunitTestMainObject.parseFrom(data).getId());
    }

    @Test(expected = InvalidProtocolBufferException.class)
    public void testParseFromByteArray() throws IOException {
        final byte[] data = new byte[100];
        JunitTestMainObject.parseFrom(data);
    }
}
