package com.liy.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 資料加解密相關
 * MD5, SHA
 */
public class DigestUtils {

	/**
	 * 將資料透過MD5雜湊加密
	 * @param content
	 * @return
	 */
	public static String md5Hex(byte[] content) {
		MessageDigest md5;
		String digest = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			digest = bytesToHex(md5.digest(content));
		} catch (NoSuchAlgorithmException e) {
		}
		return digest;
	}

	/**
	 * 將資料透過SHA雜湊加密
	 * @param content
	 * @return
	 */
	public static String shaHex(byte[] content) {
		MessageDigest sha;
		String digest = "";
		try {
			sha = MessageDigest.getInstance("SHA");
			digest = bytesToHex(sha.digest(content));
		} catch (NoSuchAlgorithmException e) {
		}
		return digest;
	}
	
	/**
	 * 將資料透過SHA-256雜湊加密
	 * @param content
	 * @return
	 */
	public static byte[] sha256(String content) {
		return sha256(content.getBytes());
	}
	
	/**
	 * 將資料透過SHA-256雜湊加密
	 * @param content
	 * @return
	 */
	public static byte[] sha256(byte[] content) {
		
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-256");
			return sha.digest(content);
		} catch (NoSuchAlgorithmException e) {
		}
		
		return null;
	}
	
	/**
	 * 取得sha 256 hex string
	 * @param content
	 * @return
	 * @throws CryptException 
	 */
	public static String sha256String(String content) {
		return byteArray2HexString(sha256(content));
	}

	/**
	 * 將byte array轉成Hex的字串
	 * @param b
	 * @return
	 */
	private static String bytesToHex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; ++i) {
			sb.append((Integer.toHexString((b[i] & 0xFF) | 0x100)).substring(1, 3));
		}
		return sb.toString();
	}
	
	/**
	 * 將byte array資料轉為十六進位字串
	 * @param raw 待轉換之bye array
	 */
	public static String byteArray2HexString(byte[] raw) {
		StringBuffer sHex = new StringBuffer("");
		for (int i = 0; i < raw.length; i++) {
			sHex.append(byte2HexString(raw[i]));
		}
		return sHex.toString();
	}

	/**
	 * 將Byte資料轉換為十六進位字串
	 * @param raw
	 */
	public static String byte2HexString(byte raw) {
		StringBuffer sHex = new StringBuffer("");
		int iByte = (raw & 0xF0) >> 4;
		sHex.append(Character.forDigit(iByte, 16));
		iByte = (raw & 0x0F);
		sHex.append(Character.forDigit(iByte, 16));
		return sHex.toString();
	}
}
