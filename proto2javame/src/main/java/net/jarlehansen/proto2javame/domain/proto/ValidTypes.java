package net.jarlehansen.proto2javame.domain.proto;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ValidTypes {
	DOUBLE("double", "double", "DATA_TYPE_DOUBLE"), 
	FLOAT("float", "float", "DATA_TYPE_FLOAT"), 
	INT32("int32", "int", "DATA_TYPE_INT"), 
	INT64("int64", "long", "DATA_TYPE_LONG"), 
	BOOL("bool", "boolean", "DATA_TYPE_BOOLEAN"),
	STRING("string", "String", "DATA_TYPE_STRING"),
	BYTES("bytes", "net.jarlehansen.protobuf.javame.ByteString", "DATA_TYPE_BYTESTRING");
	
	private final String javaType;
	private final String protoType;
	private final String dataTypeConstant; // Used to identify list data types
	
	private ValidTypes(final String protoType, final String javaType, final String dataTypeConstant) {
		this.protoType = protoType;
		this.javaType = javaType;
		this.dataTypeConstant = dataTypeConstant;
	}
	
	public String getJavaType() {
		return javaType;
	}
	
	public String getProtoType() {
		return protoType;
	}
	
	public String getDataTypeConstant() {
		return dataTypeConstant;
	}
}
