package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.IntegrationTestObjectProto;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.IntegrationTestObject;
import com.google.protobuf.ByteString;

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

    static IntegrationTestObject createIntegrationTestObjectJavaMe() {
        return IntegrationTestObject.newBuilder().setId(ID).setLongNumber(LONG_NUMBER).addElementName(NAME_1).
                addElementName(NAME_2).setBytesObject(net.jarlehansen.protobuf.javame.ByteString.copyFromUtf8(BYTES_OBJECT)).
                setFloatObject(FLOAT_OBJECT).setAmount(AMOUNT).build();
    }
}
