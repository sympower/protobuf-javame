package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestMainObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.JunitTestMainObjectProto;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: Jun 14, 2009
 *         Time: 7:44:38 PM
 */
public class GoogleProtobufIntegration {
    private static final int ID = 123987;

    @Test
    public void testJavaMeGeneratedByteArrayToStandardProtobuf() throws IOException {
        final JunitTestMainObject javaMeObj = JunitTestMainObject.newBuilder().setId(ID).build();

        final byte[] javaMeData = javaMeObj.toByteArray();

        final JunitTestMainObjectProto.JunitTestMainObject javaSeObj = JunitTestMainObjectProto.JunitTestMainObject.parseFrom(javaMeData);
        assertEquals(ID, javaSeObj.getId());
    }

    @Test
    public void testJavaSeGeneratedByteArrayToJavaMeProtobuf() throws IOException {
        final JunitTestMainObjectProto.JunitTestMainObject javaSeObj = JunitTestMainObjectProto.JunitTestMainObject
                .newBuilder().setId(ID).build();

        final byte[] javaSeData = javaSeObj.toByteArray();

        final JunitTestMainObject javaMeObj = JunitTestMainObject.parseFrom(javaSeData);
        assertEquals(ID, javaMeObj.getId());
    }
}
