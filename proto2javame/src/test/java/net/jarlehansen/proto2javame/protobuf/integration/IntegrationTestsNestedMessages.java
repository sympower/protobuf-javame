package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.*;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.EmployeeGoal;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.NestedListTestJavaSe;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.NestedTestJavaSe;
import org.junit.Ignore;
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
        System.out.println(javaSeObj);
        System.out.println(javaMeObj);

        assertEquals(javaSeObj.getId(), javaMeObj.getId());
        assertEquals(javaSeObj.getPersons(0).getName(), ((Person)javaMeObj.getPersons().get(0)).getName());
        assertEquals(javaSeObj.getPersons(1).getName(), ((Person)javaMeObj.getPersons().get(1)).getName());
    }

    @Test
    public void testIntegrationEmployeeGoalJavaMe() throws IOException {
        EmployeeGoalJavaMe javaMeObj = IntegrationTestConstants.createEmployeeExampleObjectJavaMe();
        byte[] data = javaMeObj.toByteArray();

        EmployeeGoal.EmployeeGoalJavaSe javaSeObj = EmployeeGoal.EmployeeGoalJavaSe.parseFrom(data);
        assertEquals(javaMeObj.getName(), javaSeObj.getName());
        assertEquals(((EmployeeGoalCommentJavaMe)javaMeObj.getEmployeeGoalComments().get(0)).getAuthor(), javaSeObj.getEmployeeGoalComments(0).getAuthor());
        assertEquals(((EmployeeGoalCommentJavaMe)javaMeObj.getEmployeeGoalComments().get(0)).getComment(), javaSeObj.getEmployeeGoalComments(0).getComment());
    }

    @Test
    public void testIntegrationEmployeeGoalJavaSe() throws IOException {
        EmployeeGoal.EmployeeGoalJavaSe javaSeObj = IntegrationTestConstants.createEmployeeExampleObjectJavaSe();
        byte[] data = javaSeObj.toByteArray();

        EmployeeGoalJavaMe javaMeObj = EmployeeGoalJavaMe.parseFrom(data);
        assertEquals(javaSeObj.getStatus(), javaMeObj.getStatus());
        assertEquals(javaSeObj.getEmployeeGoalComments(0).getComment(), ((EmployeeGoalCommentJavaMe)javaMeObj.getEmployeeGoalComments().get(0)).getComment());
    }
}
