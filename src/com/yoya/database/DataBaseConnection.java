package com.yoya.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
	private static String databaseUrl;//数据库连接地址
	private static String driverName;//驱动名
	private static String userName;//数据库用户名
	private static String password;//数据库密码
	//装载驱动
	 static{
		DbConfig config=new DbConfig();
		databaseUrl=config.getDatabaseUrl();
		userName=config.getUserName();
		password=config.getPassword();
		driverName=config.getDriverName();
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(databaseUrl, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放连接
	 * @param conn
	 */
	private static void freeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 释放statement
	 * @param statement
	 */
	private static void freeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 释放resultset
	 * @param rs
	 */
	private static void freeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 释放资源
	 * @param conn
	 * @param statement
	 * @param rs
	 */
	public static void free(Connection conn, Statement statement, ResultSet rs) {
		if (rs != null) {
			freeResultSet(rs);
		}
		if (statement != null) {
			freeStatement(statement);
		}
		if (conn != null) {
			freeConnection(conn);
		}
	}
	
	
	
}
