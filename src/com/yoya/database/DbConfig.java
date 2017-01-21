package com.yoya.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * @author linan
 *
 */
public final class DbConfig {
	
	private String databaseUrl;//数据库连接地址
	private String driverName;//驱动名
	private String userName;//数据库用户名
	private String password;//数据库密码
	
	public DbConfig(){
		InputStream inputStream =null;
		try {
				inputStream = new FileInputStream("./properties/dbConfig.properties");
				Properties properties=new Properties();	
				properties.load(inputStream);
				this.driverName=properties.getProperty("driverName");
				this.databaseUrl=properties.getProperty("databaseUrl");
				this.userName=properties.getProperty("username");
				this.password=properties.getProperty("password");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public String getDatabaseUrl() {
		return databaseUrl;
	}


	public String getDriverName() {
		return driverName;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}

	
}
