package net.jarlehansen.proto2javame.io.protoinput.options;

import static org.junit.Assert.assertEquals;

import java.util.StringTokenizer;

import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.testutils.TestConstants;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class OptionParserImplTest {
	private OptionParser optionParser;

	@Before
	public void setUp() {
		optionParser = OptionParserImpl.newInstance();
	}

	@Test
	public void testParseJavaPackageValidInput() {
		final StringTokenizer stringTokens = new StringTokenizer(TestConstants.VALID_PROTO_JAVA_PACKAGE_TAG, "[ ]+");
		assertEquals(TestConstants.VALID_PROTO_JAVA_PACKAGE, optionParser.parseJavaPackage(stringTokens));
	}

	@Test
	public void testParseJavaPackageValidInput2() {
		final StringTokenizer stringTokens = new StringTokenizer(TestConstants.VALID_PROTO_JAVA_PACKAGE_TAG2, "[ ]+");
		assertEquals(TestConstants.VALID_PROTO_JAVA_PACKAGE, optionParser.parseJavaPackage(stringTokens));
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testParseJavaPackageInvalidInput() {
		final StringTokenizer stringTokens = new StringTokenizer(TestConstants.INVALID_PROTO_JAVA_PACKAGE_TAG, "[ ]+");
		assertEquals(TestConstants.VALID_PROTO_JAVA_PACKAGE, optionParser.parseJavaPackage(stringTokens));
	}
}
