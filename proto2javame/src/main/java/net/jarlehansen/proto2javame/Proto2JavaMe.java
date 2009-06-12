package net.jarlehansen.proto2javame;

import net.jarlehansen.proto2javame.business.generator.CodeGenerator;
import net.jarlehansen.proto2javame.business.generator.CodeGeneratorImpl;
import net.jarlehansen.proto2javame.io.exception.Proto2JavaMeException;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class Proto2JavaMe {

    // It should not be possible to create an instance of this class
	private Proto2JavaMe() {
	}
	
	public static void main(final String[] args) {
		final CodeGenerator codeGen = CodeGeneratorImpl.newInstance();

        try {
            final String className = codeGen.generateJavaSourceCode(args);
            System.out.println("SUCCESS! Created the java-object: " + className);
        } catch(Proto2JavaMeException protoException) {
            System.err.println("Error: " + protoException.getMessage());
        }

        System.out.println("\nFINISHED");
	}
}
