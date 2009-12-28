package net.jarlehansen.proto2javame.io.protoinput.fields;

import net.jarlehansen.proto2javame.domain.proto.FieldData;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import org.junit.Before;
import org.junit.Test;

import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class FieldParserImplTest {
	private FieldParserImpl fieldParser;

	@Before
	public void setUp() {
		fieldParser = FieldParserImpl.newInstance();
	}

	@Test
	public void testParseField() {
		final StringTokenizer stringTokens = new StringTokenizer(TestConstants.VALID_PROTO_FIELD, "[ ]+");
		final FieldData field = fieldParser.parseField(stringTokens);

		assertEquals(TestConstants.VALID_PROTO_ID, field.getId());
		assertEquals(TestConstants.VALID_PROTO_NAME, field.getName());
		assertEquals(TestConstants.VALID_PROTO_SCOPE, field.getScope());
		assertEquals(TestConstants.VALID_PROTO_TYPE, field.getType());
	}

	@Test
	public void testGetScopeTagValidInput() {
		assertEquals(TestConstants.VALID_PROTO_SCOPE, fieldParser.getScopeTag(TestConstants.VALID_PROTO_SCOPE
				.getScopeValue()));
	}

	@Test(expected = ProtoFileValidationException.class)
	public void testGetScopeTagInvalidInput() {
		fieldParser.getScopeTag("invalid input");
	}

	@Test
	public void testgetTypeTagValidInput() {
		assertEquals(TestConstants.VALID_PROTO_TYPE, fieldParser.getTypeTag(TestConstants.VALID_PROTO_TYPE
				.getProtoType()));
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testgetTypeTagInvalidInput() {
		fieldParser.getTypeTag("invalid input");
	}
	
	@Test
	public void testGetIdTagValidInput() {
		final StringTokenizer stringTokens = new StringTokenizer(TestConstants.VALID_PROTO_ID_TAG, "[ ]+");
		assertEquals(TestConstants.VALID_PROTO_ID, fieldParser.getIdTag(stringTokens));
	}
	
	@Test
	public void testGetIdTagValidInput2() {
		final StringTokenizer stringTokens = new StringTokenizer(TestConstants.VALID_PROTO_ID_TAG2, "[ ]+");
		assertEquals(TestConstants.VALID_PROTO_ID, fieldParser.getIdTag(stringTokens));
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testGetIdTagInvalidInputTag() {
		final StringTokenizer stringTokens = new StringTokenizer("invalid input tag", "[ ]+");
		fieldParser.getIdTag(stringTokens);
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testGetIdTagDuplicateIdNumber() {
		StringTokenizer stringTokens = new StringTokenizer(TestConstants.INVALID_PROTO_ID_TAG, "[ ]+");
		fieldParser.getIdTag(stringTokens);

        stringTokens = new StringTokenizer(TestConstants.INVALID_PROTO_ID_TAG, "[ ]+");
		fieldParser.getIdTag(stringTokens);
	}
}
