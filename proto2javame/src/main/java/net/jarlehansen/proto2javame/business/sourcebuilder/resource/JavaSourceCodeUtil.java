package net.jarlehansen.proto2javame.business.sourcebuilder.resource;

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
		if(methodName.contains("ByteString")) {
			methodName = "ByteString";
		} else if(methodName.contains("Vector")) {
			methodName = "List";
		}
		else {
			methodName = createCapitalLetterName(methodName);
		}
		
		return methodName;
	}
}
