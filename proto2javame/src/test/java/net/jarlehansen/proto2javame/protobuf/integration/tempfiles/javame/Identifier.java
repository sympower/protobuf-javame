package net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame;
// Generated by proto2javame, Sat Sep 04 21:22:20 CEST 2010.

import java.io.IOException;
import java.io.InputStream;
import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;

public final class Identifier extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final long id;
	private static final int fieldNumberId = 1;


	public static Builder newBuilder() {
		return new Builder();
	}

	private Identifier(final Builder builder) {
		if (builder.hasId ) {
			this.id = builder.id;
		} else {
			throw new UninitializedMessageException("Not all required fields were included (false = not included in message), " + 
				" id:" + builder.hasId + "");
		}
	}

	public static class Builder {
		private long id;
		private boolean hasId = false;


		private Builder() {
		}

		public Builder setId(final long id) {
			this.id = id;
			this.hasId = true;
			return this;
		}

		public Identifier build() {
			return new Identifier(this);
		}
	}

	public long getId() {
		return id;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "id = " + this.id + TAB;
		retValue += ")";

		return retValue;
	}

	// Override
	public int computeSize() {
		int totalSize = 0;
		totalSize += ComputeSizeUtil.computeLongSize(fieldNumberId, id);
		totalSize += computeNestedMessageSize();

		return totalSize;
	}

	private int computeNestedMessageSize() {
		int messageSize = 0;

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeLong(fieldNumberId, id);
		writer.writeData();
	}

	static Identifier parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final Identifier.Builder builder = Identifier.newBuilder();

		while (nextFieldNumber > 0) {
			populateBuilderWithField(reader, builder, nextFieldNumber);
			nextFieldNumber = getNextFieldNumber(reader);
		}

		return builder.build();
	}
	static int getNextFieldNumber(final InputReader reader) throws IOException {
		return reader.getNextFieldNumber();
	}

	static void populateBuilderWithField(final InputReader reader, final Builder builder, final int fieldNumber) throws IOException {
		switch (fieldNumber) {
			case fieldNumberId:
				builder.setId(reader.readLong(fieldNumber));
				break;
		default:
			reader.getPreviousTagDataTypeAndReadContent();
		}
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		Identifier.unknownTagHandler = unknownTagHandler;
	}

	public static Identifier parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static Identifier parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static Identifier parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}