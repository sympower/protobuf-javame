package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.ExampleObjectNestedJavaMe;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.ExampleObjectNestedListJavaMe;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.Person;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.NestedListTestJavaSe;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.NestedTestJavaSe;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 3, 2010
 * Time: 1:48:06 PM
 */
public class IntegrationTestsNestedMessages {

    @Test
    public void testIntegrationJavaMe() throws IOException {
        ExampleObjectNestedJavaMe javaMeObj = IntegrationTestConstants.createExampleObjectNestedJavaMe();
        byte[] data = javaMeObj.toByteArray();

        NestedTestJavaSe.ExampleObjectNested javaSeObj = NestedTestJavaSe.ExampleObjectNested.parseFrom(data);
        assertEquals(javaMeObj.getId(), javaSeObj.getId());
        assertEquals(javaMeObj.getNumber().getId(), javaSeObj.getNumber().getId());
        assertEquals(javaMeObj.getIdentifier().getId(), javaSeObj.getIdentifier().getId());
    }

    @Test
    public void testIntegrationJavaSe() throws IOException {
        NestedTestJavaSe.ExampleObjectNested javaSeObj = IntegrationTestConstants.createExampleObjectNestedJavaSe();
        byte[] data = javaSeObj.toByteArray();

        ExampleObjectNestedJavaMe javaMeObj = ExampleObjectNestedJavaMe.parseFrom(data);
        assertEquals(javaSeObj.getId(), javaMeObj.getId());
        assertEquals(javaSeObj.getNumber().getId(), javaMeObj.getNumber().getId());
        assertEquals(javaSeObj.getIdentifier().getId(), javaMeObj.getIdentifier().getId());
    }

    @Test
    public void testIntegrationListJavaMe() throws IOException {
        ExampleObjectNestedListJavaMe javaMeObj = IntegrationTestConstants.createExampleObjectNestedListJavaMe();
        byte[] data = javaMeObj.toByteArray();

        NestedListTestJavaSe.ExampleObjectNestedListJavaSe javaSeObj = NestedListTestJavaSe.ExampleObjectNestedListJavaSe.parseFrom(data);
        assertEquals(javaMeObj.getId(), javaSeObj.getId());
        assertEquals(((Person)javaMeObj.getPersons().get(0)).getName(), javaSeObj.getPersons(0).getName());
        assertEquals(((Person)javaMeObj.getPersons().get(1)).getName(), javaSeObj.getPersons(1).getName());
    }

    @Test
    public void testIntegrationListJavaSe() throws IOException {
        NestedListTestJavaSe.ExampleObjectNestedListJavaSe javaSeObj = IntegrationTestConstants.createExampleObjectNestedListJavaSe();
        byte[] data = javaSeObj.toByteArray();

        ExampleObjectNestedListJavaMe javaMeObj = ExampleObjectNestedListJavaMe.parseFrom(data);
        assertEquals(javaSeObj.getId(), javaMeObj.getId());
        assertEquals(javaSeObj.getPersons(0).getName(), ((Person)javaMeObj.getPersons().get(0)).getName());
        assertEquals(javaSeObj.getPersons(1).getName(), ((Person)javaMeObj.getPersons().get(1)).getName());
    }
}
