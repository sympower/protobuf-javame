package net.jarlehansen.proto2javame.io.protoinput;

import static org.junit.Assert.assertEquals;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.testutils.TestConstants;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class ProtoObjectBuilderImplTest {
	private ProtoObjectBuilderImpl protoBuilder;

	@Before
	public void setUp() {
		protoBuilder = ProtoObjectBuilderImpl.newInstance();
	}

	@Test
	public void testValidateAndSaveProtoData() {
		final ProtoFileInput protoInput = protoBuilder.validateAndSaveProtoData(TestConstants.PROTO_FILE);
		assertEquals(TestConstants.PROTO_CLASS_NAME, protoInput.getProtoClassName());
		assertEquals(1, protoInput.getFields().get(0).getId());
		assertEquals("id", protoInput.getFields().get(0).getName());
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testValidateAndSaveProtoDataInvalidProtoLocation() {
		protoBuilder.validateAndSaveProtoData(TestConstants.INVALID_PROTO_FILE);
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testCheckProtoMessageParts() {
		protoBuilder.checkProtoMessageParts();
	}
}
