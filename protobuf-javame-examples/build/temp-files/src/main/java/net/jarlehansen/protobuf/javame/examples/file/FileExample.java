package net.jarlehansen.protobuf.javame.examples.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.jarlehansen.protobuf.javame.examples.generated.ExampleObject;

public class FileExample {
	private static final File file = new File("src/main/resources/test-file/TestFile.txt");

	public FileExample() {
	}

	public static void main(String[] args) throws IOException {
		FileExample fileExample = new FileExample();
		fileExample.writeAndReadFile();
		fileExample.writeAndReadFileDelimited();
	}

	private void writeAndReadFile() throws IOException {
		// Write to file
		FileOutputStream output = new FileOutputStream(file);
		final ExampleObject exampleObject = ExampleObject.newBuilder().setId(241).setName("Jarle").setAddress("Oslo")
				.build();
		exampleObject.writeTo(output);
		
		// Read from file
		FileInputStream input = new FileInputStream(file);
		final ExampleObject exampleObject2 = ExampleObject.parseFrom(input);
		
		System.out.println("Object read from file: " + exampleObject2.toString());
	}
	
	private void writeAndReadFileDelimited() throws IOException {
		// Write to file
		FileOutputStream output = new FileOutputStream(file);
		final ExampleObject exampleObject = ExampleObject.newBuilder().setId(241).setName("Jarle").setAddress("Oslo")
				.build();
		exampleObject.writeDelimitedTo(output);
		// Write additional content that is not part of the protocol buffer message
		output.write("additional content".getBytes());
		
		// Read from file
		FileInputStream input = new FileInputStream(file);
		final ExampleObject exampleObject2 = ExampleObject.parseDelimitedFrom(input);
		
		// Using readDelimited from will make sure the additional content is not included as part of the message
		System.out.println("Delimited Object read from file: " + exampleObject2.toString());
	}
}
