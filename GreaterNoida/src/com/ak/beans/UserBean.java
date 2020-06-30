package com.ak.beans;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public class UserBean
{
	private String userId,userType;
	private boolean upload,view,download,print,update,report,logs,agenda;
	
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId=userId;
	}
	
	public String getUserType() 
	{
		return userType;
	}
	public void setUserType(String userType) 
	{
		this.userType=userType;
	}
	
	public void setUpload(boolean upload)
	{
		this.upload=upload;
	}
	public boolean getUpload()
	{
		return upload;
	}
	
	public boolean getView()
	{
		return view;
	}
	public void setView(boolean view)
	{
		this.view=view;
	}
	
	public boolean getDownload()
	{
		return download;
	}
	public void setDownload(boolean download)
	{
		this.download=download;
	}
	
	public void setPrint(boolean print)
	{
		this.print=print;
	}
	public boolean getPrint()
	{
		return print;
	}
	
	public boolean getUpdate()
	{
		return update;
	}
	public void setUpdate(boolean update)
	{
		this.update=update;
	}
	
	public void setReport(boolean report)
	{
		this.report=report;
	}
	public boolean getReport()
	{
		return report;
	}
	
	public void setAgenda(boolean agenda)
	{
		this.agenda=agenda;
	}
	public boolean getAgenda()
	{
		return agenda;
	}
	
	public boolean getLogs()
	{
		return logs;
	}
	public void setLogs(boolean logs)
	{
		this.logs=logs;
	}
}
