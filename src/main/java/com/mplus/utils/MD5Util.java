package com.mplus.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.shiro.codec.Hex;

/**
 * MD5工具类，加盐
 */
@Deprecated
public class MD5Util {

	/**
	 * 普通MD5
	 */
	public static String MD5(String text) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return "check jdk";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = text.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 盐值MD5
	 */
	public static String MD5Salt(String text) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();
//		text = md5Hex(text + salt);
//		char[] cs = new char[48];
//		for (int i = 0; i < 48; i += 3) {
//			cs[i] = text.charAt(i / 3 * 2);
//			char c = salt.charAt(i / 3);
//			cs[i + 1] = c;
//			cs[i + 2] = text.charAt(i / 3 * 2 + 1);
//		}
//		return new String(cs);
		return md5Hex(salt);
	}

	/**
	 * 校验加盐后是否和原文一致
	 */
	public static boolean verify(String text, String md5) {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		return md5Hex(text + salt).equals(new String(cs1));
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要
	 */
	@SuppressWarnings("static-access")
	private static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}
}
