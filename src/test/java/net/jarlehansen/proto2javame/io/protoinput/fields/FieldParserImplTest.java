package net.jarlehansen.proto2javame.io.protoinput.fields;

import net.jarlehansen.proto2javame.domain.proto.CustomType;
import net.jarlehansen.proto2javame.domain.proto.DataType;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.domain.proto.ValidTypes;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class FieldParserImplTest {
	private FieldParser fieldParser;

	@Before
	public void setUp() {
		fieldParser = new FieldParser();
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
	public void testGetTypeTagValidInput() {
        ProtoFileInput protoInput = new ProtoFileInput();
        protoInput.setEnumIfAbsent("test");
        fieldParser.protoInput = protoInput;

		assertEquals(TestConstants.VALID_PROTO_TYPE, fieldParser.getTypeTag(TestConstants.VALID_PROTO_TYPE
				.getProtoType()));
	}
	
	@Test
	public void testGetTypeTagEnumInput() {
        ProtoFileInput protoInput = new ProtoFileInput();
        protoInput.setEnumIfAbsent("Color");
        fieldParser.protoInput = protoInput;

		DataType dataType = fieldParser.getTypeTag("Color");
        assertEquals("int", dataType.getImplementationType());
	}

    @Test
    public void testGetTypeTagCustomInput() {
        DataType dataType = fieldParser.getTypeTag("Color");
        assertTrue(dataType instanceof CustomType);
        assertEquals("Color", dataType.getImplementationType());
    }
	
	@Test
	public void testGetIdTagValidInput() {
		assertEquals(TestConstants.VALID_PROTO_ID, fieldParser.getIdTag(TestConstants.VALID_PROTO_ID_TAG));
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testGetIdTagInvalidInputTag() {
		fieldParser.getIdTag("invalid input tag");
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testGetIdTagDuplicateIdNumber() {
		fieldParser.getIdTag(TestConstants.INVALID_PROTO_ID_TAG);
		fieldParser.getIdTag(TestConstants.INVALID_PROTO_ID_TAG);
	}
}
