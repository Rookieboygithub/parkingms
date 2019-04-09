package com.parkingms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 检测手机后，固定电话，密码格式等
 * @author BHH
 *
 */
public class RegularCheck {
	/**
	 * 检测手机号
	 * @param tel
	 * @return
	 */
	public static boolean telCheck(String tel) {
		boolean flag = false;
		try {
			String check = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(tel);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 固定电话号码检测
	 * @param tel
	 * @return
	 */
	public static boolean callCheck(String tel) {
		boolean flag = false;
		try {
			String check = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(tel);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/**
	 * 检测密码
	 * @param tel
	 * @return
	 */
	public static boolean pwdCheck(String pwd) {
		boolean flag = false;
		try {
			String check = "^\\d{6,15}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(pwd);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/**
	 * 检测登录名
	 * @param account
	 * @return
	 */
	public static boolean accountCheck(String account) {
		boolean flag = false;
		try {
			String check = "^[a-z0-9A-Z\u4e00-\u9fa5]{6,18}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(account);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args) {
		System.out.println(accountCheck("111中国111111ad1111"));
	}
}
