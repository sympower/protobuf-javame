package net.jarlehansen.proto2javame.io.protoinput.factory;

import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.protoinput.ProtoParser;
import net.jarlehansen.proto2javame.io.protoinput.enums.EnumParser;
import net.jarlehansen.proto2javame.io.protoinput.enums.EnumValueParser;
import net.jarlehansen.proto2javame.io.protoinput.fields.FieldParser;
import net.jarlehansen.proto2javame.io.protoinput.message.MessageParser;
import net.jarlehansen.proto2javame.io.protoinput.options.OptionParser;
import net.jarlehansen.proto2javame.testutils.TestObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 2, 2010
 * Time: 12:48:21 PM
 */
public class ProtoParserFactoryImplTest {
    private ProtoParserFactoryImpl protoParserFactory;

    @Before
    public void setup() {
        protoParserFactory = TestObjectFactory.createProtoParserFactory();
    }

    @Test(expected = ProtoFileValidationException.class)
    public void testGetProtoParserInvalidPattern() {
        protoParserFactory.getProtoParser(ProtoPatternConstants.COMMON_INVALID_VALUE);
    }

    @Test
    public void testGetProtoParserPackagePattern() {
        ProtoParser protoParser = protoParserFactory.getProtoParser(ProtoPatternConstants.PACKAGE_PATTERN);
        assertEquals(ProtoParserFactoryImpl.NULL_PARSER, protoParser);
    }

    @Test
    public void testGetProtoParserJavaPackagePattern() {
        ProtoParser protoParser = protoParserFactory.getProtoParser(ProtoPatternConstants.JAVA_PACKAGE_PATTERN);
        assertTrue(protoParser instanceof OptionParser);
    }

    @Test
    public void testGetProtoParserJavaOuterClassnamePattern() {
        ProtoParser protoParser = protoParserFactory.getProtoParser(ProtoPatternConstants.JAVA_OUTER_CLASSNAME_PATTERN);
        assertEquals(ProtoParserFactoryImpl.NULL_PARSER, protoParser);
    }

    @Test
    public void testGetProtoParserMessageStartPattern() {
        ProtoParser protoParser = protoParserFactory.getProtoParser(ProtoPatternConstants.MESSAGE_START_PATTERN);
        assertTrue(protoParser instanceof MessageParser);
    }

    @Test
    public void testGetProtoParserMessageFieldPattern() {
        protoParserFactory.getProtoParser(ProtoPatternConstants.MESSAGE_START_PATTERN);
        ProtoParser protoParser = protoParserFactory.getProtoParser(ProtoPatternConstants.MESSAGE_FIELD_PATTERN);
        assertTrue(protoParser instanceof FieldParser);
    }

    @Test(expected = ProtoFileValidationException.class)
    public void testGetProtoParserMessageFieldPatternInvalidOrder() {
        protoParserFactory.getProtoParser(ProtoPatternConstants.MESSAGE_FIELD_PATTERN);
    }

    @Test
    public void testGetProtoParserEnumStartPattern() {
        ProtoParser protoParser = protoParserFactory.getProtoParser(ProtoPatternConstants.ENUM_START_PATTERN);
        assertTrue(protoParser instanceof EnumParser);
    }

    @Test(expected = ProtoFileValidationException.class)
    public void testGetProtoParserEnumValuePatternInvalidOrder() {
        protoParserFactory.getProtoParser(ProtoPatternConstants.ENUM_VALUE_PATTERN);
    }

    @Test
    public void testGetProtoParserEnumValuePattern() {
        protoParserFactory.getProtoParser(ProtoPatternConstants.ENUM_START_PATTERN);
        ProtoParser protoParser = protoParserFactory.getProtoParser(ProtoPatternConstants.ENUM_VALUE_PATTERN);
        assertTrue(protoParser instanceof EnumValueParser);
    }

    @Test(expected = ProtoFileValidationException.class)
    public void testGetProtoParserEndPatternInvalidOrder() {
        protoParserFactory.getProtoParser(ProtoPatternConstants.COMMON_END_PATTERN);
    }

    @Test
    public void testGetProtoParserEnumEndPattern() {
        protoParserFactory.getProtoParser(ProtoPatternConstants.ENUM_START_PATTERN);
        protoParserFactory.getProtoParser(ProtoPatternConstants.ENUM_VALUE_PATTERN);

        ProtoParser protoParser = protoParserFactory.getProtoParser(ProtoPatternConstants.COMMON_END_PATTERN);
        assertEquals(ProtoParserFactoryImpl.NULL_PARSER, protoParser);
    }

    @Test
    public void testGetProtoParserMessageEndPattern() {
        protoParserFactory.getProtoParser(ProtoPatternConstants.MESSAGE_START_PATTERN);
        protoParserFactory.getProtoParser(ProtoPatternConstants.MESSAGE_FIELD_PATTERN);

        protoParserFactory.getProtoParser(ProtoPatternConstants.COMMON_END_PATTERN);
        assertTrue(protoParserFactory.getHasMessageEnd());
    }
}
