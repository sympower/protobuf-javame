package net.jarlehansen.proto2javame.io.protoinput.options;

import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.io.protoinput.AbstractProtoParser;

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
    @Override
	public void parseAndAddProtoFile(ProtoFileInput protoInput) {
    	if(strings[1].equals("java_package"))
    	{
    		// remove ;
    		String packageName = strings[3].substring(0, strings[3].length() - 1);
    		// removed "
    		packageName = packageName.replace("\"", "");

    		protoInput.setPackageName(packageName);
    	}
    	else if(strings[1].equals("optimize_for"))
    	{
    		 String optimizeOpt = strings[3];
    		 if(optimizeOpt.equals("JSON_RUNTIME;"))
    			 protoInput.setSupportJsonOpt(true);
    	}
    }
}
