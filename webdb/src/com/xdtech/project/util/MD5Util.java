package com.xdtech.project.util;

/**
 * MD5加密工具
 * 
 * @author zhangjianbing@msn.com
 */
public final class MD5Util {
	/**
	 * 加密字符串
	 * 
	 * @param s
	 *            字符串
	 * @return 加密后的字符串
	 */
	public final static String MD5(String source) {
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

	public static void main(String[] args) {
		System.out.println(MD5("123456"));
	}
}
