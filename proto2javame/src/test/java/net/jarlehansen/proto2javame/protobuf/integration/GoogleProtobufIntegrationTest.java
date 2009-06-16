package net.jarlehansen.proto2javame.protobuf.integration;

import static net.jarlehansen.proto2javame.protobuf.integration.JavaMeIntegrationTestMatcher.equalToJavaMeProtobufObject;
import static net.jarlehansen.proto2javame.protobuf.integration.JavaSeIntegrationTestMatcher.equalToJavaSeProtobufObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.IntegrationTestObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.IntegrationTestObjectProto;
import net.jarlehansen.protobuf.javame.ByteString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: Jun 14, 2009
 *         Time: 7:44:38 PM
 */
public class GoogleProtobufIntegrationTest {

    @Test
    public void testGoogleProtobufGeneratedByteArrayToJavaMe() throws IOException {
        final IntegrationTestObjectProto.IntegrationTestObject javaSeObj = IntegrationTestConstants.createIntegrationTestObjectJavaSe();
        final byte[] javaSeData = javaSeObj.toByteArray();

        final IntegrationTestObject javaMeObj = IntegrationTestObject.parseFrom(javaSeData);

        assertThat(javaMeObj, is(equalToJavaSeProtobufObject()));
    }

    @Test
    public void testGoogleProtobufGeneratedByteArrayToJavaMeInvalidInput() {
        final IntegrationTestObject javaMeInvalidObj = IntegrationTestObject.newBuilder().setBoolObj(false).setAmount(23).
                setBytesObject(ByteString.copyFromUtf8("test")).setFloatObject(14).setId(3).setLongNumber(141).build();

        assertThat(javaMeInvalidObj, is(not(equalToJavaSeProtobufObject())));
    }

    @Test
    public void testJavaMeGeneratedByteArrayToGoogleProtobuf() throws IOException {
        final IntegrationTestObject javaMeObj = IntegrationTestConstants.createIntegrationTestObjectJavaMe();
        final byte[] javaMeData = javaMeObj.toByteArray();

        final IntegrationTestObjectProto.IntegrationTestObject javaSeObj = IntegrationTestObjectProto.IntegrationTestObject.parseFrom(javaMeData);

        assertThat(javaSeObj, is(equalToJavaMeProtobufObject()));
    }
}
