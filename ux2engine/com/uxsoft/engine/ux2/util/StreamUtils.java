/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/**
 *
 * @author David
 */
public class StreamUtils {
	private static final Charset charset = Charset.forName("UTF-8");

	public static void writeByte(OutputStream out, int value) throws IOException {
		out.write((byte) value);
	}

	public static void writeShort(OutputStream out, int value) throws IOException {
		out.write((byte) (value & 0xFF));
		out.write((byte) ((value >>> 8) & 0xFF));
	}

	public static void writeInt(OutputStream out, int value) throws IOException {
		out.write((byte) (value & 0xFF));
		out.write((byte) ((value >>> 8) & 0xFF));
		out.write((byte) ((value >>> 16) & 0xFF));
		out.write((byte) ((value >>> 24) & 0xFF));
	}

	public static void writeLong(OutputStream out, long value) throws IOException {
		out.write((byte) (value & 0xFF));
		out.write((byte) ((value >>> 8) & 0xFF));
		out.write((byte) ((value >>> 16) & 0xFF));
		out.write((byte) ((value >>> 24) & 0xFF));
		out.write((byte) ((value >> 32) & 0xFF));
		out.write((byte) ((value >>> 40) & 0xFF));
		out.write((byte) ((value >>> 48) & 0xFF));
		out.write((byte) ((value >>> 56) & 0xFF));
	}

	public static void writeString(OutputStream out, String value) throws IOException {
		StreamUtils.writeShort(out, value.length());
		byte[] stringBytes = value.getBytes(charset);
		for (int x = 0; x < stringBytes.length; x++) {
			StreamUtils.writeByte(out, stringBytes[x] ^ 0x0A);
		}
	}

	public static byte readByte(InputStream in) throws IOException {
		return (byte) in.read();
	}

	public static short readShort(InputStream in) throws IOException {
        int byte1, byte2;

        byte1 = in.read();
        byte2 = in.read();
        return (short) ((byte2 << 8) + byte1);
	}

	public static int readInt(InputStream in) throws IOException {
        int byte1, byte2, byte3, byte4;

        byte1 = in.read();
        byte2 = in.read();
        byte3 = in.read();
        byte4 = in.read();
        return (byte4 << 24) + (byte3 << 16) + (byte2 << 8) + byte1;
	}

	public static long readLong(InputStream in) throws IOException {
        long byte1 = in.read();
        long byte2 = in.read();
        long byte3 = in.read();
        long byte4 = in.read();
        long byte5 = in.read();
        long byte6 = in.read();
        long byte7 = in.read();
        long byte8 = in.read();

        return (byte8 << 56) + (byte7 << 48) + (byte6 << 40) + (byte5 << 32) + (byte4 << 24) + (byte3 << 16)
               + (byte2 << 8) + byte1;
	}

	public static String readString(InputStream in) throws IOException {
		short stringLen = StreamUtils.readShort(in);
		byte[] stringData = new byte[stringLen];
		for (int x = 0; x < stringLen; x++) {
			stringData[x] = (byte) (StreamUtils.readByte(in) ^ 0x0A);
		}
		return new String(stringData, 0, stringData.length, charset);
	}

	public static byte readByte(RandomAccessFile in) throws IOException {
		return (byte) in.read();
	}

	public static short readShort(RandomAccessFile in) throws IOException {
        int byte1, byte2;

        byte1 = in.read();
        byte2 = in.read();
        return (short) ((byte2 << 8) + byte1);
	}

	public static int readInt(RandomAccessFile in) throws IOException {
        int byte1, byte2, byte3, byte4;

        byte1 = in.read();
        byte2 = in.read();
        byte3 = in.read();
        byte4 = in.read();
        return (byte4 << 24) + (byte3 << 16) + (byte2 << 8) + byte1;
	}

	public static long readLong(RandomAccessFile in) throws IOException {
        long byte1 = in.read();
        long byte2 = in.read();
        long byte3 = in.read();
        long byte4 = in.read();
        long byte5 = in.read();
        long byte6 = in.read();
        long byte7 = in.read();
        long byte8 = in.read();

        return (byte8 << 56) + (byte7 << 48) + (byte6 << 40) + (byte5 << 32) + (byte4 << 24) + (byte3 << 16)
               + (byte2 << 8) + byte1;
	}

	public static String readString(RandomAccessFile in) throws IOException {
		short stringLen = StreamUtils.readShort(in);
		byte[] stringData = new byte[stringLen];
		for (int x = 0; x < stringLen; x++) {
			stringData[x] = (byte) (StreamUtils.readByte(in) ^ 0x0A);
		}
		return new String(stringData, 0, stringData.length, charset);
	}

	public final static int memcmp(byte[] s1, int s1offset, byte[] s2, int s2offset) {

		int n = s1.length - s1offset;

		if (n > (s2.length-s2offset)) {
			n = s2.length-s2offset;
		}
		for (int i = 0; i < n; i++) {
			if (s1[i + s1offset] != s2[i + s2offset]) {
				return s1[i + s1offset] < s2[i + s2offset] ? -1 : 1;
			}
		}

		return 0;
	}

	public static final boolean readFromStream(InputStream in, byte[] buf, int offset, int len)
			throws IOException {
		int totalBytesRead = 0;
		int nbytes;

		while (totalBytesRead < len) {
			nbytes = in.read(buf, offset + totalBytesRead, len - totalBytesRead);
			if (nbytes < 0) {
				return false;
			}
			totalBytesRead += nbytes;
		}

		return true;
	}

	public static final boolean readFromStream(RandomAccessFile in, byte[] buf, int offset, int len)
			throws IOException {
		int totalBytesRead = 0;
		int nbytes;

		while (totalBytesRead < len) {
			nbytes = in.read(buf, offset + totalBytesRead, len - totalBytesRead);
			if (nbytes < 0) {
				return false;
			}
			totalBytesRead += nbytes;
		}

		return true;
	}
}
