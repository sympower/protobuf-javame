package net.jarlehansen.proto2javame.business.sourcebuilder.builder;

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
public class InternalClassBuilderImplTest {
	private InternalClassBuilder internalClassBuilder;

	@Before
	public void setUp() {
		internalClassBuilder = new InternalClassBuilderImpl();
	}

	@Test
	public void testCreateInternalClass() {
		assertThat(internalClassBuilder.createInternalClass(TestConstants.PROTO_CLASS_NAME, TestObjectFactory
				.createTestProtoFileInput()), is(StringBuilder.class));
	}

	@Test
	public void testCreateInteralClassAssertSource() {
		final StringBuilder builder = internalClassBuilder.createInternalClass(TestConstants.PROTO_CLASS_NAME,
				TestObjectFactory.createTestProtoFileInput());

		assertTrue(builder.length() > 0);
	}
}
