package net.jarlehansen.proto2javame.protobuf.integration;

import com.google.protobuf.ByteString;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.*;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.Number;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.*;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: Jun 15, 2009
 *         Time: 8:06:45 PM
 */
enum IntegrationTestConstants {
    ;

    private static final int ID = 1273;
    private static final long LONG_NUMBER = 14124151667134L;

    private static final String ADDRESS = "address";
    private static final String NAME_1 = "Jarle Hansen";
    private static final String NAME_2 = "Ola Olsen";

    private static final String BYTES_OBJECT = "This is a ByteString value";
    private static final float FLOAT_OBJECT = 172894195F;
    private static final double AMOUNT = 124.613;

    static IntegrationTestObjectProto.IntegrationTestObject createIntegrationTestObjectJavaSe() {
        return IntegrationTestObjectProto.IntegrationTestObject.newBuilder().setId(ID).setLongNumber(LONG_NUMBER)
                .addName(NAME_1).addName(NAME_2).setBytesObject(ByteString.copyFromUtf8(BYTES_OBJECT))
                .setFloatObject(FLOAT_OBJECT).setAmount(AMOUNT).build();
    }

    static UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject createUpdatedIntegrationTestObjectJavaSe() {
        return UpdatedIntegrationTestObjectProto.UpdatedIntegrationTestObject.newBuilder().setId(ID)
                .setLongNumber(LONG_NUMBER).addName(NAME_1).addName(NAME_2)
                .setBytesObject(ByteString.copyFromUtf8(BYTES_OBJECT))
                .setFloatObject(FLOAT_OBJECT).setAmount(AMOUNT).setNewField(ByteString.copyFromUtf8("newField"))
                .setNewField2(241).setNewField3(15915125L).setNewField4("newField4").setNewField5(1903515103951F)
                .setNewField6(true).setNewField7(513.1151).build();
    }

    static IntegrationTestObject createIntegrationTestObjectJavaMe() {
        return IntegrationTestObject.newBuilder().setId(ID).setLongNumber(LONG_NUMBER).addElementName(NAME_1).
                addElementName(NAME_2).setBytesObject(net.jarlehansen.protobuf.javame.ByteString.copyFromUtf8(BYTES_OBJECT)).
                setFloatObject(FLOAT_OBJECT).setAmount(AMOUNT).build();
    }

    static UpdatedIntegrationTestObject createUpdatedIntegrationTestObjectJavaMe() {
        return UpdatedIntegrationTestObject.newBuilder().setId(ID).setLongNumber(LONG_NUMBER).addElementName(NAME_1).
                addElementName(NAME_2).setBytesObject(net.jarlehansen.protobuf.javame.ByteString.copyFromUtf8(BYTES_OBJECT)).
                setFloatObject(FLOAT_OBJECT).setAmount(AMOUNT)
                .setNewField(net.jarlehansen.protobuf.javame.ByteString.copyFromUtf8("oijijo"))
                .setNewField2(12661).setNewField3(1351356L).setNewField4("newField4").setNewField5(151356F)
                .setNewField6(false).setNewField7(13560.231).build();
    }

    static EnumTestJavaSe.ExampleObjectEnum createExampleObjectEnumJavaSe() {
        return EnumTestJavaSe.ExampleObjectEnum.newBuilder().addColor(EnumTestJavaSe.ExampleObjectEnum.Color.BLUE).setAddress(ADDRESS)
                .setDirection(EnumTestJavaSe.ExampleObjectEnum.Direction.DOWN).setId(ID).addNumber(LONG_NUMBER).setName(NAME_1).build();
    }

    static ExampleObjectEnumJavaMe createExampleObjectEnumJavaMe() {
        return ExampleObjectEnumJavaMe.newBuilder().setAddress(ADDRESS).addElementColor(ExampleObjectEnumJavaMe.Color.RED)
                .setDirection(ExampleObjectEnumJavaMe.Direction.UP).setId(ID).setName(NAME_2).addElementNumber(LONG_NUMBER).build();
    }

    static ExampleObjectNestedJavaMe createExampleObjectNestedJavaMe() {
        net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.Number number = Number.newBuilder().setId(859235).build();
        Identifier identifier = Identifier.newBuilder().setId(23439869863489L).build();

        return ExampleObjectNestedJavaMe.newBuilder().setId(1513).setAddress(ADDRESS).setName(NAME_2).setNumber(number).setIdentifier(identifier).build();
    }

    static NestedTestJavaSe.ExampleObjectNested createExampleObjectNestedJavaSe() {
        NestedTestJavaSe.Number number = NestedTestJavaSe.Number.newBuilder().setId(612).build();
        NestedTestJavaSe.Identifier identifier = NestedTestJavaSe.Identifier.newBuilder().setId(2342346L).build();

        return NestedTestJavaSe.ExampleObjectNested.newBuilder().setId(1513).setAddress(ADDRESS).setName(NAME_1).setNumber(number).setIdentifier(identifier).build();
    }

    static ExampleObjectNestedListJavaMe createExampleObjectNestedListJavaMe() {
        Person person1 = Person.newBuilder().setName(NAME_1).build();
        Person person2 = Person.newBuilder().setName(NAME_2).build();

        return ExampleObjectNestedListJavaMe.newBuilder().setId(1879254).addElementPersons(person1).addElementPersons(person2).build();
    }

    static NestedListTestJavaSe.ExampleObjectNestedListJavaSe createExampleObjectNestedListJavaSe() {
        NestedListTestJavaSe.Person person1 = NestedListTestJavaSe.Person.newBuilder().setName(NAME_1).build();
        NestedListTestJavaSe.Person person2 = NestedListTestJavaSe.Person.newBuilder().setName(NAME_2).build();

        return NestedListTestJavaSe.ExampleObjectNestedListJavaSe.newBuilder().setId(1325).addPersons(person1).addPersons(person2).build();
    }


    static EmployeeGoalJavaMe createEmployeeExampleObjectJavaMe() {
        EmployeeGoalCommentJavaMe comment = EmployeeGoalCommentJavaMe.newBuilder().setAuthor(NAME_1)
                .setComment("comment").setCreateDate(1351L).build();

        return EmployeeGoalJavaMe.newBuilder().addElementEmployeeGoalComments(comment)
                .setActualValue(24124F).setCriticality(123).setDueDate(235235).setKey("key").setName(NAME_2)
                .setPercentCompleted(32532F).setPercentProgress(235325F).setStartDate(12412421).setStatus("status")
                .setTargetValue(12412124F).setType("type").build();
    }

    static EmployeeGoal.EmployeeGoalJavaSe createEmployeeExampleObjectJavaSe() {
        EmployeeGoal.EmployeeGoalCommentJavaSe comment = EmployeeGoal.EmployeeGoalCommentJavaSe.newBuilder()
                .setComment("comment").setAuthor(NAME_1).build();

        return EmployeeGoal.EmployeeGoalJavaSe.newBuilder().addEmployeeGoalComments(comment).setActualValue(24124F)
                .setCriticality(123).setDueDate(235235).setKey("key").setName(NAME_2).setPercentCompleted(32532F)
                .setPercentProgress(235325F).setStartDate(12412421).setStatus("status").setTargetValue(12412124F)
                .setType("type").build();
    }
}
