package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: Jul 2, 2009
 *         Time: 8:32:39 PM
 */
class ConsoleUnknownTagHandlerImpl implements UnknownTagHandler {
    private String unknownFields = "";

    public void unknownTag(final String unknownFields) {
        this.unknownFields = unknownFields;
        System.out.println(unknownFields);
    }

    public String getUnknownFields() {
        return unknownFields;
    }
}
