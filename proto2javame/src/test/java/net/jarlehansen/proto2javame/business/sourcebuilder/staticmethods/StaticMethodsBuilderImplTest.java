package net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods;

import net.jarlehansen.proto2javame.testutils.TestConstants;
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
public class StaticMethodsBuilderImplTest {
	private StaticMethodsBuilder staticMethodsBuilder;

	@Before
	public void setUp() {
		staticMethodsBuilder = new StaticMethodsBuilderImpl();
	}

	@Test
	public void testCreateStaticMethods() {
		assertThat(staticMethodsBuilder.createStaticMethods(TestConstants.PROTO_CLASS_NAME), is(StringBuilder.class));
	}

	@Test
	public void testCreateStaticMethodsAssertSource() {
		final StringBuilder builder = staticMethodsBuilder.createStaticMethods(TestConstants.PROTO_CLASS_NAME);

		assertTrue(builder.length() > 0);
	}
}
