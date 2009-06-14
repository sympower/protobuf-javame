package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestMainObject;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: Jun 14, 2009
 *         Time: 7:44:38 PM
 */
public class GoogleProtobufIntegration {

    @Test
    public void testJavaMeGeneratedByteArrayToStandardProtobuf() throws IOException {
        final JunitTestMainObject javaMeObj = JunitTestMainObject.newBuilder().setId(123).build();

        final byte[] javaMeData = javaMeObj.toByteArray();

        

    }
}
