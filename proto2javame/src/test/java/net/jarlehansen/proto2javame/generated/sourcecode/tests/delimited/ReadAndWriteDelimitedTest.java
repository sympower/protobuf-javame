package net.jarlehansen.proto2javame.generated.sourcecode.tests.delimited;

import net.jarlehansen.proto2javame.generated.sourcecode.tests.LargeObjectConstants;
import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestLargeObject;
import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Dec 28, 2009
 * Time: 6:17:55 PM
 */
public class ReadAndWriteDelimitedTest {
    private OutputStream out;
    private InputStream in;
    private JunitTestLargeObject testObj;

    @Before
    public void setup() throws IOException {
        out = mock(OutputStream.class);
        in = mock(InputStream.class);
        testObj = LargeObjectConstants.allFields;
    }

    @Test
    public void testWriteDelimitedToAllFields() throws IOException {
        testObj.writeDelimitedTo(out);

        verify(out).write(any(byte[].class));
        verify(out).flush();
        verifyNoMoreInteractions(out);
    }

    @Test(expected = UninitializedMessageException.class)
    public void testParseDelimitedToInvalidStream() throws IOException {
        testObj = JunitTestLargeObject.parseDelimitedFrom(in);
    }
}
