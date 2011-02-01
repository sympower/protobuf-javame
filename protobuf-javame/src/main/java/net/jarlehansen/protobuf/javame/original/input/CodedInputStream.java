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

package net.jarlehansen.protobuf.javame.original.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import net.jarlehansen.protobuf.javame.ByteString;
import net.jarlehansen.protobuf.javame.original.WireFormat;

/**
 * Reads and decodes protocol message fields.
 * 
 * This class contains two kinds of methods: methods that read specific protocol
 * message constructs and field types (e.g. {@link #readTag()} and
 * {@link #readInt32()}) and methods that read low-level values (e.g.
 * {@link #readRawVarint32()} and {@link #readRawBytes}). If you are reading
 * encoded protocol messages, you should use the former methods, but if you are
 * reading some other format of your own design, use the latter.
 * 
 * @author kenton@google.com Kenton Varda
 */
public final class CodedInputStream {

	/**
	 * Create a new CodedInputStream wrapping the given InputStream.
	 */
	public static CodedInputStream newInstance(InputStream input) {
		return new CodedInputStream(input);
	}

	/**
	 * Create a new CodedInputStream wrapping the given byte array.
	 */
	public static CodedInputStream newInstance(byte[] buf) {
		return new CodedInputStream(buf, 0, buf.length);
	}

	// -----------------------------------------------------------------

	/**
	 * Attempt to read a field tag, returning zero if we have reached EOF.
	 * Protocol message parsers use this to read tags, since a protocol message
	 * may legally end wherever a tag occurs, and zero is not a valid tag
	 * number.
	 */
	public int readTag() throws IOException {
		if (bufferPos == bufferSize && !refillBuffer(false)) {
			lastTag = 0;
			return 0;
		}

		lastTag = readRawVarint32();
		if (lastTag == 0) {
			// If we actually read zero, that's not a valid tag.
			throw InvalidProtocolBufferException.invalidTag();
		}

		return lastTag;
	}

	// -----------------------------------------------------------------

	/** Read a {@code double} field value from the stream. */
	public double readDouble() throws IOException {
		return Double.longBitsToDouble(readRawLittleEndian64());
	}

	/** Read a {@code float} field value from the stream. */
	public float readFloat() throws IOException {
		return Float.intBitsToFloat(readRawLittleEndian32());
	}

	/** Read an {@code int64} field value from the stream. */
	public long readInt64() throws IOException {
		return readRawVarint64();
	}

	/** Read an {@code int32} field value from the stream. */
	public int readInt32() throws IOException {
		return readRawVarint32();
	}

	/** Read a {@code bool} field value from the stream. */
	public boolean readBool() throws IOException {
		return readRawVarint32() != 0;
	}
	 
	public int readMessageStart() throws IOException {
		int rawVarint32 = readRawVarint32();
		return rawVarint32;
	}
	
	/**
	 * Use Breadth First Search (BFS) Algorithm to get all nested messages
	 * @param fieldNumber
	 * @return
	 * @throws IOException
	 */
	public Vector readMessages(int currentFieldNumber) throws IOException
	{
		Vector chunks = new Vector();
		int eachChunkSize = readRawVarint32(); 
		byte[] bChunk = new byte[eachChunkSize];
		System.arraycopy(buffer, bufferPos, bChunk, 0, eachChunkSize);
		//chunks.add(bChunk);//RIM do not support add()
		chunks.addElement(bChunk);
		bufferPos = bufferPos + eachChunkSize;
		int lastPos = bufferPos;
		if(bufferPos == bufferSize)
		{
			return chunks;
		}
		int[] tmp = readInnerRawVarint32(lastPos);
		int nextFieldNumber = tmp[0];
		while(WireFormat.getTagFieldNumber(nextFieldNumber) == currentFieldNumber)
		{
			bufferPos = tmp[1];
			eachChunkSize = readRawVarint32();
			byte[] eachChunk = new byte[eachChunkSize]; //TODO check : bChunk = new byte[eachChunkSize];
			System.arraycopy(buffer, bufferPos, eachChunk, 0, eachChunkSize);
			//chunks.add(eachChunk);//RIM do not support add()
			chunks.addElement(eachChunk);
			bufferPos = bufferPos + eachChunkSize;
			if(bufferPos == bufferSize)
			{
			    break;
			}
			lastPos = bufferPos;
			tmp = readInnerRawVarint32(lastPos);
			nextFieldNumber = tmp[0];
		}
		return chunks;
	}
	
	/** Read a {@code string} field value from the stream. */
	public String readString() throws IOException {
		int size = readRawVarint32();
		if (size < bufferSize - bufferPos && size > 0) {
			// Fast path: We already have the bytes in a contiguous buffer, so
			// just copy directly from it.
			String result = new String(buffer, bufferPos, size, "UTF-8");
			bufferPos += size;
			return result;
		} else {
			// Slow path: Build a byte array first then copy it.
			return new String(readRawBytes(size), "UTF-8");
		}
	}

	/** Read a {@code bytes} field value from the stream. */
	public ByteString readBytes() throws IOException {
		int size = readRawVarint32();
		if (size < bufferSize - bufferPos && size > 0) {
			// Fast path: We already have the bytes in a contiguous buffer, so
			// just copy directly from it.
			ByteString result = ByteString.copyFrom(buffer, bufferPos, size);
			bufferPos += size;
			return result;
		} else {
			// Slow path: Build a byte array first then copy it.
			return ByteString.copyFrom(readRawBytes(size));
		}
	}

	public static int readDelimitedSize(final InputStream input) throws IOException {
		return CodedInputStream.readRawVarint32(input);
	}

	/**
	 * Reads a varint from the input one byte at a time, so that it does not
	 * read any bytes after the end of the varint. If you simply wrapped the
	 * stream in a CodedInputStream and used
	 * {@link #readRawVarint32(InputStream)} then you would probably end up
	 * reading past the end of the varint since CodedInputStream buffers its
	 * input.
	 */
	private static int readRawVarint32(InputStream input) throws IOException {
		int result = 0;
		int offset = 0;
		for (; offset < 32; offset += 7) {
			int b = input.read();
			if (b == -1) {
				throw InvalidProtocolBufferException.truncatedMessage();
			}
			result |= (b & 0x7f) << offset;
			if ((b & 0x80) == 0) {
				return result;
			}
		}
		// Keep reading up to 64 bits.
		for (; offset < 64; offset += 7) {
			int b = input.read();
			if (b == -1) {
				throw InvalidProtocolBufferException.truncatedMessage();
			}
			if ((b & 0x80) == 0) {
				return result;
			}
		}
		throw InvalidProtocolBufferException.malformedVarint();
	}

    /**
     * Read Int value without moving buffer position
     * @param lastPos : the last position in buffer array
     * @return
     * @throws IOException
     */
	private int[] readInnerRawVarint32(int lastPos) throws IOException 
	{
		byte tmp = readRawByte(lastPos);
		lastPos++;
		if (tmp >= 0) {
			return new int[]{tmp, lastPos};
		}
		int result = tmp & 0x7f;
		if ((tmp = readRawByte(lastPos)) >= 0) {
			lastPos++;
			result |= tmp << 7;
		} else {
			result |= (tmp & 0x7f) << 7;
			if ((tmp = readRawByte(lastPos)) >= 0) {
				lastPos++;
				result |= tmp << 14;
			} else {
				result |= (tmp & 0x7f) << 14;
				if ((tmp = readRawByte(lastPos)) >= 0) {
					lastPos++;
					result |= tmp << 21;
				} else {
					result |= (tmp & 0x7f) << 21;
					result |= (tmp = readRawByte(lastPos)) << 28;
					lastPos++;
					if (tmp < 0) {
						// Discard upper 32 bits.
						for (int i = 0; i < 5; i++) {
							if (readRawByte(lastPos) >= 0){
								lastPos++;
								return new int[]{tmp, lastPos};
							}
						}
						throw InvalidProtocolBufferException.malformedVarint();
					}
				}
			}
		}
		return new int[]{result, lastPos};
	}
	
	private byte readRawByte(int startPos) throws IOException {
		return buffer[startPos];
	}
	// =================================================================
	
	/**
	 * Read a raw Varint from the stream. If larger than 32 bits, discard the
	 * upper bits.
	 */
	private int readRawVarint32() throws IOException {
		byte tmp = readRawByte();
		if (tmp >= 0) {
			return tmp;
		}
		int result = tmp & 0x7f;
		if ((tmp = readRawByte()) >= 0) {
			result |= tmp << 7;
		} else {
			result |= (tmp & 0x7f) << 7;
			if ((tmp = readRawByte()) >= 0) {
				result |= tmp << 14;
			} else {
				result |= (tmp & 0x7f) << 14;
				if ((tmp = readRawByte()) >= 0) {
					result |= tmp << 21;
				} else {
					result |= (tmp & 0x7f) << 21;
					result |= (tmp = readRawByte()) << 28;
					if (tmp < 0) {
						// Discard upper 32 bits.
						for (int i = 0; i < 5; i++) {
							if (readRawByte() >= 0)
								return result;
						}
						throw InvalidProtocolBufferException.malformedVarint();
					}
				}
			}
		}
		return result;
	}

	/** Read a raw Varint from the stream. */
	public long readRawVarint64() throws IOException {
		int shift = 0;
		long result = 0;
		while (shift < 64) {
			byte b = readRawByte();
			result |= (long) (b & 0x7F) << shift;
			if ((b & 0x80) == 0)
				return result;
			shift += 7;
		}
		throw InvalidProtocolBufferException.malformedVarint();
	}

	/** Read a 32-bit little-endian integer from the stream. */
	int readRawLittleEndian32() throws IOException {
		byte b1 = readRawByte();
		byte b2 = readRawByte();
		byte b3 = readRawByte();
		byte b4 = readRawByte();
		return (((int) b1 & 0xff)) | (((int) b2 & 0xff) << 8) | (((int) b3 & 0xff) << 16) | (((int) b4 & 0xff) << 24);
	}

	/** Read a 64-bit little-endian integer from the stream. */
	long readRawLittleEndian64() throws IOException {
		byte b1 = readRawByte();
		byte b2 = readRawByte();
		byte b3 = readRawByte();
		byte b4 = readRawByte();
		byte b5 = readRawByte();
		byte b6 = readRawByte();
		byte b7 = readRawByte();
		byte b8 = readRawByte();
		return (((long) b1 & 0xff)) | (((long) b2 & 0xff) << 8) | (((long) b3 & 0xff) << 16)
				| (((long) b4 & 0xff) << 24) | (((long) b5 & 0xff) << 32) | (((long) b6 & 0xff) << 40)
				| (((long) b7 & 0xff) << 48) | (((long) b8 & 0xff) << 56);
	}

	// -----------------------------------------------------------------

	private byte[] buffer;
	private int bufferSize;
	private int bufferSizeAfterLimit = 0;
	private int bufferPos;
	private InputStream input;
	private int lastTag = 0;
	
	/**
	 * The total number of bytes read before the current buffer. The total bytes
	 * read up to the current position can be computed as
	 * {@code totalBytesRetired + bufferPos}.
	 */
	private int totalBytesRetired = 0;

	/** The absolute position of the end of the current message. */
	private int currentLimit = Integer.MAX_VALUE;

	/** See setSizeLimit() */
	private int sizeLimit = DEFAULT_SIZE_LIMIT;

	private static final int DEFAULT_SIZE_LIMIT = 64 << 20; // 64MB
	private static final int BUFFER_SIZE = 2048; // Default value = 4096

	private CodedInputStream(byte[] buffer, int off, int len) {
		this.buffer = buffer;
		this.bufferSize = off + len;
		this.bufferPos = off;
		this.input = null;
	}

	private CodedInputStream(InputStream input) {
		this.buffer = new byte[BUFFER_SIZE];
		this.bufferSize = 0;
		this.bufferPos = 0;
		this.input = input;
	}

	private void recomputeBufferSizeAfterLimit() {
		bufferSize += bufferSizeAfterLimit;
		int bufferEnd = totalBytesRetired + bufferSize;
		if (bufferEnd > currentLimit) {
			// Limit is in current buffer.
			bufferSizeAfterLimit = bufferEnd - currentLimit;
			bufferSize -= bufferSizeAfterLimit;
		} else {
			bufferSizeAfterLimit = 0;
		}
	}

	/**
	 * Called with {@code this.buffer} is empty to read more bytes from the
	 * input. If {@code mustSucceed} is true, refillBuffer() gurantees that
	 * either there will be at least one byte in the buffer when it returns or
	 * it will throw an exception. If {@code mustSucceed} is false,
	 * refillBuffer() returns false if no more bytes were available.
	 */
	private boolean refillBuffer(boolean mustSucceed) throws IOException {
		if (bufferPos < bufferSize) {
			throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
		}

		if (totalBytesRetired + bufferSize == currentLimit) {
			// Oops, we hit a limit.
			if (mustSucceed) {
				throw InvalidProtocolBufferException.truncatedMessage();
			} else {
				return false;
			}
		}

		totalBytesRetired += bufferSize;

		bufferPos = 0;
		bufferSize = (input == null) ? -1 : input.read(buffer);
		if (bufferSize == -1) {
			bufferSize = 0;
			if (mustSucceed) {
				throw InvalidProtocolBufferException.truncatedMessage();
			} else {
				return false;
			}
		} else {
			recomputeBufferSizeAfterLimit();
			int totalBytesRead = totalBytesRetired + bufferSize + bufferSizeAfterLimit;
			if (totalBytesRead > sizeLimit || totalBytesRead < 0) {
				throw InvalidProtocolBufferException.sizeLimitExceeded();
			}
			return true;
		}
	}

	/**
	 * Read one byte from the input.
	 * 
	 * @throws InvalidProtocolBufferException
	 *             The end of the stream or the current limit was reached.
	 */
	private byte readRawByte() throws IOException {
		if (bufferPos == bufferSize) {
			refillBuffer(true);
		}
		return buffer[bufferPos++];
	}

	/**
	 * Read a fixed size of bytes from the input.
	 * 
	 * @throws InvalidProtocolBufferException
	 *             The end of the stream or the current limit was reached.
	 */
	private byte[] readRawBytes(int size) throws IOException {
		if (size < 0) {
			throw InvalidProtocolBufferException.negativeSize();
		}

		if (totalBytesRetired + bufferPos + size > currentLimit) {
			// Read to the end of the stream anyway.
			skipRawBytes(currentLimit - totalBytesRetired - bufferPos);
			// Then fail.
			throw InvalidProtocolBufferException.truncatedMessage();
		}

		if (size <= bufferSize - bufferPos) {
			// We have all the bytes we need already.
			byte[] bytes = new byte[size];
			System.arraycopy(buffer, bufferPos, bytes, 0, size);
			bufferPos += size;
			return bytes;
		} else if (size < BUFFER_SIZE) {
			// Reading more bytes than are in the buffer, but not an excessive
			// number
			// of bytes. We can safely allocate the resulting array ahead of
			// time.

			// First copy what we have.
			byte[] bytes = new byte[size];
			int pos = bufferSize - bufferPos;
			System.arraycopy(buffer, bufferPos, bytes, 0, pos);
			bufferPos = bufferSize;

			// We want to use refillBuffer() and then copy from the buffer into
			// our
			// byte array rather than reading directly into our byte array
			// because
			// the input may be unbuffered.
			refillBuffer(true);

			while (size - pos > bufferSize) {
				System.arraycopy(buffer, 0, bytes, pos, bufferSize);
				pos += bufferSize;
				bufferPos = bufferSize;
				refillBuffer(true);
			}

			System.arraycopy(buffer, 0, bytes, pos, size - pos);
			bufferPos = size - pos;

			return bytes;
		} else {
			// The size is very large. For security reasons, we can't allocate
			// the
			// entire byte array yet. The size comes directly from the input, so
			// a
			// maliciously-crafted message could provide a bogus very large size
			// in
			// order to trick the app into allocating a lot of memory. We avoid
			// this
			// by allocating and reading only a small chunk at a time, so that
			// the
			// malicious message must actually *be* extremely large to cause
			// problems. Meanwhile, we limit the allowed size of a message
			// elsewhere.

			// Remember the buffer markers since we'll have to copy the bytes
			// out of
			// it later.
			int originalBufferPos = bufferPos;
			int originalBufferSize = bufferSize;

			// Mark the current buffer consumed.
			totalBytesRetired += bufferSize;
			bufferPos = 0;
			bufferSize = 0;

			// Read all the rest of the bytes we need.
			int sizeLeft = size - (originalBufferSize - originalBufferPos);
			Vector chunks = new Vector();

			while (sizeLeft > 0) {
				byte[] chunk = new byte[Math.min(sizeLeft, BUFFER_SIZE)];
				int pos = 0;
				while (pos < chunk.length) {
					int n = (input == null) ? -1 : input.read(chunk, pos, chunk.length - pos);
					if (n == -1) {
						throw InvalidProtocolBufferException.truncatedMessage();
					}
					totalBytesRetired += n;
					pos += n;
				}
				sizeLeft -= chunk.length;

				chunks.addElement(chunk);
			}

			// OK, got everything. Now concatenate it all into one buffer.
			byte[] bytes = new byte[size];

			// Start by copying the leftover bytes from this.buffer.
			int pos = originalBufferSize - originalBufferPos;
			System.arraycopy(buffer, originalBufferPos, bytes, 0, pos);

			// And now all the chunks.
			for (int i = 0; i < chunks.size(); i++) {
				byte[] chunk = (byte[]) chunks.elementAt(i);
				System.arraycopy(chunk, 0, bytes, pos, chunk.length);
				pos += chunk.length;
			}

			// Done.
			return bytes;
		}
	}

	/**
	 * Reads and discards {@code size} bytes.
	 * 
	 * @throws InvalidProtocolBufferException
	 *             The end of the stream or the current limit was reached.
	 */
	private void skipRawBytes(int size) throws IOException {
		if (size < 0) {
			throw InvalidProtocolBufferException.negativeSize();
		}

		if (totalBytesRetired + bufferPos + size > currentLimit) {
			// Read to the end of the stream anyway.
			skipRawBytes(currentLimit - totalBytesRetired - bufferPos);
			// Then fail.
			throw InvalidProtocolBufferException.truncatedMessage();
		}

		if (size < bufferSize - bufferPos) {
			// We have all the bytes we need already.
			bufferPos += size;
		} else {
			// Skipping more bytes than are in the buffer. First skip what we
			// have.
			int pos = bufferSize - bufferPos;
			totalBytesRetired += pos;
			bufferPos = 0;
			bufferSize = 0;

			// Then skip directly from the InputStream for the rest.
			while (pos < size) {
				int n = (input == null) ? -1 : (int) input.skip(size - pos);
				if (n <= 0) {
					throw InvalidProtocolBufferException.truncatedMessage();
				}
				pos += n;
				totalBytesRetired += n;
			}
		}
	}
}
