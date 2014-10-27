package com.mp.util;
/**
 * 
 * @author Administrator
 *
 */
public class UtilMethod {
	/**
	 * eliminate prefix and postfix from string s
	 * @param mPrefix
	 * @param s
	 * @param mPostfix
	 * @return
	 */
	public static String elimFix(String mPrefix, String s, String mPostfix) {
		return elimPrefix(mPrefix, elimPostfix(s,mPostfix));
	}
	/**
	 * eliminate prefix from s
	 * @param mPrefix
	 * @param s
	 * @return
	 */
	public static String elimPrefix(String mPrefix, String s) {
		int temp = s.indexOf(mPrefix);
		if(temp >= 0) {
			return s.substring(mPrefix.length(), s.length());
		}
		return "";
	}
	/**
	 * eliminate postfix from s
	 * @param s
	 * @param mPostfix
	 * @return
	 */
	public static String elimPostfix(String s,String mPostfix) {
		int temp = s.indexOf(mPostfix);
		if(temp >= 0) {
			return s.substring(0, temp);
		}
		return "";
	}
}
