package net.jarlehansen.proto2javame.business.sourcebuilder.privateandprotectedmethods;

import net.jarlehansen.proto2javame.testutils.TestConstants;
import net.jarlehansen.proto2javame.testutils.TestObjectFactory;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class PrivateMethodsBuilderImplTest {
	private PrivateAndProtectedMethodsBuilder privateClassBuilder;

	@Before
	public void setUp() {
		privateClassBuilder = PrivateAndProtectedMethodsBuilderImpl.newInstance();
	}

	@Test
	public void testCreatePrivateMethods() {
		assertThat(privateClassBuilder.createPrivateAndProtectedMethods(TestConstants.PROTO_CLASS_NAME, TestObjectFactory
				.createTestProtoFileInput()), is(StringBuilder.class));
	}

	@Test
	public void testCreatePrivateMethodsAssertSource() {
		final StringBuilder builder = privateClassBuilder.createPrivateAndProtectedMethods(TestConstants.PROTO_CLASS_NAME,
				TestObjectFactory.createTestProtoFileInput());

		assertTrue(builder.length() > 0);
	}
}
