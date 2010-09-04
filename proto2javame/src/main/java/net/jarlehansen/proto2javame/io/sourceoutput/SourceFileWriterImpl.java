package net.jarlehansen.proto2javame.io.sourceoutput;

import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.io.exception.ProtoFileException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class SourceFileWriterImpl implements SourceFileWriter {

    public SourceFileWriterImpl() {
    }

    public void writeJavaSourceFile(final InputMetaData metaData, final List<JavaFileOutput> javaOutputList) {

        for(JavaFileOutput javaOutput : javaOutputList) {

        BufferedWriter output = null;
            try {
                output = new BufferedWriter(new FileWriter(SourceWriterUtil.createDestinationFile(
                        metaData, javaOutput), false));

                output.write(javaOutput.getCompleteSource());
            } catch (IOException io) {
                throw new ProtoFileException("Error when trying to write file, filename: " + javaOutput.getClassName()
                        + ".java directory: " + metaData.getDestinationDirectory(), io);
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException io) {
                        System.out.println("Unable to close the BufferedWriter, " + io.getMessage());
                    }
                }
            }
        }
    }
}
