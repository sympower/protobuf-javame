package net.jarlehansen.proto2javame.business.sourcebuilder.resource;

import net.jarlehansen.proto2javame.domain.proto.ValidTypes;
import net.jarlehansen.proto2javame.domain.proto.ListImplementation;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum JavaSourceCodeUtil {
	;
	
	public static String createClassName(final String protoClassName) {
		return modifyNameCapitalLetter(protoClassName);
	}
	
	private static String modifyNameCapitalLetter(final String name) {
		final String startChar = name.substring(0, 1);
		final String end = name.substring(1);
		
		return startChar.toUpperCase() + end;
	}
	
	public static String createCapitalLetterName(final String name) {
		final String startChar = name.substring(0, 1);
		final String end = name.substring(1);
		
		return startChar.toUpperCase() + end;
	}

	public static String createCapitalLetterMethod(final String name) {
		String methodName = name;
		if(methodName.compareToIgnoreCase(ValidTypes.BYTES.getImplementationType()) == 0) {
			methodName = "ByteString";
		} else if(methodName.compareToIgnoreCase(ListImplementation.VECTOR.getImplementation()) == 0) {
			methodName = "List";
		}
		else {
			methodName = createCapitalLetterName(methodName);
		}
		
		return methodName;
	}
}
