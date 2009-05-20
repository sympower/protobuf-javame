package net.jarlehansen.proto2javame.io.sourceoutput;

import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.io.exception.ProtoFileException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class SourceFileWriterImpl implements SourceFileWriter {

    private SourceFileWriterImpl() {
    }

    public static SourceFileWriter newInstance() {
        return new SourceFileWriterImpl();
    }

    public void writeJavaSourceFile(final InputMetaData metaData, final JavaFileOutput javaOutput) {
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
                    System.out.println("Problem closing the BufferedWriter, " + io.getMessage());
                }
            }
        }
    }
}
