package cn.et.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class MysalDBUtils {
	
	static Properties p = new Properties();
	static{
		InputStream is =(MysalDBUtils.class.getResourceAsStream("mysaljdbc.properties"));
		try {
			p.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws Exception {
		String url = p.getProperty("url");
		String driverClass = p.getProperty("driverClass");
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		Class.forName(driverClass);
		Connection conn = DriverManager.getConnection(url, username, password);
		 
		System.out.println(conn);
		return conn;
	}
	
	public static List<Map> query(String sql){
		List list=new ArrayList<>();
		try {
			Connection conn=getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			
			ResultSetMetaData rsd=rs.getMetaData();
			//获取列的个数
			int columnCount=rsd.getColumnCount();
			while(rs.next()){
				Map map=new HashMap<>();
				for(int i=1;i<=columnCount;i++){
					String colName=rsd.getColumnName(i);
					String colValue=rs.getString(i);
					map.put(colName, colValue);
				}
				list.add(map);
				
			}
			conn.close();
			pst.close();
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static int execute(String sql) throws Exception{
		Connection conn=getConnection();
		PreparedStatement pst=conn.prepareStatement(sql);
		int num=pst.executeUpdate();
		conn.close();
		pst.close();
		return num;
	}
}
