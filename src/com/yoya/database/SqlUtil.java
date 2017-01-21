package com.yoya.database;

import java.util.List;

/**
 * SQL语句工具类
 *
 */
public class SqlUtil {

	/**
	 * 查询安全中心短信验证码的sql
	 */
	public static final String MESSAGE_SELECT_SQL ="SELECT sms_messageBody FROM `web_sms_info` WHERE sms_telNo=? ORDER BY modify_time DESC LIMIT 1";

	/**
	 * 拼接SQL语句
	 * 
	 * @param parentSql
	 * @param appendList
	 * @return
	 */
	public static String append(String parentSql, List<String> appendList) {
		StringBuilder sb = new StringBuilder();
		if (appendList != null && appendList.size() > 0) {
			for (String string : appendList) {
				sb.append(" and ").append(string);
			}
		}
		return parentSql + sb.toString();
	}
}
