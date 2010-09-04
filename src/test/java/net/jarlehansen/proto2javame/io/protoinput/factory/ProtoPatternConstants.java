package net.jarlehansen.proto2javame.io.protoinput.factory;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 2, 2010
 * Time: 12:55:26 PM
 */
enum ProtoPatternConstants {
    ;

    static final String COMMON_INVALID_VALUE = "this should be invalid for all fields";
    static final String COMMON_END_PATTERN = "}";

    static final String PACKAGE_PATTERN = "package test;";
    static final String JAVA_PACKAGE_PATTERN = "option java_package = \"net.jarlehansen.test\";";
    static final String JAVA_OUTER_CLASSNAME_PATTERN = "option java_outer_classname = \"Test\";";
    static final String MESSAGE_START_PATTERN = "message Book {";
    static final String MESSAGE_FIELD_PATTERN = "optional int32 id = 1;";
    static final String ENUM_START_PATTERN = "enum Color {";
    static final String ENUM_VALUE_PATTERN = "BLUE = 1;";
}
