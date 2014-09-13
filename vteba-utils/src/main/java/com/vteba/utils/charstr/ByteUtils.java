package com.vteba.utils.charstr;

import com.vteba.utils.serialize.Kryos;

public class ByteUtils {
	public static byte[][] toBytes(String... keys) {
		byte[][] bytes = new byte[keys.length][0];
		int i = 0;
		for (String value : keys) {
			bytes[i++] = value.getBytes();
		}
		return bytes;
	}

	public static byte[][] toBytes(Object... objects) {
		byte[][] bytes = new byte[objects.length][0];
		int i = 0;
		for (Object value : objects) {
			bytes[i++] = Kryos.toBytes(value);
		}
		return bytes;
	}

	/**
	 * 将int转为byte数组，字节数组的低位是整型的低字节位
	 * @param source
	 * @return int转换成的字节数组
	 */
	public static byte[] toBytes(int source) {
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; i++) {
			bytes[i] = (byte) (source >> 8 * i & 0xFF);
		}
		return bytes;
	}
	
	/**
	 * 将source转为长度为length的byte数组，字节数组的低位是整型的低字节位
	 * @param source 要转换的int
	 * @param length 字节长度
	 * @return int转换成的字节数组
	 */
	public static byte[] toBytes(int source, int length) {
		byte[] bytes = new byte[length];
		for (int i = 0; (i < 4) && (i < length); i++) {
			bytes[i] = (byte) (source >> 8 * i & 0xFF);
		}
		return bytes;
	}

	/**
	 * 将byte数组bytes转为一个整数，字节数组的低位是整型的低字节位
	 * @param bytes 要转换的字节数组
	 * @return 字节数组int值
	 */
	public static int toInt(byte[] bytes) {
		int result = 0;
		byte loop;
		for (int i = 0; i < 4; i++) {
			loop = bytes[i];
			result += (loop & 0xFF) << (8 * i);
		}
		return result;
	}

	/**
	 * int转换为字节数组
	 * @param res 要被转换的int值
	 * @return 转换后的字节数组
	 */
	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	/**
	 * 字节数组转换为int
	 * @param res 要被转换的字节数组
	 * @return 转换后的int值
	 */
	public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示按位或运算
				| ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	}

	public static void main(String[] args) {
		toBytes("a尹雷", "b");
		
		int i = 212123;
		byte[] b = toBytes(i, 4); // 整型到字节，

		System.out.println("212123 bin: " + Integer.toBinaryString(212123));// 212123的二进制表示
		System.out.println("212123 hex: " + Integer.toHexString(212123)); // 212123的十六进制表示

		for (int j = 0; j < 4; j++) {
			System.out.println("b[" + j + "]=" + b[j]);// 从低位到高位输出,java的byte范围是-128到127
		}

		int k = toInt(b);// 字节到整型，转换回来
		System.out.println("byte to int:" + k);

		byte[] aasd = int2byte(238237);
		int aaa = byte2int(aasd);
		System.out.println("238237:" + aaa);
	}
}
