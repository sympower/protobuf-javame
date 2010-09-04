package net.jarlehansen.proto2javame.io.sourceoutput;

import java.io.File;

import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.io.exception.ProtoFileException;

enum SourceWriterUtil {
	;
	
	static String createDestinationFile(final InputMetaData metaData, final JavaFileOutput javaOutput) {
		final String packageName = javaOutput.getPackageName().replace(".", "/");
		
		if(!createPackageDirectory(packageName, metaData.getDestinationDirectory())) {
			throw new ProtoFileException("Unable to create directory " + packageName);
		}
		
		final StringBuilder builder = new StringBuilder();
		builder.append(metaData.getDestinationDirectory()).append("/");
		builder.append(packageName);
		builder.append("/");
		builder.append(javaOutput.getClassName()).append(".java");
		
		return builder.toString();
	}
	
	private static boolean createPackageDirectory(final String packageDirectory, final String destinationDirectory) {
		final File packageDir = new File(destinationDirectory + "/" + packageDirectory);
		
        return packageDir.isDirectory() || packageDir.mkdirs();
	}
}
