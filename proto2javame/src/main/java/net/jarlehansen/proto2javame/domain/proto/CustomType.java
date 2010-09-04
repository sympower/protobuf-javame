package net.jarlehansen.proto2javame.domain.proto;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 2, 2010
 * Time: 10:21:30 PM
 */
public class CustomType implements DataType {
    private final String implementationType;

    public CustomType(final String implementationType) {
        this.implementationType = implementationType;
    }

    public String getImplementationType() {
        return implementationType;
    }

    public String getProtoType() {
        return getImplementationType();
    }

    public String getDataTypeConstant() {
        return getImplementationType();
    }

    // Custom object will never be a primitive type
    public boolean isPrimitiveType() {
        return false;
    }

    public String getJavaObjectType() {
        return getImplementationType();
    }
}
