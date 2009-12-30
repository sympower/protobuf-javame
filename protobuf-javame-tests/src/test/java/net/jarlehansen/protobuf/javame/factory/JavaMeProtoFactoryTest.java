package net.jarlehansen.protobuf.javame.factory;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

import java.io.InputStream;

import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.output.OutputWriter;

import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class JavaMeProtoFactoryTest {

	@Test
	public void testCreateInputUtilInputStream() {
		assertThat(JavaMeProtoFactory.createInputUtil(mock(InputStream.class), DefaultUnknownTagHandlerImpl.newInstance()),
				is(InputReader.class));
	}
	
	@Test
	public void testCreateInputUtilByteArray() {
		assertThat(JavaMeProtoFactory.createInputUtil(new byte[100], DefaultUnknownTagHandlerImpl.newInstance()),
				is(InputReader.class));
	}

	@Test
	public void testCreateOutputUtilByteArray() {
		assertThat(JavaMeProtoFactory.createOutputUtil(new byte[100]), is(OutputWriter.class));
	}
}
