package net.jarlehansen.proto2javame.business.sourcebuilder;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import net.jarlehansen.proto2javame.testutils.TestObjectFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class JavaSourceCodeBuilderImplTest {
	private JavaSourceCodeBuilder javaSourceBuilder;

	@Before
	public void setUp() {
		javaSourceBuilder = JavaSourceCodeBuilderImpl.newInstance();
	}

	@Test
	public void testCreateJavaSourceCode() {
		assertThat(javaSourceBuilder.createJavaSourceCode(TestObjectFactory.createTestProtoFileInput()),
				is(JavaFileOutput.class));
	}

	@Test
	public void testCreateJavaSourceCodeAssertClassName() {
		final JavaFileOutput javaOutput = javaSourceBuilder.createJavaSourceCode(TestObjectFactory
				.createTestProtoFileInput());
		assertEquals(TestConstants.PROTO_CLASS_NAME, javaOutput.getClassName());
	}

	@Test
	public void testCreateJavaSourceCodeAssertSource() {
		final JavaFileOutput javaOutput = javaSourceBuilder.createJavaSourceCode(TestObjectFactory
				.createTestProtoFileInput());
		assertTrue(javaOutput.getCompleteSource().length() > 0);
	}
}
