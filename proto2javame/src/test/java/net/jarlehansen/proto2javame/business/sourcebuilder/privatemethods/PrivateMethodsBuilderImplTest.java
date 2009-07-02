package net.jarlehansen.proto2javame.business.sourcebuilder.privatemethods;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import net.jarlehansen.proto2javame.testutils.TestObjectFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class PrivateMethodsBuilderImplTest {
	private PrivateMethodsBuilder privateClassBuilder;

	@Before
	public void setUp() {
		privateClassBuilder = PrivateMethodsBuilderImpl.newInstance();
	}

	@Test
	public void testCreatePrivateMethods() {
		assertThat(privateClassBuilder.createPrivateMethods(TestConstants.PROTO_CLASS_NAME, TestObjectFactory
				.createTestProtoFileInput()), is(StringBuilder.class));
	}

	@Test
	public void testCreatePrivateMethodsAssertSource() {
		final StringBuilder builder = privateClassBuilder.createPrivateMethods(TestConstants.PROTO_CLASS_NAME,
				TestObjectFactory.createTestProtoFileInput());

		assertTrue(builder.length() > 0);
	}
}
