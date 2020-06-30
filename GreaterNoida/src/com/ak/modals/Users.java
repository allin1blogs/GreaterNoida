package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Entity
@Table(name="Users")
public class Users 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="UserId")
	private String userId;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="EmployeeId")
	private String employeeId;
	
	@Column(name="EmailId")
	private String emailId;
	
	@Column(name="MobileNo")
	private String mobileNo;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="UserType")
	private String userType;
	
	@Column(name="Upload")
	private boolean upload;
	
	@Column(name="View")
	private boolean view;
	
	@Column(name="Download")
	private boolean download;
	
	@Column(name="Print")
	private boolean print;
	
	@Column(name="Edit")
	private boolean update;
	
	@Column(name="Report")
	private boolean report;
	
	@Column(name="Agenda")
	private boolean agenda;
	
	@Column(name="Logs")
	private boolean logs;
	
	@Column(name="Disable")
	private boolean disable;
	
	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno=sno;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId=userId;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password=password;
	}
	
	public void setEmployeeId(String employeeId)
	{
		this.employeeId=employeeId;
	}
	public String getEmployeeId()
	{
		return employeeId;
	}

	public String getEmailId() 
	{
		return emailId;
	}
	public void setEmailId(String emailId) 
	{
		this.emailId=emailId;
	}

	public String getMobileNo() 
	{
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) 
	{
		this.mobileNo=mobileNo;
	}

	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName=firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName=lastName;
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
	
	public boolean getDisable()
	{
		return disable;
	}
	public void setDisable(boolean disable)
	{
		this.disable=disable;
	}
}