package com.cx.framework.utils;

import java.util.regex.Pattern;

public final class CheckSQLInjectionUtil {
//	private static final String sqlReg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
//			+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|"
//			+ "ascii|declare|exec|count|master|into|drop|execute)\\b)";

	private static final String sqlReg = "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|"
			+ "ascii|declare|exec|count|master|into|drop|execute)\\b)";

	private static Pattern pattern = Pattern.compile(sqlReg, Pattern.CASE_INSENSITIVE);

	/**
	 * 檢查SQL注入
	 *
	 * @param str
	 */
	public static boolean validate(String str) {
		return !pattern.matcher(str).find();
	}

	/**
	 * 檢查SQL注入
	 *
	 * @param strs
	 */
	public static boolean validate(String[] strs) {
		for (String str : strs) {
			if (pattern.matcher(str).find()) {
				return false;
			}
		}
		return true;
	}

}