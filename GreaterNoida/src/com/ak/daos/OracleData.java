package com.ak.daos;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.ak.modals.General;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public class OracleData
{
	public static General getGen(String allotmentNo,String category)
	{
		String table=null;
		General general=null;
		table=getPropertyValue(category);
		general=getRecord(allotmentNo,category,table);
		return general;
	}
	
	private static String getPropertyValue(String key)
	{
		String value=null;
		FileInputStream inputStream;
		Properties prop=null;
		try 
		{
			inputStream=new FileInputStream(new File("C:/Resources/QuerySoftOracleResources.properties"));
			prop=new Properties();
			if(inputStream!=null)
				prop.load(inputStream);
			value=prop.getProperty(key);
		} 
		catch (Exception e) 
		{e.printStackTrace();}
		return value;
	}
	
	public static General getRecord(String allotmentNo,String category,String table)
	{
		General general=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String driverClass=null,url=null,userName=null,password=null;
		driverClass=getPropertyValue("driverClass");
		url=getPropertyValue("url");
		userName=getPropertyValue("userName");
		password=getPropertyValue("password");
		try
		{
			Class.forName(driverClass);
			con=DriverManager.getConnection(url,userName,password);
			st=con.createStatement();
			rs=st.executeQuery("select * from "+table+" where allotNo='"+allotmentNo+"'");
			if(rs.next())
			{
				general=new General();
				general.setDepartment(category);
				general.setSector(rs.getString(7));
				general.setScheme(rs.getString(1));
				general.setAllotmentNo(allotmentNo);
				general.setAlloteeName(rs.getString(6));
				general.setPlotNo(rs.getString(9));
				general.setPlotSize(rs.getString(10));
				general.setBlock(rs.getString(8));
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		finally
		{
			try
			{
				st.close();
				con.close();
			}
			catch(Exception e)
			{e.printStackTrace();}
		}
		return general;
	}
}