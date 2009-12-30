package net.jarlehansen.protobuf.javame.input.taghandler;

import org.junit.Test;

public class DefaultUnknownTagHandlerImplTest {
	
	@Test
	public void testUnknownTag() {
		DefaultUnknownTagHandlerImpl.newInstance().unknownTag("test");
	}
}
