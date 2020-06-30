package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.ak.utils.Utils;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Entity
@Table(name="Logs")
public class Logs
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="UserId")
	private String userId;
	
	@Column(name="Activity")
	private String activity;
	
	@Column(name="Date")
	private String date;
	
	@Column(name="Time")
	private String time;
	
	@Column(name="MACId")
	private String macId;
	
	@Column(name="DeviceName")
	private String deviceName;
	
	public Logs()
	{
		
	}
	
	public Logs(String userId,String activity)
	{
		setUserId(userId);
		setActivity(activity);
		setDate(Utils.getDate());
		setTime(Utils.getTime());
		setMacId(Utils.getMacAddress());
		setDeviceName(Utils.getDeviceName());
	}
	
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
	
	public String getActivity()
	{
		return activity;
	}
	public void setActivity(String activity)
	{
		this.activity=activity;
	}
	
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date=date;
	}
	
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time=time;
	}
	
	public void setMacId(String macId)
	{
		this.macId=macId;
	}
	public String getMacId()
	{
		return macId;
	}
	
	public void setDeviceName(String deviceName)
	{
		this.deviceName=deviceName;
	}
	public String getDeviceName()
	{
		return deviceName;
	}
}