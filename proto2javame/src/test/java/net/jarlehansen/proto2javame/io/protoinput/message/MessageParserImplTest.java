package net.jarlehansen.proto2javame.io.protoinput.message;

import static org.junit.Assert.assertEquals;

import java.util.StringTokenizer;

import net.jarlehansen.proto2javame.testutils.TestConstants;

import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class MessageParserImplTest {

	@Test
	public void testParseMessageStart() {
		final StringTokenizer stringTokens = new StringTokenizer(TestConstants.VALID_PROTO_MESSAGE_START_TAG, "[ ]+");

		final MessageParser messageParser = MessageParserImpl.newInstance();
		assertEquals(TestConstants.VALID_PROTO_MESSAGE, messageParser.parseMessageStart(stringTokens));
	}
}
