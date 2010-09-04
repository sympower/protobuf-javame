package net.jarlehansen.proto2javame.business.generator.factory;

import static org.junit.Assert.assertEquals;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.io.exception.InvalidInputException;
import net.jarlehansen.proto2javame.testutils.TestConstants;

import org.junit.Test;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class MetaDataFactoryTest {

    @Test
    public void testCreateInputMetaDataVoValidInput() {
        final InputMetaData metaData = MetaDataFactory.createInputMetaDataVo(new String[]{TestConstants.JAVA_OUT,
                TestConstants.PROTO_FILE});
        assertEquals(TestConstants.GENERATED_FOLDER, metaData.getDestinationDirectory());
        assertEquals(TestConstants.PROTO_FILE, metaData.getProtoLocation());
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateInputMetaDataVoMissingJavaOut() {
        MetaDataFactory.createInputMetaDataVo(new String[]{TestConstants.PROTO_FILE});
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateInputMetaDataVoMissingProtoLocation() {
        MetaDataFactory.createInputMetaDataVo(new String[]{TestConstants.JAVA_OUT});
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateInputMetaDataVoMissingJavaOutTag() {
        MetaDataFactory.createInputMetaDataVo(new String[]{TestConstants.GENERATED_FILE, TestConstants.PROTO_FILE});
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateInputMetaDataVoInvalidDestinationDirectory() {
        MetaDataFactory.createInputMetaDataVo(new String[]{"", TestConstants.PROTO_FILE});
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateInputMetaDataVoInvalidProtoLocation() {
        MetaDataFactory.createInputMetaDataVo(new String[]{TestConstants.JAVA_OUT, ""});
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateInputMetaDataVoInalidInput() {
        MetaDataFactory.createInputMetaDataVo(new String[]{"", ""});
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateInputMetaDataVoInvalidNumberOfParameters() {
        MetaDataFactory.createInputMetaDataVo(new String[]{"", "", ""});
    }

}
