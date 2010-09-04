package net.jarlehansen.proto2javame.business.sourcebuilder.main;

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
public class MainClassBuilderImplTest {
	private MainClassBuilder mainClassBuilder;

	@Before
	public void setUp() {
		mainClassBuilder = new MainClassBuilderImpl();
	}

	@Test
	public void testCreateMainClass() {
		assertThat(mainClassBuilder.createMainClass(TestConstants.PROTO_CLASS_NAME, TestObjectFactory
				.createTestProtoFileInput()), is(StringBuilder.class));
	}

	@Test
	public void testCreateMainClassAssertSource() {
		final StringBuilder builder = mainClassBuilder.createMainClass(TestConstants.PROTO_CLASS_NAME,
				TestObjectFactory.createTestProtoFileInput());

		assertTrue(builder.length() > 0);
	}
}
