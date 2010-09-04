package net.jarlehansen.proto2javame.business.generator;

import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;

import java.util.List;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface CodeGenerator {
	public List<JavaFileOutput> generateJavaSourceCode(final String... inputValues);
}
