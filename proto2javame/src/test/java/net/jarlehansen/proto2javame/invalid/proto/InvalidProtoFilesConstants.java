package net.jarlehansen.proto2javame.invalid.proto;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum InvalidProtoFilesConstants {
    ;

    static final String VALID_OUTPUT_FOLDER = "--java_out=src/test/generated";

    private static final String PROTO_FOLDER = "src/test/resources/invalid_proto/";

    static final String INVALID_PACKAGE = PROTO_FOLDER + "invalid_package.proto";
    static final int INVALID_PACKAGE_LINE_NUM = 3;

    static final String INVALID_MESSAGE_START = PROTO_FOLDER + "invalid_message_start.proto";
    static final int INVALID_MESSAGE_START_LINE_NUM = 6;

    static final String INVALID_FIELD = PROTO_FOLDER + "invalid_field.proto";
    static final int INVALID_FIELD_LINE_NUM = 7;

    static final String INVALID_MESSAGE_END = PROTO_FOLDER + "invalid_message_end.proto";
    static final int INVALID_MESSAGE_END_LINE_NUM = 8;

    static final String INVALID_FIELD_ID = PROTO_FOLDER + "invalid_field_id.proto";

    static final String INVALID_ENUM_ID = PROTO_FOLDER + "invalid_enum_id.proto";
}
