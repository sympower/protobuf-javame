package net.jarlehansen.protobuf.javame.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.CustomListWriter;
import net.jarlehansen.protobuf.javame.SupportedDataTypes;
import net.jarlehansen.protobuf.javame.original.output.CodedOutputStream;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public class OutputWriter {
	private final byte[] dataHolder;
	private final OutputStream output;
	private final CodedOutputStream codedOutput;

	public OutputWriter(final byte[] dataHolder) {
		this(dataHolder, 0, dataHolder.length);
	}

	public OutputWriter(final byte[] dataHolder, int offset, int len) {
		this.dataHolder = dataHolder;
		output = null;

		codedOutput = CodedOutputStream.newInstance(dataHolder, offset, len);
	}

	public OutputWriter(final byte[] dataHolder, final OutputStream output) {
		this.output = output;
		this.dataHolder = dataHolder;

		codedOutput = CodedOutputStream.newInstance(this.dataHolder);
	}

	public void writeBoolean(final int id, final boolean value) throws IOException {
		codedOutput.writeBool(id, value);
	}

	public void writeByteString(final int id, final ByteString value) throws IOException {
		codedOutput.writeBytes(id, value);
	}

	public void writeDouble(final int id, final double value) throws IOException {
		codedOutput.writeDouble(id, value);
	}

	public void writeFloat(final int id, final float value) throws IOException {
		codedOutput.writeFloat(id, value);
	}

	public void writeInt(final int id, final int value) throws IOException {
		codedOutput.writeInt32(id, value);
	}

	public void writeLong(final int id, final long value) throws IOException {
		codedOutput.writeInt64(id, value);
	}

	public void writeString(final int id, final String value) throws IOException {
		codedOutput.writeString(id, value);
	}
	
	public void writeDelimitedSize(final int value) throws IOException {
		codedOutput.writeDelimitedSize(value);
	}
	
	public void writeMessage(final int id, final int value) throws IOException {
		codedOutput.writeMessage(id, value);
	}

	/**
	 * Try to avoid this since it involves lots of casts, may impact
	 * performance.
	 * 
	 * @param id
	 * @param dataType
	 * @param list
	 * @throws IOException
	 */
	public void writeList(final int id, final int dataType, final Vector list) throws IOException {
	    if(list != null) {
			switch (dataType) {
				case SupportedDataTypes.DATA_TYPE_BYTESTRING:
					for (int i = 0; i < list.size(); i++) {
						writeByteString(id, (ByteString) list.elementAt(i));
					}
					break;
				case SupportedDataTypes.DATA_TYPE_DOUBLE:
					for (int i = 0; i < list.size(); i++) {
						writeDouble(id, ((Double) list.elementAt(i)).doubleValue());
					}
					break;
				case SupportedDataTypes.DATA_TYPE_FLOAT:
					for (int i = 0; i < list.size(); i++) {
						writeFloat(id, ((Float) list.elementAt(i)).floatValue());
					}
					break;
				case SupportedDataTypes.DATA_TYPE_INT:
					for (int i = 0; i < list.size(); i++) {
						writeInt(id, ((Integer) list.elementAt(i)).intValue());
					}
					break;
				case SupportedDataTypes.DATA_TYPE_LONG:
					for (int i = 0; i < list.size(); i++) {
						writeLong(id, ((Long) list.elementAt(i)).longValue());
					}
					break;
				case SupportedDataTypes.DATA_TYPE_STRING:
					for (int i = 0; i < list.size(); i++) {
						writeString(id, (String) list.elementAt(i));
					}
					break;
				case SupportedDataTypes.DATA_TYPE_BOOLEAN:
					for (int i = 0; i < list.size(); i++) {
						writeBoolean(id, ((Boolean) list.elementAt(i)).booleanValue());
					}
					break;
				case SupportedDataTypes.DATA_TYPE_CUSTOM:
					for(int i = 0; i < list.size(); i++) {
						CustomListWriter customList = (CustomListWriter)list.elementAt(i);
						writeMessage(id, customList.computeSize());
						customList.writeFields(this);
					}
					break;
				default:
					throw new IllegalArgumentException("The data type was not found, the id used was " + dataType);
			}
	    }
	}

	public void writeData() throws IOException {
		if (output != null) {
			output.write(dataHolder);
			output.flush();
		}
	}
}
