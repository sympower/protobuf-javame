package net.jarlehansen.protobuf.javame.examples.integration.nested;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.examples.generated.ExampleNestedObject;
import net.jarlehansen.protobuf.javame.examples.generated.ExampleNestedObjectImpl;

public class NestedObjectExample {
	
	public static void main(String[] args) throws IOException {
		ExampleNestedObjectImpl.Number javaSeNumber = ExampleNestedObjectImpl.Number.newBuilder().setId(511).build();
		ExampleNestedObjectImpl.ExampleNestedObject javaSeObj = ExampleNestedObjectImpl.ExampleNestedObject.newBuilder().setNumber(javaSeNumber).build();
		byte[] data = javaSeObj.toByteArray();
		
		ExampleNestedObject javaMeObj = ExampleNestedObject.parseFrom(data);
		System.out.println(javaMeObj);
	}
}
