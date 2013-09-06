package com.xdtech.platform.util;

/**
 * MD5加密类
 * @author Administrator
 *
 */
public class MD5 {
	/**
	 * 进行MD5加密
	 * @param source
	 * @return
	 */
	public static String encoding(String source) {
		StringBuffer reStr = null;
		try {
			java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
			byte[] bs = alga.digest(source.getBytes("UTF-8"));
			reStr = new StringBuffer();
			for (int i = 0; i < bs.length; i++) {
				reStr.append(byteHEX(bs[i]));
			}
		} catch (Exception ex) {
		}
		return reStr == null ? null : reStr.toString();
	}

	private static String byteHEX(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
}
