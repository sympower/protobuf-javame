package net.jarlehansen.proto2javame.protobuf.integration;

import com.google.protobuf.InvalidProtocolBufferException;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.IntegrationTestObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.UpdatedIntegrationTestObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.IntegrationTestObjectProto;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.UpdatedIntegrationTestObjectProto;
import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static net.jarlehansen.proto2javame.protobuf.integration.JavaMeIntegrationTestMatcher.equalToJavaMeProtobufObject;
import static net.jarlehansen.proto2javame.protobuf.integration.JavaSeIntegrationTestMatcher.equalToJavaSeProtobufObject;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

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

        System.out.println(consoleUnknownTagHandler.getUnknownFields().length());

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

    @Test
    public void testWriteDelimitedWithAdditionalContentFromJavaMeObj() throws IOException {
        final String tempFileName = "src/test/generated/txt2/file.txt";
        File file = new File(tempFileName);
        FileOutputStream out = new FileOutputStream(file);

        final UpdatedIntegrationTestObject updatedJavaMeObj = IntegrationTestConstants.createUpdatedIntegrationTestObjectJavaMe();
        updatedJavaMeObj.writeDelimitedTo(out);

        out.write("additional content".getBytes("UTF-8"));
        out.write("this should not be included when parsing the proto message".getBytes("UTF-8"));
        out.close();

        FileInputStream input = new FileInputStream(file);
        UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject javaSeObj2 = UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject.parseDelimitedFrom(input);
        input.close();

        assertTrue(javaSeObj2.toString().length() > 0);
    }

    @Test(expected = InvalidProtocolBufferException.class)
    public void testWriteWithAdditionalContentFromJavaMeObj() throws IOException {
        final String tempFileName = "src/test/generated/txt2/file.txt";
        File file = new File(tempFileName);
        FileOutputStream out = new FileOutputStream(file);

        final UpdatedIntegrationTestObject updatedJavaMeObj = IntegrationTestConstants.createUpdatedIntegrationTestObjectJavaMe();
        // This should fail since it does not use writeDelimitedTo and adds additional content to the OutputStream
        updatedJavaMeObj.writeTo(out);

        out.write("additional content".getBytes("UTF-8"));
        out.write("this should not be included when parsing the proto message".getBytes("UTF-8"));
        out.close();

        FileInputStream input = new FileInputStream(file);
        UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject javaSeObj2 = UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject.parseDelimitedFrom(input);
        input.close();

        fail("Expected InvalidProtocolBufferException");
    }

    @Test
    public void testReadDelimitedFromWithAdditionalContentFromJavaSeObj() throws IOException {
        final String tempFileName = "src/test/generated/txt2/file.txt";
        File file = new File(tempFileName);
        FileOutputStream out = new FileOutputStream(file);

        UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject javaSeObj = IntegrationTestConstants.createUpdatedIntegrationTestObjectJavaSe();
        javaSeObj.writeDelimitedTo(out);

        out.write("additional content".getBytes("UTF-8"));
        out.write("this should not be included when parsing the proto message".getBytes("UTF-8"));
        out.close();

        FileInputStream input = new FileInputStream(file);

        UpdatedIntegrationTestObject javaMeObj = UpdatedIntegrationTestObject.parseDelimitedFrom(input);
        input.close();

        assertTrue(javaMeObj.toString().length() > 0);
    }

    @Test(expected = net.jarlehansen.protobuf.javame.original.input.InvalidProtocolBufferException.class)
    public void testReadFromWithAdditionalContentFromJavaSeObj() throws IOException {
        final String tempFileName = "src/test/generated/txt2/file.txt";
        File file = new File(tempFileName);
        FileOutputStream out = new FileOutputStream(file);

        UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject javaSeObj = IntegrationTestConstants.createUpdatedIntegrationTestObjectJavaSe();
        javaSeObj.writeTo(out);

        out.write("additional content".getBytes("UTF-8"));
        out.write("this should not be included when parsing the proto message".getBytes("UTF-8"));
        out.close();

        FileInputStream input = new FileInputStream(file);

        UpdatedIntegrationTestObject javaMeObj = UpdatedIntegrationTestObject.parseDelimitedFrom(input);
        input.close();

        fail("Expected InvalidProtocolBufferException");
    }
}
