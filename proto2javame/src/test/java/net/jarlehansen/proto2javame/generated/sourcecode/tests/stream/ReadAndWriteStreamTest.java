package net.jarlehansen.proto2javame.generated.sourcecode.tests.stream;

import net.jarlehansen.proto2javame.testutils.TestConstants;
import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestMainObject;
import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class ReadAndWriteStreamTest {
    
    private File generatedTxtFile;

    @Before
    public void setUp() {
        generatedTxtFile = new File(TestConstants.GENERATED_TXT_FILE);

        if (generatedTxtFile.isFile()) {
            final boolean deleted = generatedTxtFile.delete();
            System.out.println(this.getClass().getName() + " - The file " + TestConstants.GENERATED_TXT_FILE + " was delete? " + deleted);
        }
    }

    @Test
    public void testWriteToOutputStreamValidInput() throws IOException {
        FileOutputStream output = null;

        try {
            output = new FileOutputStream(generatedTxtFile);

            final JunitTestMainObject testObj = JunitTestMainObject.newBuilder().setId(123).build();
            testObj.writeTo(output);

            assertTrue(generatedTxtFile.isFile());
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }

    }

    @Test(expected = UninitializedMessageException.class)
    public void testWriteToOutputStreamInvalidInput() throws IOException {
        FileOutputStream output = null;

        try {
            output = new FileOutputStream(generatedTxtFile);

            final JunitTestMainObject testObj = JunitTestMainObject.newBuilder().build();
            testObj.writeTo(output);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    @Test
    public void testParseFromInputStreamValidInput() throws IOException {
        final InputStream input = new FileInputStream(new File(TestConstants.CONSTANT_TXT_FILE));

        final JunitTestMainObject testObj = JunitTestMainObject.parseFrom(input);
        input.close();

        assertEquals(123, testObj.getId());
    }

    @Test(expected = UninitializedMessageException.class)
    public void testParseFromInputStreamInvalidInput() throws IOException {
        final InputStream input = new FileInputStream(new File(TestConstants.EMPTY_TXT_FILE));

        JunitTestMainObject.parseFrom(input);
        input.close();
    }
}
