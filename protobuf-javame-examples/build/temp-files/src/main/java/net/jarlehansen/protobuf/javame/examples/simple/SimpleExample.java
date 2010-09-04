package net.jarlehansen.protobuf.javame.examples.simple;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.examples.generated.ExampleObject;

/**
 * A simple example that shows how to use the protobuf-javame implementation.
 * 
 * @author Jarle Hansen hansjar@gmail.com
 * 
 */
public class SimpleExample {
	public static void main(String[] main) throws IOException {
		// Create a byte array from the ExampleObject instance
		final ExampleObject exampleObject = ExampleObject.newBuilder().setId(241).setName("Jarle").setAddress("Oslo").build();
		final byte[] data = exampleObject.toByteArray();
		
		// Transform the byte array back to a new ExampleObject instance with the same content
		final ExampleObject exampleObject2 = ExampleObject.parseFrom(data);
		System.out.println(exampleObject2.toString());
	}
}
