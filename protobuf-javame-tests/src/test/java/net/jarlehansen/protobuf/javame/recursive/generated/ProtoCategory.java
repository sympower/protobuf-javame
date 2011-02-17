package net.jarlehansen.protobuf.javame.recursive.generated;
// Generated by proto2javame, Thu Feb 17 21:54:32 CET 2011.

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;

public final class ProtoCategory extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final int id;
	private static final int fieldNumberId = 1;

	private final String name;
	private static final int fieldNumberName = 2;

	private final java.util.Vector category;
	private static final int fieldNumberCategory = 3;


	public static Builder newBuilder() {
		return new Builder();
	}

	private ProtoCategory(final Builder builder) {
		if (builder.hasId && builder.hasName ) {
			this.id = builder.id;
			this.name = builder.name;
			this.category = builder.category;
		} else {
			throw new UninitializedMessageException("Not all required fields were included (false = not included in message), " + 
				" id:" + builder.hasId + " name:" + builder.hasName + "");
		}
	}

	public static class Builder {
		private int id;
		private boolean hasId = false;

		private String name;
		private boolean hasName = false;

		private java.util.Vector category = new java.util.Vector();
		private boolean hasCategory = false;


		private Builder() {
		}

		public Builder setId(final int id) {
			this.id = id;
			this.hasId = true;
			return this;
		}

		public Builder setName(final String name) {
			this.name = name;
			this.hasName = true;
			return this;
		}

		public Builder setCategory(final java.util.Vector category) {
			if(!hasCategory) {
				hasCategory = true;
			}
			this.category = category;
			return this;
		}


		public Builder addElementCategory(final ProtoCategory element) {
			if(!hasCategory) {
				hasCategory = true;
			}
			category.addElement(element);
			return this;
		}

		public ProtoCategory build() {
			return new ProtoCategory(this);
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public java.util.Vector getCategory() {
		return category;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "id = " + this.id + TAB;
		retValue += "name = " + this.name + TAB;
		retValue += "category = " + this.category + TAB;
		retValue += ")";

		return retValue;
	}

	// Override
	public int computeSize() {
		int totalSize = 0;
		totalSize += ComputeSizeUtil.computeIntSize(fieldNumberId, id);
		totalSize += ComputeSizeUtil.computeStringSize(fieldNumberName, name);
		totalSize += computeNestedMessageSize();

		return totalSize;
	}

	private int computeNestedMessageSize() {
		int messageSize = 0;
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberCategory, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, category);

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeInt(fieldNumberId, id);
		writer.writeString(fieldNumberName, name);
		writer.writeList(fieldNumberCategory, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, category);
		writer.writeData();
	}

	static ProtoCategory parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final ProtoCategory.Builder builder = ProtoCategory.newBuilder();

		while (nextFieldNumber > 0) {
			populateBuilderWithField(reader, builder, nextFieldNumber);
			nextFieldNumber = getNextFieldNumber(reader);
		}

		return builder.build();
	}
	static int getNextFieldNumber(final InputReader reader) throws IOException {
		return reader.getNextFieldNumber();
	}

	static boolean populateBuilderWithField(final InputReader reader, final Builder builder, final int fieldNumber) throws IOException {
		boolean fieldFound = true;
		switch (fieldNumber) {
			case fieldNumberId:
				builder.setId(reader.readInt(fieldNumber));
				break;
			case fieldNumberName:
				builder.setName(reader.readString(fieldNumber));
				break;
			case fieldNumberCategory:
				Vector vcCategory = reader.readMessages(fieldNumberCategory);
				for(int i = 0 ; i < vcCategory.size(); i++) {
					byte[] eachBinData = (byte[]) vcCategory.elementAt(i);
					ProtoCategory.Builder builderCategory = ProtoCategory.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolCategory = true;
					int nestedFieldCategory = -1;
					while(boolCategory) {
						nestedFieldCategory = getNextFieldNumber(innerInputReader);
						boolCategory = ProtoCategory.populateBuilderWithField(innerInputReader, builderCategory, nestedFieldCategory);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementCategory(builderCategory.build());
				}
				break;
		default:
			fieldFound = false;
		}
		return fieldFound;
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		ProtoCategory.unknownTagHandler = unknownTagHandler;
	}

	public static ProtoCategory parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static ProtoCategory parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static ProtoCategory parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}