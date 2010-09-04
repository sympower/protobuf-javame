package net.jarlehansen.protobuf.javame.examples.integration.enums;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.examples.generated.ExampleEnumObject;
import net.jarlehansen.protobuf.javame.examples.generated.ExampleEnumObjectImpl;

public class EnumExample {

	public static void main(String[] args) throws IOException {
		ExampleEnumObject javaMeObj = ExampleEnumObject.newBuilder().addElementColor(ExampleEnumObject.Color.BLUE)
				.addElementColor(ExampleEnumObject.Color.RED).build();
		byte[] data = javaMeObj.toByteArray();

		ExampleEnumObjectImpl.ExampleEnumObject javaSeObj = ExampleEnumObjectImpl.ExampleEnumObject.parseFrom(data);
		System.out.println(javaSeObj);
		
		System.out.println("Color.getStrinValue(1): " + ExampleEnumObject.Color.getStringValue(1));
	}
}
