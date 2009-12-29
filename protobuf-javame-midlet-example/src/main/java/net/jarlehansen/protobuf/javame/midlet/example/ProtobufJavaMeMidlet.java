package net.jarlehansen.protobuf.javame.midlet.example;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

import net.jarlehansen.protobuf.javame.examples.generated.ExampleObject;
import net.sf.microlog.core.Appender;
import net.sf.microlog.core.Logger;
import net.sf.microlog.core.LoggerFactory;
import net.sf.microlog.core.appender.ConsoleAppender;

public class ProtobufJavaMeMidlet extends MIDlet implements CommandListener {
	private static final Logger logger = LoggerFactory.getLogger(ProtobufJavaMeMidlet.class);

	private final Form form = new Form("Protobuf-javame example");
	private final Command exitCommand = new Command("Exit", Command.EXIT, 1);

	public ProtobufJavaMeMidlet() {
		/*
		 * This is added to the source code to make it as simply as possible.
		 * The best option is to add Microlog appender to the
		 * microlog.properties file instead.
		 * 
		 * If you want to try the example out on a mobile device please change
		 * Appender to for example RecordStoreAppender or
		 * BluetoothSerialAppender.
		 */
		final Appender appender = new ConsoleAppender();
		logger.addAppender(appender);
	}

	protected void destroyApp(boolean uncondtional) {
	}

	protected void pauseApp() {
	}

	protected void startApp() {
		form.addCommand(exitCommand);
		form.setCommandListener(this);

		Display.getDisplay(this).setCurrent(form);
		form.append("Starting to serialize and deserialize protobuf-javame objects.");
		
		try {
			logger.info("Creating example object");
			final ExampleObject exampleObject = ExampleObject.newBuilder().setId(1).setAddress("Highway 1").setName(
					"John").build();
			logger.info("ExampleObject1: " + exampleObject.toString());

			logger.info("Creating byte array from example object");
			byte[] data = exampleObject.toByteArray();
			logger.info("Length of byte array: " + data.length);

			logger.info("Parsing byte array back to example object");
			final ExampleObject exampleObject2 = ExampleObject.parseFrom(data);
			logger.info("ExampleObject2: " + exampleObject2.toString());
		} catch (IOException io) {
			logger.error("Unable to complete the protobuf-javame midlet example", io);
		} finally {
			form.append("Finished! Results printed in log output.");
		}
	}

	public void commandAction(Command cmd, Displayable d) {
		if (cmd == exitCommand) {
			notifyDestroyed();
		}
	}
}
