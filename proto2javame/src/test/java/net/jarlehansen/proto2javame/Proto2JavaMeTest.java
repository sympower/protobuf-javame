package net.jarlehansen.proto2javame;

import net.jarlehansen.proto2javame.testutils.TestConstants;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class Proto2JavaMeTest {
    private File generatedFile;
    private File generatedFileWithComments;

    @Before
    public void setUp() {
        generatedFile = new File(TestConstants.GENERATED_FILE);
        generatedFileWithComments = new File(TestConstants.GENERATED_FILE_COMMENTS);

        if (generatedFile.isFile()) {
            final boolean deleted = generatedFile.delete();

            if (deleted) {
                System.out.println(this.getClass().getName() + " - The file " + TestConstants.GENERATED_FILE + " was delete? " + deleted);
            }
        }

        if(generatedFileWithComments.isFile()) {
            final boolean deleted = generatedFileWithComments.delete();

            if (deleted) {
                System.out.println(this.getClass().getName() + " - The file " + TestConstants.GENERATED_FILE_COMMENTS + " was delete? " + deleted);
            }
        }
    }

    @Test
    public void testMainValidInput() {
        Proto2JavaMe.main(new String[]{TestConstants.JAVA_OUT, TestConstants.PROTO_FILE});
        assertTrue(generatedFile.isFile());
    }

    @Test
    public void testMainInvalidInput() {
        Proto2JavaMe.main(new String[]{""});
        assertFalse(generatedFile.isFile());
    }

    @Test
    public void testGenerateFileWithComments() {
        Proto2JavaMe.main(new String[]{TestConstants.JAVA_OUT, TestConstants.PROTO_FILE_COMMENTS});
        assertTrue(generatedFileWithComments.isFile());
    }
}
