package net.jarlehansen.proto2javame.io.sourceoutput;

import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;

import java.util.List;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface SourceFileWriter {
	public void writeJavaSourceFile(final InputMetaData metaData, final List<JavaFileOutput> javaOutputList);
}
