package net.jarlehansen.proto2javame.protobuf.integration;

import static net.jarlehansen.proto2javame.protobuf.integration.JavaMeIntegrationTestMatcher.equalToJavaMeProtobufObject;
import static net.jarlehansen.proto2javame.protobuf.integration.JavaSeIntegrationTestMatcher.equalToJavaSeProtobufObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.IntegrationTestObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.UpdatedIntegrationTestObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.IntegrationTestObjectProto;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.UpdatedIntegrationTestObjectProto;
import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: Jun 14, 2009
 *         Time: 7:44:38 PM
 */
public class GoogleProtobufIntegrationTest {
    private ConsoleUnknownTagHandlerImpl consoleUnknownTagHandler;

    @Before
    public void setUp() {
        consoleUnknownTagHandler = new ConsoleUnknownTagHandlerImpl();
        IntegrationTestObject.setUnknownTagHandler(consoleUnknownTagHandler);
    }

    @After
    public void tearDown() {
        IntegrationTestObject.setUnknownTagHandler(DefaultUnknownTagHandlerImpl.newInstance());
    }

    @Test
    public void testGoogleProtobufGeneratedByteArrayToJavaMe() throws IOException {
        final IntegrationTestObjectProto.IntegrationTestObject javaSeObj = IntegrationTestConstants.createIntegrationTestObjectJavaSe();
        final byte[] javaSeData = javaSeObj.toByteArray();

        final IntegrationTestObject javaMeObj = IntegrationTestObject.parseFrom(javaSeData);

        assertThat(javaMeObj, is(equalToJavaSeProtobufObject()));
    }

    /*
     * This test runs using an updated Google protocol buffer instance of the object.
     * The new object has new fields, representing an updated contract.
     * This is to make sure the Java ME implementation supports unknown fields.
     */
    @Test
    public void testUpdatedGoogleProtobufGeneratedByteArrayToJavaMe() throws IOException {
        final UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject updatedJavaSeObj =
                IntegrationTestConstants.createUpdatedIntegrationTestObjectJavaSe();
        final byte[] updatedJavaSeData = updatedJavaSeObj.toByteArray();
        
        IntegrationTestObject.parseFrom(updatedJavaSeData);
        assertTrue(consoleUnknownTagHandler.getUnknownFields().length() > 0);
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

    /*
    * This test runs using an updated Java ME protocol buffer instance of the object.
    * The new object has new fields, representing an updated contract.
    * This is to make sure the Google Protocolbuffer implementation is able to read the updated version with unknown fields.
    */
    @Test
    public void testUpdatedJavaMeGeneratedByteArrayToGoogleProtocolbuf() throws IOException {
        final UpdatedIntegrationTestObject updatedJavaMeObj = IntegrationTestConstants.createUpdatedIntegrationTestObjectJavaMe();
        final byte[] updatedJavaMeData = updatedJavaMeObj.toByteArray();

        final IntegrationTestObjectProto.IntegrationTestObject javaSeObj = IntegrationTestObjectProto.IntegrationTestObject.parseFrom(updatedJavaMeData);

        assertTrue(javaSeObj.toString().length() > 0);
    }
}
