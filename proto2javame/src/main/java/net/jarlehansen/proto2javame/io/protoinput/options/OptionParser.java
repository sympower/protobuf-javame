package net.jarlehansen.proto2javame.io.protoinput.options;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.protoinput.AbstractProtoParser;

import java.util.Arrays;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class OptionParser extends AbstractProtoParser {
    public OptionParser() {
    }

    /**
     * Valid input:
     * option java_package = "net.jarlehansen.protobuf.javame.generated";
     */
    public void parseAndAddProtoFile(ProtoFileInput protoInput) {
        // remove ;
        String packageName = strings[3].substring(0, strings[3].length() - 1);
        // removed "
        packageName = packageName.replace("\"", "");

        protoInput.setPackageName(packageName);
    }
}
