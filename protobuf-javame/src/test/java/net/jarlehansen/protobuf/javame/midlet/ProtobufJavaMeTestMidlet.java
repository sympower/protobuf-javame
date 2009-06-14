package net.jarlehansen.protobuf.javame.midlet;

import net.jarlehansen.protobuf.javame.InputAndOutputImplListTest;
import net.jarlehansen.protobuf.javame.InputAndOutputImplTest;
import net.jarlehansen.protobuf.javame.factory.JavaMeProtoFactoryTest;
import net.jarlehansen.protobuf.javame.generatedobjtest.GeneratedJavaMeObjectTest;
import net.jarlehansen.protobuf.javame.util.ComputeSizeUtilTest;
import jmunit.framework.cldc11.TestSuite;


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
