package net.jarlehansen.proto2javame.io.protoinput;

import static org.junit.Assert.assertEquals;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.protoinput.fields.FieldParser;
import net.jarlehansen.proto2javame.io.protoinput.message.MessageParser;
import net.jarlehansen.proto2javame.io.protoinput.options.OptionParser;
import net.jarlehansen.proto2javame.testutils.TestConstants;

import net.jarlehansen.proto2javame.testutils.TestObjectFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class ProtoObjectBuilderImplTest {
	private ProtoObjectBuilderImpl protoBuilder;

	@Before
	public void setUp() {
		protoBuilder = TestObjectFactory.createProtoObjectBuilder();
	}

	@Test
	public void testValidateAndSaveProtoData() {
		final List<ProtoFileInput> protoInputList = protoBuilder.validateAndSaveProtoData(TestConstants.PROTO_FILE);
		assertEquals(TestConstants.PROTO_CLASS_NAME, protoInputList.get(0).getProtoClassName());
		assertEquals(1, protoInputList.get(0).getFields().get(0).getId());
		assertEquals("id", protoInputList.get(0).getFields().get(0).getName());
	}
	
	@Test(expected = ProtoFileValidationException.class)
	public void testValidateAndSaveProtoDataInvalidProtoLocation() {
		protoBuilder.validateAndSaveProtoData(TestConstants.INVALID_PROTO_FILE);
	}
}
