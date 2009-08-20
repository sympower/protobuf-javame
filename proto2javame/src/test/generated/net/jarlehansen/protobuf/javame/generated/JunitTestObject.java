package net.jarlehansen.protobuf.javame.generated;
// Generated by proto2javame, Thu Aug 20 09:55:48 CEST 2009. DO NOT EDIT!

import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import net.jarlehansen.protobuf.javame.factory.JavaMeProtoFactory;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.output.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.util.ComputeSizeUtil;

import java.io.IOException;
import java.io.InputStream;

public final class JunitTestObject extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final int id;
	private static final int fieldNumberId = 1;

	private final long longNumber;
	private static final int fieldNumberLongNumber = 2;

	private final String name;
	private static final int fieldNumberName = 3;
	private final boolean hasName;

	private final java.util.Vector addressList;
	private static final int fieldNumberAddressList = 4;

	private final net.jarlehansen.protobuf.javame.ByteString bytesObject;
	private static final int fieldNumberBytesObject = 5;

	private final float floatObject;
	private static final int fieldNumberFloatObject = 6;

	private final boolean boolObj;
	private static final int fieldNumberBoolObj = 7;
	private final boolean hasBoolObj;

	private final double amount;
	private static final int fieldNumberAmount = 8;


	public static Builder newBuilder() {
		return new Builder();
	}

	private JunitTestObject(final Builder builder) {
		if (builder.hasId && builder.hasLongNumber && builder.hasBytesObject && builder.hasFloatObject && builder.hasAmount ) {
			this.id = builder.id;
			this.longNumber = builder.longNumber;
			this.name = builder.name;
			this.hasName = builder.hasName;
			this.addressList = builder.addressList;
			this.bytesObject = builder.bytesObject;
			this.floatObject = builder.floatObject;
			this.boolObj = builder.boolObj;
			this.hasBoolObj = builder.hasBoolObj;
			this.amount = builder.amount;
		} else {
			throw new UninitializedMessageException("Not all required fields were included, " + 
				" id:" + builder.hasId + " longNumber:" + builder.hasLongNumber + " bytesObject:" + builder.hasBytesObject + " floatObject:" + builder.hasFloatObject + " amount:" + builder.hasAmount + ", all fields that are false are mandatory but were not added");
		}
	}

	public static class Builder {
		private int id;
		private boolean hasId = false;

		private long longNumber;
		private boolean hasLongNumber = false;

		private String name;
		private boolean hasName = false;

		private java.util.Vector addressList = new java.util.Vector();
		private boolean hasAddressList = false;

		private net.jarlehansen.protobuf.javame.ByteString bytesObject;
		private boolean hasBytesObject = false;

		private float floatObject;
		private boolean hasFloatObject = false;

		private boolean boolObj;
		private boolean hasBoolObj = false;

		private double amount;
		private boolean hasAmount = false;


		private Builder() {
		}

		public Builder setId(final int id) {
			this.id = id;
			this.hasId = true;
			return this;
		}

		public Builder setLongNumber(final long longNumber) {
			this.longNumber = longNumber;
			this.hasLongNumber = true;
			return this;
		}

		public Builder setName(final String name) {
			this.name = name;
			this.hasName = true;
			return this;
		}

		public Builder setAddressList(final java.util.Vector addressList) {
			if(!hasAddressList) {
				hasAddressList = true;
			}
			this.addressList = addressList;
			return this;
		}


		public Builder addElementAddressList(final String element) {
			if(!hasAddressList) {
				hasAddressList = true;
			}
			addressList.addElement(element);
			return this;
		}

		public Builder setBytesObject(final net.jarlehansen.protobuf.javame.ByteString bytesObject) {
			this.bytesObject = bytesObject;
			this.hasBytesObject = true;
			return this;
		}

		public Builder setFloatObject(final float floatObject) {
			this.floatObject = floatObject;
			this.hasFloatObject = true;
			return this;
		}

		public Builder setBoolObj(final boolean boolObj) {
			this.boolObj = boolObj;
			this.hasBoolObj = true;
			return this;
		}

		public Builder setAmount(final double amount) {
			this.amount = amount;
			this.hasAmount = true;
			return this;
		}

		public JunitTestObject build() {
			return new JunitTestObject(this);
		}
	}

	public int getId() {
		return id;
	}

	public long getLongNumber() {
		return longNumber;
	}

	public String getName() {
		return name;
	}

	public boolean hasName() {
		return hasName;
	}

	public java.util.Vector getAddressList() {
		return addressList;
	}

	public net.jarlehansen.protobuf.javame.ByteString getBytesObject() {
		return bytesObject;
	}

	public float getFloatObject() {
		return floatObject;
	}

	public boolean getBoolObj() {
		return boolObj;
	}

	public boolean hasBoolObj() {
		return hasBoolObj;
	}

	public double getAmount() {
		return amount;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "id = " + this.id + TAB;
		retValue += "longNumber = " + this.longNumber + TAB;
		if(hasName) retValue += "name = " + this.name + TAB;
		retValue += "addressList = " + this.addressList + TAB;
		retValue += "bytesObject = " + this.bytesObject + TAB;
		retValue += "floatObject = " + this.floatObject + TAB;
		if(hasBoolObj) retValue += "boolObj = " + this.boolObj + TAB;
		retValue += "amount = " + this.amount + TAB;
		retValue += ")";

		return retValue;
	}

	// Override
	protected int computeSize() {
		int totalSize = 0;
		totalSize += ComputeSizeUtil.computeIntSize(fieldNumberId, id);
		totalSize += ComputeSizeUtil.computeLongSize(fieldNumberLongNumber, longNumber);
		if(hasName) totalSize += ComputeSizeUtil.computeStringSize(fieldNumberName, name);
		totalSize += ComputeSizeUtil.computeListSize(fieldNumberAddressList, net.jarlehansen.protobuf.javame.util.SupportedDataTypes.DATA_TYPE_STRING, addressList);
		totalSize += ComputeSizeUtil.computeByteStringSize(fieldNumberBytesObject, bytesObject);
		totalSize += ComputeSizeUtil.computeFloatSize(fieldNumberFloatObject, floatObject);
		if(hasBoolObj) totalSize += ComputeSizeUtil.computeBooleanSize(fieldNumberBoolObj, boolObj);
		totalSize += ComputeSizeUtil.computeDoubleSize(fieldNumberAmount, amount);

		return totalSize;
	}

	// Override
	protected void writeFields(final OutputWriter writer) throws IOException {
		writer.writeInt(fieldNumberId, id);
		writer.writeLong(fieldNumberLongNumber, longNumber);
		if(hasName) writer.writeString(fieldNumberName, name);
		writer.writeList(fieldNumberAddressList, net.jarlehansen.protobuf.javame.util.SupportedDataTypes.DATA_TYPE_STRING, addressList);
		writer.writeByteString(fieldNumberBytesObject, bytesObject);
		writer.writeFloat(fieldNumberFloatObject, floatObject);
		if(hasBoolObj) writer.writeBoolean(fieldNumberBoolObj, boolObj);
		writer.writeDouble(fieldNumberAmount, amount);
		writer.writeData();
	}

	private static JunitTestObject parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = reader.getNextFieldNumber();
		final JunitTestObject.Builder builder = new JunitTestObject.Builder();

		while (nextFieldNumber > 0) {
			switch (nextFieldNumber) {
			case fieldNumberId:
				builder.setId(reader.readInt(nextFieldNumber));
				break;
			case fieldNumberLongNumber:
				builder.setLongNumber(reader.readLong(nextFieldNumber));
				break;
			case fieldNumberName:
				builder.setName(reader.readString(nextFieldNumber));
				break;
			case fieldNumberAddressList:
				builder.addElementAddressList(reader.readString(nextFieldNumber));
				break;
			case fieldNumberBytesObject:
				builder.setBytesObject(reader.readByteString(nextFieldNumber));
				break;
			case fieldNumberFloatObject:
				builder.setFloatObject(reader.readFloat(nextFieldNumber));
				break;
			case fieldNumberBoolObj:
				builder.setBoolObj(reader.readBoolean(nextFieldNumber));
				break;
			case fieldNumberAmount:
				builder.setAmount(reader.readDouble(nextFieldNumber));
				break;
			default:
				 reader.getPreviousTagDataTypeAndReadContent();
			}

			nextFieldNumber = reader.getNextFieldNumber();
		}

		return builder.build();
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		JunitTestObject.unknownTagHandler = unknownTagHandler;
	}

	public static JunitTestObject parseFrom(final byte[] data) throws IOException {
		return parseFields(JavaMeProtoFactory.createInputUtil(data, unknownTagHandler));
	}

	public static JunitTestObject parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(JavaMeProtoFactory.createInputUtil(inputStream, unknownTagHandler));
	}

	public static JunitTestObject parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(JavaMeProtoFactory.createInputUtil(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}