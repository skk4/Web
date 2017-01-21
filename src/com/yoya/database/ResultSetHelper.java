package com.yoya.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 获取查询结果的辅助类
 * @author lin
 *
 */
public class ResultSetHelper {
	
	/**
	 * 获取短信验证码
	 * @return
	 */
	public static String smsMessage(String mobile_number){
		List<String> messages=new ArrayList<String>();
		Connection conn=DataBaseConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement(SqlUtil.MESSAGE_SELECT_SQL);
			ps.setString(1, mobile_number);
			rs=ps.executeQuery();
			while(rs.next()){
				messages.add(rs.getString("sms_messageBody"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataBaseConnection.free(conn, ps, rs);
		}
		return messages.size()>0 ? messages.get(0) : "";
	}

}
