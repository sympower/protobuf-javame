package net.jarlehansen.proto2javame.invalid.proto;

import net.jarlehansen.proto2javame.business.generator.CodeGenerator;
import net.jarlehansen.proto2javame.business.generator.CodeGeneratorImpl;
import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.io.sourceoutput.SourceFileWriterImpl;
import net.jarlehansen.proto2javame.testutils.TestObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class InvalidProtoFileInputTest {
    private CodeGenerator codeGen;

    @Before
    public void setUp() {
        codeGen = new CodeGeneratorImpl(TestObjectFactory.createProtoObjectBuilder(), TestObjectFactory.createJavaSourceCodeBuilder(), new SourceFileWriterImpl());
    }

    @Test
    public void testInvalidPackage() {
        try {
            codeGen.generateJavaSourceCode(InvalidProtoFilesConstants.VALID_OUTPUT_FOLDER, InvalidProtoFilesConstants.INVALID_PACKAGE);
            fail("Expected " + ProtoFileValidationException.class.getName());
        } catch (ProtoFileValidationException pfve) {
            assertEquals(InvalidProtoFilesConstants.INVALID_PACKAGE_LINE_NUM, pfve.getLineNumber());
        }
    }

    @Test
    public void testInvalidMessageStart() {
        try {
            codeGen.generateJavaSourceCode(InvalidProtoFilesConstants.VALID_OUTPUT_FOLDER, InvalidProtoFilesConstants.INVALID_MESSAGE_START);
            fail("Expected " + ProtoFileValidationException.class.getName());
        } catch (ProtoFileValidationException pfve) {
            assertEquals(InvalidProtoFilesConstants.INVALID_MESSAGE_START_LINE_NUM, pfve.getLineNumber());
        }
    }

    @Test
    public void testInvalidField() {
        try {
            codeGen.generateJavaSourceCode(InvalidProtoFilesConstants.VALID_OUTPUT_FOLDER, InvalidProtoFilesConstants.INVALID_FIELD);
            fail("Expected " + ProtoFileValidationException.class.getName());
        } catch (ProtoFileValidationException pfve) {
            assertEquals(InvalidProtoFilesConstants.INVALID_FIELD_LINE_NUM, pfve.getLineNumber());
        }
    }

    @Test
    public void testInvalidMessageEnd() {
        try {
            codeGen.generateJavaSourceCode(InvalidProtoFilesConstants.VALID_OUTPUT_FOLDER, InvalidProtoFilesConstants.INVALID_MESSAGE_END);
            fail("Expected " + ProtoFileValidationException.class.getName());
        } catch (ProtoFileValidationException pfve) {
            assertEquals(InvalidProtoFilesConstants.INVALID_MESSAGE_END_LINE_NUM, pfve.getLineNumber());
        }
    }

    @Test(expected = ProtoFileValidationException.class)
    public void testInvalidFieldId() {
        codeGen.generateJavaSourceCode(InvalidProtoFilesConstants.VALID_OUTPUT_FOLDER, InvalidProtoFilesConstants.INVALID_FIELD_ID);
    }

    @Test(expected = ProtoFileValidationException.class)
    public void testInvalidEnumId() {
        codeGen.generateJavaSourceCode(InvalidProtoFilesConstants.VALID_OUTPUT_FOLDER, InvalidProtoFilesConstants.INVALID_ENUM_ID);
    }
}
