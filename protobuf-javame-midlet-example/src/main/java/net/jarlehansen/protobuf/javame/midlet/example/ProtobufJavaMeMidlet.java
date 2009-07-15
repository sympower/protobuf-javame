package net.jarlehansen.protobuf.javame.midlet.example;

import java.io.IOException;

import javax.microedition.midlet.MIDlet;

import net.jarlehansen.protobuf.javame.examples.generated.ExampleObject;
import net.sf.microlog.core.Logger;
import net.sf.microlog.core.LoggerFactory;
import net.sf.microlog.midp.bluetooth.BluetoothRemoteDevice;
import net.sf.microlog.midp.bluetooth.BluetoothSerialAppender;

public class ProtobufJavaMeMidlet extends MIDlet {

	public ProtobufJavaMeMidlet() {
	}

	protected void destroyApp(boolean uncondtional) {
	}

	protected void pauseApp() {

	}

	protected void startApp() {
		final Logger logger = LoggerFactory.getLogger();
		final BluetoothSerialAppender appender = new BluetoothSerialAppender(BluetoothRemoteDevice.setAddress("002608BDB48C"));
		logger.addAppender(appender);
		
		try {
			logger.info("Creating example object");
			final ExampleObject exampleObject = ExampleObject.newBuilder().setId(1).setAddress("Highway 1").setName("John")
			.build();
			logger.info("ExampleObject1: " + exampleObject.toString());
			
			logger.info("Creating byte array from example object");
			byte[] data = exampleObject.toByteArray();
			logger.info("Length of byte array: " + data.length);
			
			logger.info("Parsing byte array back to example object");
			final ExampleObject exampleObject2 = ExampleObject.parseFrom(data);
			logger.info("ExampleObject2: " + exampleObject2.toString());
		} catch(IOException io) {
			logger.error("Unable to complete the protobuf-javame midlet example", io);
		}
	}
}
