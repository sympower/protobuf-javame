package net.jarlehansen.proto2javame.domain.proto;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ValidTypes implements DataType {
	DOUBLE("double", "double", "DATA_TYPE_DOUBLE", true, "Double"),
	FLOAT("float", "float", "DATA_TYPE_FLOAT", true, "Float"),
	INT32("int32", "int", "DATA_TYPE_INT", true, "Integer"),
	INT64("int64", "long", "DATA_TYPE_LONG", true, "Long"),
	BOOL("bool", "boolean", "DATA_TYPE_BOOLEAN", true, "Boolean"),
	STRING("string", "String", "DATA_TYPE_STRING", false),
    ENUM("enum", "enum", "DATA_TYPE_ENUM", true, "Integer"),
	ENUM_VALUE("", "int", "DATA_TYPE_INT", true, "Integer"),
    BYTES("bytes", "net.jarlehansen.protobuf.javame.ByteString", "DATA_TYPE_BYTESTRING", false);

	
	private final String implementationType;
	private final String protoType;
	private final String dataTypeConstant; // Used to identify list data types
    private final boolean primitiveType;
    private final String javaObjectType; // If this is not set, the object type will be the same as the javaType with a capital letter

	private ValidTypes(final String protoType, final String implementationType, final String dataTypeConstant, final boolean primitiveType) {
		this.protoType = protoType;
		this.implementationType = implementationType;
		this.dataTypeConstant = dataTypeConstant;
        this.primitiveType = primitiveType;
        javaObjectType = "";
	}

    private ValidTypes(final String protoType, final String implementationType, final String dataTypeConstant, final boolean primitiveType,
                       final String javaObjectType) {
		this.protoType = protoType;
		this.implementationType = implementationType;
		this.dataTypeConstant = dataTypeConstant;
        this.primitiveType = primitiveType;
        this.javaObjectType = javaObjectType;
	}
	
	public String getImplementationType() {
		return implementationType;
	}
	
	public String getProtoType() {
		return protoType;
	}
	
	public String getDataTypeConstant() {
		return dataTypeConstant;
	}

    public boolean isPrimitiveType() {
        return primitiveType;
    }

    public String getJavaObjectType() {
        return javaObjectType;
    }
}
