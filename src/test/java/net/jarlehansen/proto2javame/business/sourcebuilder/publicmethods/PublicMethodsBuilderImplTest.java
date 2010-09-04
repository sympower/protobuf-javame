package net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods;

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
public class PublicMethodsBuilderImplTest {
	private PublicMethodsBuilder publicMethodsBuilder;

	@Before
	public void setUp() {
		publicMethodsBuilder = new PublicMethodsBuilderImpl();
	}

	@Test
	public void testCreatePublicMethods() {
		assertThat(publicMethodsBuilder.createPublicMethods(TestConstants.PROTO_CLASS_NAME, TestObjectFactory
				.createTestProtoFileInput()), is(StringBuilder.class));
	}

	@Test
	public void testCreatePublicMethodsAssertSource() {
		final StringBuilder builder = publicMethodsBuilder.createPublicMethods(TestConstants.PROTO_CLASS_NAME,
				TestObjectFactory.createTestProtoFileInput());

		assertTrue(builder.length() > 0);
	}
}
