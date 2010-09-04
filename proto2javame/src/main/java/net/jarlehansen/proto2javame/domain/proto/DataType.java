package net.jarlehansen.proto2javame.domain.proto;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 2, 2010
 * Time: 10:24:55 PM
 */
public interface DataType {
    public String getImplementationType();
    public String getProtoType();
    public String getDataTypeConstant();
    public boolean isPrimitiveType();
    public String getJavaObjectType();
}
