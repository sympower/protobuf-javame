package net.jarlehansen.proto2javame.business.generator.factory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilder;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilder;
import net.jarlehansen.proto2javame.io.sourceoutput.SourceFileWriter;

import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class Proto2JavaFactoryTest {

	@Test
	public void testCreateProtoObjectBuilder() {
		assertThat(Proto2JavaFactory.createProtoObjectBuilder(), is(ProtoObjectBuilder.class));
	}

	@Test
	public void testCreateJavaSourceCodeBuilder() {
		assertThat(Proto2JavaFactory.createJavaSourceCodeBuilder(), is(JavaSourceCodeBuilder.class));
	}

	@Test
	public void testCreateSourceFileWriter() {
		assertThat(Proto2JavaFactory.createSourceFileWriter(), is(SourceFileWriter.class));
	}
}
