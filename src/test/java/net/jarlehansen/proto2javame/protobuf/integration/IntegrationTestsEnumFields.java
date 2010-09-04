package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.ExampleObjectEnumJavaMe;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.EnumTestJavaSe;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 2, 2010
 * Time: 5:29:42 PM
 */
public class IntegrationTestsEnumFields {

    @Test
    public void testEnumIntegrationJavaMe() throws IOException {
        ExampleObjectEnumJavaMe javaMeObj = IntegrationTestConstants.createExampleObjectEnumJavaMe();
        byte[] data = javaMeObj.toByteArray();

        EnumTestJavaSe.ExampleObjectEnum javaSeObj = EnumTestJavaSe.ExampleObjectEnum.parseFrom(data);
        assertEquals(javaMeObj.getAddress(), javaSeObj.getAddress());
        assertEquals(javaMeObj.getNumber().get(0), javaSeObj.getNumber(0));
        assertEquals(ExampleObjectEnumJavaMe.Color.getStringValue((Integer)javaMeObj.getColor().get(0)), javaSeObj.getColor(0).name());
    }

    @Test
    public void testEnumIntegrationJavaSe() throws IOException {
        EnumTestJavaSe.ExampleObjectEnum javaSeObj = IntegrationTestConstants.createExampleObjectEnumJavaSe();
        byte[] data = javaSeObj.toByteArray();

        ExampleObjectEnumJavaMe javaMeObj = ExampleObjectEnumJavaMe.parseFrom(data);
        assertEquals(javaSeObj.getAddress(), javaMeObj.getAddress());
        assertEquals(javaSeObj.getDirection().name(), ExampleObjectEnumJavaMe.Direction.getStringValue(javaMeObj.getDirection()));
    }

}
