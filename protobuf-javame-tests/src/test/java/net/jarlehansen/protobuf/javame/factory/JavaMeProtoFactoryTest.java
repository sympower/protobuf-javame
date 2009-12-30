package net.jarlehansen.protobuf.javame.factory;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;


import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.output.OutputWriter;


/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class JavaMeProtoFactoryTest {

	@Test
	public void testCreateInputUtilByteArray() {
		assertTrue(JavaMeProtoFactory.createInputUtil(new byte[100], DefaultUnknownTagHandlerImpl.newInstance()) instanceof InputReader);
	}
	
	@Test
	public void testCreateOutputUtilByteArray() {
		assertTrue(JavaMeProtoFactory.createOutputUtil(new byte[100]) instanceof OutputWriter);
	}
}
