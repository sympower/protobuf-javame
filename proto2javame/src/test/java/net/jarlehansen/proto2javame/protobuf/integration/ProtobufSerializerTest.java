package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.BatteryStatus;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.CarStatus;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @Author Jarle Hansen (jarle@jarlehansen.net)
 * Created: 10:57 PM - 7/1/11
 */
public class ProtobufSerializerTest {

    private void dump(byte[] bytes) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            sb.append(Integer.toHexString(b & 0xFF));
            if (i < bytes.length - 1) {
                sb.append(" ");
            }
        }

        System.out.println("RAW protobuf (" + bytes.length + "): " + sb);
    }


    @Test
    public void testWriteTo_andToArray_nestedObject() throws IOException {
        // create the object to serialize
        BatteryStatus batteryStatus = BatteryStatus.newBuilder()
                .setBatterySoC(64).build();
        CarStatus obj = CarStatus.newBuilder().setUpdateTime(1309518199967L)
                .setBatteryStatus(batteryStatus).setEstimatedRange(80)
                .setIdealRange(112).build();

        // Serialize to byte array
        byte[] serialized = obj.toByteArray();
        dump(serialized);

        // Serialize to stream
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        obj.writeTo(outStream);

        byte[] written = outStream.toByteArray();
        dump(written);

        // Both should be equal
        assertEquals(serialized, written);


    }
}
