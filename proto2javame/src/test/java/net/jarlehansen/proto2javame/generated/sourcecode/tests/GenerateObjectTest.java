package net.jarlehansen.proto2javame.generated.sourcecode.tests;

import static org.junit.Assert.assertTrue;
import net.jarlehansen.proto2javame.Proto2JavaMe;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import org.junit.Test;

import java.io.File;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class GenerateObjectTest {
	private static final String JAVA_OUT = "--java_out=src/test/generated";
	private static final String PROTO_FILE = "src/test/resources/integration-test/JunitTestMain.proto";
	
	@Test
	public void testGenerateObject() {
		Proto2JavaMe.main(new String[] { JAVA_OUT, PROTO_FILE });
        assertTrue(new File(TestConstants.GENERATED_TEST_FILE).isFile());
	}
}
