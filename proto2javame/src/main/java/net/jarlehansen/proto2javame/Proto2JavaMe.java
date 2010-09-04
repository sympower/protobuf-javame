package net.jarlehansen.proto2javame;

import net.jarlehansen.proto2javame.business.generator.CodeGenerator;
import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.io.exception.Proto2JavaMeException;
import net.jarlehansen.proto2javame.modules.DependencyInjector;

import java.util.Date;
import java.util.List;


/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class Proto2JavaMe {

    // It should not be possible to create an instance of this class

    private Proto2JavaMe() {
    }

    public static void main(final String[] args) {
        System.out.println("Starting protobuf-javame code generation, " + new Date());
        long start = System.currentTimeMillis();

        final CodeGenerator codeGen = DependencyInjector.MAIN.getInstance(CodeGenerator.class);

        try {
            final List<JavaFileOutput> javaOutputList = codeGen.generateJavaSourceCode(args);
            System.out.println("SUCCESS! Created the java-object(s): ");

            for(JavaFileOutput javaOutput : javaOutputList) {
                System.out.println("\t-> " + javaOutput.getClassName());
            }
            System.out.println("In package: " + javaOutputList.get(0).getPackageName());
        } catch (Proto2JavaMeException protoException) {
            System.err.println("Error: " + protoException.getMessage());
        }

        long finished = System.currentTimeMillis();
        long timeUsed = finished - start;

        System.out.println("\nFINISHED, time used: " + timeUsed + "ms");
    }
}
