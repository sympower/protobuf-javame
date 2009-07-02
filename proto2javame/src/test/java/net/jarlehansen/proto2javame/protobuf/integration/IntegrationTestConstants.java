package net.jarlehansen.proto2javame.protobuf.integration;

import com.google.protobuf.ByteString;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.IntegrationTestObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.UpdatedIntegrationTestObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.IntegrationTestObjectProto;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.UpdatedIntegrationTestObjectProto;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: Jun 15, 2009
 *         Time: 8:06:45 PM
 */
public enum IntegrationTestConstants {
    ;

    private static final int ID = 1273;
    private static final long LONG_NUMBER = 14124151667134L;

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
}
