protobuf-javame-midlet-example
----------------------------------------------------

This midlet uses MicroLog to show the results from the example, it has no UI on the phone.
Start the MicroLog server first (for more info on MicroLog: http://microlog.sourceforge.net/snapshot/) and then run the midlet on your phone.
Remember to update the ProtobufJavaMeMidlet.java with the correct Bluetooth address to get the example working.
- new BluetoothSerialAppender(BluetoothRemoteDevice.setAddress("<insert your Bluetooth address here>"));

To package the project use an IDE tool, this example is based on Eclipse and Mobile Tools for Java.
- http://www.eclipse.org/dsdp/mtj/