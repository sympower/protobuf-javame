package com.google.protobuf.javame.output;

import java.io.IOException;
import java.util.Vector;

import com.google.protobuf.ByteString;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public interface OutputWriter {
	public void writeInt(final int id, final int value) throws IOException;
	public void writeString(final int id, final String value) throws IOException;
	public void writeBoolean(final int id, final boolean value) throws IOException;
	public void writeDouble(final int id, final double value) throws IOException;
	public void writeFloat(final int id, final float value) throws IOException;
	public void writeLong(final int id, final long value) throws IOException;
	public void writeByteString(final int id, final ByteString value) throws IOException;
	public void writeList(final int id, final int dataType, final Vector list) throws IOException;
	public void writeData() throws IOException;
}
