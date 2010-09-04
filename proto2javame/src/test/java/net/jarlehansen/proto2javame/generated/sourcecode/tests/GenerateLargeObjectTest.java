package net.jarlehansen.proto2javame.generated.sourcecode.tests;

import net.jarlehansen.proto2javame.Proto2JavaMe;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.io.File;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: May 14, 2009
 */
public class GenerateLargeObjectTest {

    private static final String JAVA_OUT = "--java_out=src/test/generated";
    private static final String PROTO_FILE = "src/test/resources/JUnitTestLarge.proto";

    @Test
    public void testGenerateObject() {
        Proto2JavaMe.main(new String[]{JAVA_OUT, PROTO_FILE});
        assertTrue(new File(TestConstants.LARGE_GENERATED_TEST_FILE).isFile());
    }

}
