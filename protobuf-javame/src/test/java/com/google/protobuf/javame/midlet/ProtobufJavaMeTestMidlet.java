package com.google.protobuf.javame.midlet;

import jmunit.framework.cldc11.TestSuite;

import com.google.protobuf.javame.InputAndOutputImplListTest;
import com.google.protobuf.javame.InputAndOutputImplTest;
import com.google.protobuf.javame.JavaMeProtoFactoryTest;
import com.google.protobuf.javame.generated.GeneratedJavaMeObjectTest;
import com.google.protobuf.javame.output.ComputeSizeUtilTest;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class ProtobufJavaMeTestMidlet extends TestSuite {
	
	public ProtobufJavaMeTestMidlet() {
		super("protobuf-javame tests");
		add(new JavaMeProtoFactoryTest());
		add(new InputAndOutputImplTest());
		add(new InputAndOutputImplListTest());
		add(new GeneratedJavaMeObjectTest());
		add(new ComputeSizeUtilTest());
	}
}
