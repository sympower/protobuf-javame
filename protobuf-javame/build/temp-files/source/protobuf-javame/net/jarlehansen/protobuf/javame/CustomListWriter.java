package net.jarlehansen.protobuf.javame;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.output.OutputWriter;

public interface CustomListWriter {
	int computeSize();
	void writeFields(final OutputWriter outputWriter) throws IOException;
}
