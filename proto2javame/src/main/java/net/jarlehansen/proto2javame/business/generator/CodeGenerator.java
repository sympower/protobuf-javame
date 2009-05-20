package net.jarlehansen.proto2javame.business.generator;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface CodeGenerator {
	public String generateJavaSourceCode(final String... inputValues);
}
