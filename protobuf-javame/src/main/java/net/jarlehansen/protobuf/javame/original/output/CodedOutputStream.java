// Protocol Buffers - Google's data interchange format
// Copyright 2008 Google Inc.  All rights reserved.
// http://code.google.com/p/protobuf/
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
//     * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
//     * Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
//     * Neither the name of Google Inc. nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package net.jarlehansen.protobuf.javame.original.output;

import java.io.IOException;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.original.WireFormat;

/**
 * Encodes and writes protocol message fields.
 * 
 * <p>
 * This class contains two kinds of methods: methods that write specific
 * protocol message constructs and field types (e.g. {@link #writeTag} and
 * {@link #writeInt32}) and methods that write low-level values (e.g.
 * {@link #writeRawVarint32} and {@link #writeRawBytes}). If you are writing
 * encoded protocol messages, you should use the former methods, but if you are
 * writing some other format of your own design, use the latter.
 * 
 * <p>
 * This class is totally unsynchronized.
 * 
 * @author kneton@google.com Kenton Varda
 */
public final class CodedOutputStream {
	private final byte[] buffer;
	private final int limit;
	private int position;

	private CodedOutputStream(byte[] buffer, int offset, int length) {
		this.buffer = buffer;
		this.position = offset;
		this.limit = offset + length;
	}

	/**
	 * Create a new {@code CodedOutputStream} that writes directly to the given
	 * byte array. If more bytes are written than fit in the array,
	 * {@link OutOfSpaceException} will be thrown. Writing directly to a flat
	 * array is faster than writing to an {@code OutputStream}. See also
	 * {@link ByteString#newCodedBuilder}.
	 */
	public static CodedOutputStream newInstance(byte[] flatArray) {
		return newInstance(flatArray, 0, flatArray.length);
	}

	/**
	 * Create a new {@code CodedOutputStream} that writes directly to the given
	 * byte array slice. If more bytes are written than fit in the slice,
	 * {@link OutOfSpaceException} will be thrown. Writing directly to a flat
	 * array is faster than writing to an {@code OutputStream}. See also
	 * {@link ByteString#newCodedBuilder}.
	 */
	private static CodedOutputStream newInstance(byte[] flatArray, int offset, int length) {
		return new CodedOutputStream(flatArray, offset, length);
	}

	// -----------------------------------------------------------------

	/** Write a {@code double} field, including tag, to the stream. */
	public void writeDouble(int fieldNumber, double value) throws IOException {
		writeTag(fieldNumber, WireFormat.WIRETYPE_FIXED64);
		writeRawLittleEndian64(Double.doubleToLongBits(value));
	}

	/** Write a {@code float} field, including tag, to the stream. */
	public void writeFloat(int fieldNumber, float value) throws IOException {
		writeTag(fieldNumber, WireFormat.WIRETYPE_FIXED32);
		writeRawLittleEndian32(Float.floatToIntBits(value));
	}

	/** Write an {@code int64} field, including tag, to the stream. */
	public void writeInt64(int fieldNumber, long value) throws IOException {
		writeTag(fieldNumber, WireFormat.WIRETYPE_VARINT);
		writeRawVarint64(value);
	}

	/** Write an {@code int32} field, including tag, to the stream. */
	public void writeInt32(int fieldNumber, int value) throws IOException {
		writeTag(fieldNumber, WireFormat.WIRETYPE_VARINT);
		if (value >= 0) {
			writeRawVarint32(value);
		} else {
			// Must sign-extend.
			writeRawVarint64(value);
		}
	}
	
	public void writeDelimitedSize(int value) throws IOException {
		writeRawVarint32(value);
	}
	
	/** Write a {@code bool} field, including tag, to the stream. */
	public void writeBool(int fieldNumber, boolean value) throws IOException {
		writeTag(fieldNumber, WireFormat.WIRETYPE_VARINT);
		writeRawByte(value ? 1 : 0);
	}

	/** Write a {@code string} field, including tag, to the stream. */
	public void writeString(int fieldNumber, String value) throws IOException {
	    if (value == null)
            return;
	    
		writeTag(fieldNumber, WireFormat.WIRETYPE_LENGTH_DELIMITED);
		// Unfortunately there does not appear to be any way to tell Java to
		// encode
		// UTF-8 directly into our buffer, so we have to let it create its own
		// byte
		// array and then copy.
		byte[] bytes = value.getBytes("UTF-8");
		writeRawVarint32(bytes.length);
		writeRawBytes(bytes);
	}

	/** Write a {@code bytes} field, including tag, to the stream. */
	public void writeBytes(int fieldNumber, ByteString value) throws IOException {
	    if (value == null)
            return;
	    
		writeTag(fieldNumber, WireFormat.WIRETYPE_LENGTH_DELIMITED);
		byte[] bytes = value.toByteArray();
		writeRawVarint32(bytes.length);
		writeRawBytes(bytes);
	}
	  
	  /** Write an embedded message field, including tag, to the stream. */
	  public void writeMessage(final int fieldNumber, final int value)
	                           throws IOException {
	    writeTag(fieldNumber, WireFormat.WIRETYPE_LENGTH_DELIMITED);
	    writeRawVarint32(value);
	  }
	  
	  	  
	// =================================================================

	/**
	 * Compute the number of bytes that would be needed to encode a {@code
	 * double} field, including tag.
	 */
	public static int computeDoubleSize(int fieldNumber, double value) {
		return computeTagSize(fieldNumber) + LITTLE_ENDIAN_64_SIZE;
	}

	/**
	 * Compute the number of bytes that would be needed to encode a {@code
	 * float} field, including tag.
	 */
	public static int computeFloatSize(int fieldNumber, float value) {
		return computeTagSize(fieldNumber) + LITTLE_ENDIAN_32_SIZE;
	}

	/**
	 * Compute the number of bytes that would be needed to encode an {@code
	 * int64} field, including tag.
	 */
	public static int computeInt64Size(int fieldNumber, long value) {
		return computeTagSize(fieldNumber) + computeRawVarint64Size(value);
	}

	/**
	 * Compute the number of bytes that would be needed to encode an {@code
	 * int32} field, including tag.
	 */
	public static int computeInt32Size(int fieldNumber, int value) {
		if (value >= 0) {
			return computeTagSize(fieldNumber) + computeRawVarint32Size(value);
		} else {
			// Must sign-extend.
			return computeTagSize(fieldNumber) + 10;
		}
	}

	/**
	 * Compute the number of bytes that would be needed to encode a {@code bool}
	 * field, including tag.
	 */
	public static int computeBoolSize(int fieldNumber, boolean value) {
		return computeTagSize(fieldNumber) + 1;
	}

	/**
	 * Compute the number of bytes that would be needed to encode a {@code
	 * string} field, including tag.
	 */
	public static int computeStringSize(int fieldNumber, String value) {
	    if (value == null)
	        return 0;
	    
		try {
			byte[] bytes = value.getBytes("UTF-8");
			return computeTagSize(fieldNumber) + computeRawVarint32Size(bytes.length) + bytes.length;
		} catch (java.io.UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 not supported.");
		}
	}

	/**
	 * Compute the number of bytes that would be needed to encode a {@code
	 * bytes} field, including tag.
	 */
	public static int computeBytesSize(int fieldNumber, ByteString value) {
	    if (value == null)
            return 0;
	    
		return computeTagSize(fieldNumber) + computeRawVarint32Size(value.size()) + value.size();
	}

	/**
	 * If writing to a flat array, return the space left in the array.
	 */
	private int spaceLeft() {
		return limit - position;
	}

	/**
	 * Verifies that {@link #spaceLeft()} returns zero. It's common to create a
	 * byte array that is exactly big enough to hold a message, then write to it
	 * with a {@code CodedOutputStream}. Calling {@code checkNoSpaceLeft()}
	 * after writing verifies that the message was actually as big as expected,
	 * which can help catch bugs.
	 * 
	 * Currently only used in tests.
	 */
	void checkNoSpaceLeft() {
		if (spaceLeft() != 0) {
			throw new IllegalStateException("Did not write as much data as expected. Space left: " + spaceLeft());
		}
	}
	
	/** Write a single byte. */
	private void writeRawByte(byte value) throws IOException {
		buffer[position++] = value;
	}

	/** Write a single byte, represented by an integer value. */
	private void writeRawByte(int value) throws IOException {
		writeRawByte((byte) value);
	}

	/** Write an array of bytes. */
	private void writeRawBytes(byte[] value) throws IOException {
	    if (value == null)
            return;
	    
		writeRawBytes(value, 0, value.length);
	}

	/** Write part of an array of bytes. */
	private void writeRawBytes(byte[] value, int offset, int length) throws IOException {
	    if (value == null)
            return;
	    
		if (limit - position >= length) {
			// We have room in the current buffer.
			System.arraycopy(value, offset, buffer, position, length);
			position += length;
		} else {
			// Write extends past current buffer. Fill the rest of this buffer
			// and
			// flush.
			int bytesWritten = limit - position;
			System.arraycopy(value, offset, buffer, position, bytesWritten);
			offset += bytesWritten;
			length -= bytesWritten;
			position = limit;

			// Now deal with the rest.
			// Since we have an output stream, this is our buffer
			// and buffer offset == 0
			if (length <= limit) {
				// Fits in new buffer.
				System.arraycopy(value, offset, buffer, 0, length);
				position = length;
			}
		}
	}
	
	/** Encode and write a tag. */
	private void writeTag(int fieldNumber, int wireType) throws IOException {
		writeRawVarint32(WireFormat.makeTag(fieldNumber, wireType));
	}

	/** Compute the number of bytes that would be needed to encode a tag. */
	public static int computeTagSize(int fieldNumber) {
		return computeRawVarint32Size(WireFormat.makeTag(fieldNumber, 0));
	}

	/**
	 * Encode and write a varint. {@code value} is treated as unsigned, so it
	 * won't be sign-extended if negative.
	 */
	public void writeRawVarint32(int value) throws IOException {
		while (true) {
			if ((value & ~0x7F) == 0) {
				writeRawByte(value);
				return;
			} else {
				writeRawByte((value & 0x7F) | 0x80);
				value >>>= 7;
			}
		}
	}

	/**
	 * Compute the number of bytes that would be needed to encode a varint.
	 * {@code value} is treated as unsigned, so it won't be sign-extended if
	 * negative.
	 */
	public static int computeRawVarint32Size(int value) {
		if ((value & (0xffffffff << 7)) == 0)
			return 1;
		if ((value & (0xffffffff << 14)) == 0)
			return 2;
		if ((value & (0xffffffff << 21)) == 0)
			return 3;
		if ((value & (0xffffffff << 28)) == 0)
			return 4;
		return 5;
	}

	/** Encode and write a varint. */
	private void writeRawVarint64(long value) throws IOException {
		while (true) {
			if ((value & ~0x7FL) == 0) {
				writeRawByte((int) value);
				return;
			} else {
				writeRawByte(((int) value & 0x7F) | 0x80);
				value >>>= 7;
			}
		}
	}

	/** Compute the number of bytes that would be needed to encode a varint. */
	private static int computeRawVarint64Size(long value) {
		if ((value & (0xffffffffffffffffL << 7)) == 0)
			return 1;
		if ((value & (0xffffffffffffffffL << 14)) == 0)
			return 2;
		if ((value & (0xffffffffffffffffL << 21)) == 0)
			return 3;
		if ((value & (0xffffffffffffffffL << 28)) == 0)
			return 4;
		if ((value & (0xffffffffffffffffL << 35)) == 0)
			return 5;
		if ((value & (0xffffffffffffffffL << 42)) == 0)
			return 6;
		if ((value & (0xffffffffffffffffL << 49)) == 0)
			return 7;
		if ((value & (0xffffffffffffffffL << 56)) == 0)
			return 8;
		if ((value & (0xffffffffffffffffL << 63)) == 0)
			return 9;
		return 10;
	}

	/** Write a little-endian 32-bit integer. */
	private void writeRawLittleEndian32(int value) throws IOException {
		writeRawByte((value) & 0xFF);
		writeRawByte((value >> 8) & 0xFF);
		writeRawByte((value >> 16) & 0xFF);
		writeRawByte((value >> 24) & 0xFF);
	}

	private static final int LITTLE_ENDIAN_32_SIZE = 4;

	/** Write a little-endian 64-bit integer. */
	private void writeRawLittleEndian64(long value) throws IOException {
		writeRawByte((int) (value) & 0xFF);
		writeRawByte((int) (value >> 8) & 0xFF);
		writeRawByte((int) (value >> 16) & 0xFF);
		writeRawByte((int) (value >> 24) & 0xFF);
		writeRawByte((int) (value >> 32) & 0xFF);
		writeRawByte((int) (value >> 40) & 0xFF);
		writeRawByte((int) (value >> 48) & 0xFF);
		writeRawByte((int) (value >> 56) & 0xFF);
	}

	public static final int LITTLE_ENDIAN_64_SIZE = 8;
}
