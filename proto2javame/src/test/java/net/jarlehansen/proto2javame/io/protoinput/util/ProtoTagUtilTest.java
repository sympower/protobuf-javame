package net.jarlehansen.proto2javame.io.protoinput.util;

import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import org.junit.Test;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class ProtoTagUtilTest {

    @Test
    public void testIsValidProtoFileValidInput() {
        ProtoTagUtil.isValidProtoFile(TestConstants.PROTO_FILE);
    }

    @Test(expected = ProtoFileValidationException.class)
    public void testIsValidProtoFileInvalidInput() {
        ProtoTagUtil.isValidProtoFile("not valid proto file");
    }
}
