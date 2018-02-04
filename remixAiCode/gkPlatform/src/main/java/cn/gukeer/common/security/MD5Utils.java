package cn.gukeer.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MD5Utils {
	private static final String PASSWORD = "Se8/3HpY";

	public static boolean validate(String text, String sign) {
		if (text == null || text.isEmpty()) {
			return false;
		}
		if (sign == null || sign.length() != 32) {
			return false;
		}
		text = text + PASSWORD;
		if (sign.equals(md5(text))) {
			return true;
		}
		return false;
	}

	public static String sign(String text) {
		if (text == null || text.isEmpty()) {
			throw new RuntimeException("input text cannot be empty");
		}
		String sign = md5(text + PASSWORD);
		return sign;
	}

	public static List<String> Md5(String plainText) {
		List<String> reuslt = new ArrayList<String>();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			reuslt.add(buf.toString());// 32
			reuslt.add(buf.toString().substring(8, 24));// 16
			reuslt.add(buf.toString().substring(12, 20));
			return reuslt;

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getDadaSignature(String tocken, String time, String data) {
		List<String> list = new ArrayList<String>();
		list.add(tocken);
		list.add(time);
		list.add(data);
		StringBuffer sb = new StringBuffer();
		Collections.sort(list);
		for (String string : list) {
			sb.append(string);
		}
		return Md5(sb.toString()).get(0);
	}

	public static String md5(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}

			return md5StrBuff.toString();

		} catch (Exception e) {
			throw new RuntimeException("md5 failed!");
		}

	}

	public static void main(String[] args) {
		// String udid = "3f0e4d6b-0f7c-46c3-b6b0-5477447606de";
		// String sign = MD5Utils.sign(udid).toUpperCase();
		// System.out.println(sign);
		//
		String md5 = MD5Utils.md5(
				"id=21089397&appkey=7323fb1fae8249659a08b0ab70022c2d&oncestr=570ba8ff6c62478c9f52d3c4f6385474&secret=3c3ed7574654433bbdb14b39947d3ef9");

//		System.out.println(MD5Utils.md5("12"));
		// System.out.println("sign validate : " + MD5Utils.validate(udid,
		// sign));
		// String md5 = MD5Utils.md5(udid);
		// System.out.println(md5);
	}

}
