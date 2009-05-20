package net.jarlehansen.proto2javame.testutils;

import net.jarlehansen.proto2javame.domain.proto.ValidScopes;
import net.jarlehansen.proto2javame.domain.proto.ValidTypes;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum TestConstants {
	;
	
	public static final String GENERATED_FOLDER = "src/test/generated";
	public static final String JAVA_OUT = "--java_out=" + GENERATED_FOLDER;
	
	public static final String GENERATED_FILE = GENERATED_FOLDER + "/net/jarlehansen/protobuf/javame/generated/JunitTestObject.java";
	
	private static final String RESOURCES_FOLDER = "src/test/resources";
	public static final String PROTO_FILE = RESOURCES_FOLDER + "/JUnitTest.proto";
	public static final String PROTO_CLASS_NAME = "JunitTestObject";
	
	public static final String INVALID_PROTO_FILE = RESOURCES_FOLDER + "/InvalidFile.proto";
	
	
	public static final String VALID_PROTO_FIELD = "required int32 test = 1;";
	public static final int VALID_PROTO_ID = 1;
	public static final ValidTypes VALID_PROTO_TYPE = ValidTypes.INT32;
	public static final String VALID_PROTO_NAME = "test";
	public static final ValidScopes VALID_PROTO_SCOPE = ValidScopes.REQUIRED;
	
	public static final String VALID_PROTO_ID_TAG = "= 1;";
	public static final String VALID_PROTO_ID_TAG2 = "    =    1 ;";
	public static final String INVALID_PROTO_ID_TAG = "= 100;";
	
	public static final String VALID_PROTO_MESSAGE_START_TAG = "message Test {";
	public static final String VALID_PROTO_MESSAGE = "Test";
	
	public static final String VALID_PROTO_JAVA_PACKAGE_TAG = "option java_package = \"net.jarlehansen.protobuf.javame.generated\";";
	public static final String VALID_PROTO_JAVA_PACKAGE_TAG2 = "option java_package = \"net.jarlehansen.protobuf.javame.generated\" ;";
	public static final String VALID_PROTO_JAVA_PACKAGE = "net.jarlehansen.protobuf.javame.generated";
	
	public static final String INVALID_PROTO_JAVA_PACKAGE_TAG = "option java_package = \"net.jarlehansen.protobuf.javame.generated\"";

    public static final String GENERATED_TEST_FILE = GENERATED_FOLDER + "/net/jarlehansen/protobuf/javame/generated/JunitTestMainObject.java";
    public static final String LARGE_GENERATED_TEST_FILE = GENERATED_FOLDER + "/net/jarlehansen/protobuf/javame/generated/JunitTestLargeObject.java";
    public static final String GENERATED_TXT_FILE = GENERATED_FOLDER + "/txt/GeneratedTextFile.txt";
    public static final String LARGE_GENERATED_TXT_FILE = GENERATED_FOLDER + "/txt/LargeTextFileRequired.txt";
    public static final String CONSTANT_TXT_FILE = RESOURCES_FOLDER + "/txt/TextFile.txt";
    public static final String LARGE_CONSTANT_TXT_FILE_ALL = RESOURCES_FOLDER + "/txt/LargeTextFileAll.txt";
    public static final String LARGE_CONSTANT_TXT_FILE_REQUIRED = RESOURCES_FOLDER + "/txt/LargeTextFileRequired.txt";
    public static final String LARGE_CONSTANT_TXT_FILE_REQUIRED_OPTIONAL = RESOURCES_FOLDER + "/txt/LargeTextFileRequiredAndOptional.txt";
    public static final String EMPTY_TXT_FILE = RESOURCES_FOLDER + "/txt/EmptyFile.txt";
}
