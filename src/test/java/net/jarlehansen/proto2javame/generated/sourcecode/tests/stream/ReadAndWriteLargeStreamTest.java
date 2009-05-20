package net.jarlehansen.proto2javame.generated.sourcecode.tests.stream;

import net.jarlehansen.proto2javame.generated.sourcecode.tests.LargeObjectConstants;
import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestLargeObject;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: May 14, 2009
 */
public class ReadAndWriteLargeStreamTest {
    private File generatedTxtFile;


    @Before
    public void setUp() {
        generatedTxtFile = new File(TestConstants.LARGE_GENERATED_TXT_FILE);

        if (generatedTxtFile.isFile()) {
            final boolean deleted = generatedTxtFile.delete();
            System.out.println(this.getClass().getName() + " - The file " + TestConstants.LARGE_GENERATED_TXT_FILE + " was delete? " + deleted);
        }
    }

    @Test
    public void testWriteToOutputStreamValidRequiredInput() throws IOException {
        FileOutputStream output = null;

        try {
            output = new FileOutputStream(generatedTxtFile);
            final JunitTestLargeObject testObj = LargeObjectConstants.createTestObjOnlyRequiredFields();
            testObj.writeTo(output);

            output.close();

            assertTrue(generatedTxtFile.isFile());
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }


    }

    @Test
    public void testWriteToOutputStreamValidRequiredAndOptionalInput() throws IOException {
        FileOutputStream output = null;

        try {
            output = new FileOutputStream(generatedTxtFile);

            final JunitTestLargeObject testObj = LargeObjectConstants.createTestObjRequiredAndOptionalFields();
            testObj.writeTo(output);

            output.close();

            assertTrue(generatedTxtFile.isFile());
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }

    }

    @Test
    public void testWriteToOutputStreamValidInputAllFields() throws IOException {
        FileOutputStream output = null;

        try {
            output = new FileOutputStream(generatedTxtFile);

            final JunitTestLargeObject testObj = LargeObjectConstants.createTestObjAllFields();
            testObj.writeTo(output);

            output.close();

            assertTrue(generatedTxtFile.isFile());
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    @Test
    public void testParseFromInputStreamValidRequiredInput() throws IOException {
        final InputStream input = new FileInputStream(new File(TestConstants.LARGE_CONSTANT_TXT_FILE_REQUIRED));

        final JunitTestLargeObject testObj = JunitTestLargeObject.parseFrom(input);
        input.close();

        System.out.println("ParseFrom TestObj required fields: " + testObj.toString());

        assertTrue(LargeObjectConstants.equalsTestObjRequired(testObj));
    }

    @Test
    public void testParseFromInputStreamValidRequiredAndOptionalInput() throws IOException {
        final InputStream input = new FileInputStream(new File(TestConstants.LARGE_CONSTANT_TXT_FILE_REQUIRED_OPTIONAL));

        final JunitTestLargeObject testObj = JunitTestLargeObject.parseFrom(input);
        input.close();

        System.out.println("ParseFrom TestObj required and optional fields: " + testObj.toString());

        assertTrue(LargeObjectConstants.equalsTestObjRequiredAndOptional(testObj));
    }

    @Test
    public void testParseFromInputStreamValidInputAllFields() throws IOException {
        final InputStream input = new FileInputStream(new File(TestConstants.LARGE_CONSTANT_TXT_FILE_ALL));

        final JunitTestLargeObject testObj = JunitTestLargeObject.parseFrom(input);
        input.close();

        System.out.println("ParseFrom TestObj all fields: " + testObj.toString());

        assertTrue(LargeObjectConstants.equalsTestObjAllFields(testObj));
    }
}
