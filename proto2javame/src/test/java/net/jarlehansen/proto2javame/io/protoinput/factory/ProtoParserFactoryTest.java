package net.jarlehansen.proto2javame.io.protoinput.factory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import net.jarlehansen.proto2javame.io.protoinput.fields.FieldParser;
import net.jarlehansen.proto2javame.io.protoinput.message.MessageParser;
import net.jarlehansen.proto2javame.io.protoinput.options.OptionParser;

import org.junit.Test;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class ProtoParserFactoryTest {
	
	@Test
	public void testCreateFieldParser() {
		assertThat(ProtoParserFactory.createFieldParser(), is(FieldParser.class));
	}
	
	@Test
	public void testCreateMessageParser() {
		assertThat(ProtoParserFactory.createMessageParser(), is(MessageParser.class));
	}
	
	@Test
	public void testCreateOptionParser() {
		assertThat(ProtoParserFactory.createOptionParser(), is(OptionParser.class));
	}
}
