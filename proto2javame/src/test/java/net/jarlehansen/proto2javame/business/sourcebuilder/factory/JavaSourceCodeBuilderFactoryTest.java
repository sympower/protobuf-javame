package net.jarlehansen.proto2javame.business.sourcebuilder.factory;

import net.jarlehansen.proto2javame.business.sourcebuilder.builder.InternalClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.main.MainClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.privateandprotectedmethods.PrivateAndProtectedMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods.PublicMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods.StaticMethodsBuilder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class JavaSourceCodeBuilderFactoryTest {

	@Test
	public void testCreateInternalClassBuilder() {
		assertThat(JavaSourceCodeBuilderFactory.createInternalClassBuilder(), is(InternalClassBuilder.class));
	}

	@Test
	public void testCreateMainClassBuilder() {
		assertThat(JavaSourceCodeBuilderFactory.createMainClassBuilder(), is(MainClassBuilder.class));
	}

	@Test
	public void testCreatePrivateMethodsBuilder() {
		assertThat(JavaSourceCodeBuilderFactory.createPrivateMethodsBuilder(), is(PrivateAndProtectedMethodsBuilder.class));
	}

	@Test
	public void testCreatePublicMethodsBuilder() {
		assertThat(JavaSourceCodeBuilderFactory.createPublicMethodsBuilder(), is(PublicMethodsBuilder.class));
	}

	@Test
	public void testCreateStaticMethodsBuilder() {
		assertThat(JavaSourceCodeBuilderFactory.createStaticMethodsBuilder(), is(StaticMethodsBuilder.class));
	}
}
