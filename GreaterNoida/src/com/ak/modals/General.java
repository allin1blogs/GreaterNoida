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
@Table(name="General")
public class General
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="MasterPlot")
	private String masterPlot;
	
	@Column(name="PlotNo")
	private String plotNo;
	
	@Column(name="PlotSize")
	private String plotSize;
	
	@Column(name="Block")
	private String block;
	
	@Column(name="Sector")
	private String sector;
	
	@Column(name="Scheme")
	private String scheme;
	
	@Column(name="AllotmentNo")
	private String allotmentNo;
	
	@Column(name="Fts")
	private String fts;
	
	@Column(name="AlloteeName")
	private String alloteeName;
	
	@Column(name="OfficeOrder")
	private String officeOrder;
	
	@Column(name="OrderDate")
	private String orderDate;
	
	@Column(name="Agenda")
	private String agenda;
	
	@Column(name="BoardMeeting")
	private String boardMeeting;
	
	@Column(name="MeetingDate")
	private String meetingDate;
	
	@Column(name="Department")
	private String department;
	
	@Column(name="Location")
	private String location;
	
	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno=sno;
	}
	
	public String getMasterPlot()
	{
		return masterPlot;
	}
	public void setMasterPlot(String masterPlot)
	{
		this.masterPlot=masterPlot;
	}
	
	public String getPlotNo()
	{
		return plotNo;
	}
	public void setPlotNo(String plotNo)
	{
		this.plotNo=plotNo;
	}
	
	public String getPlotSize()
	{
		return plotSize;
	}
	public void setPlotSize(String plotSize)
	{
		this.plotSize=plotSize;
	}
	
	public String getBlock()
	{
		return block;
	}
	public void setBlock(String block)
	{
		this.block=block;
	}
	
	public String getSector()
	{
		return sector;
	}
	public void setSector(String sector)
	{
		this.sector=sector;
	}
	
	public String getScheme()
	{
		return scheme;
	}
	public void setScheme(String scheme)
	{
		this.scheme=scheme;
	}
	
	public String getAllotmentNo()
	{
		return allotmentNo;
	}
	public void setAllotmentNo(String allotmentNo)
	{
		this.allotmentNo=allotmentNo;
	}
	
	public String getFts()
	{
		return fts;
	}
	public void setFts(String fts)
	{
		this.fts=fts;
	}
	
	public String getAlloteeName()
	{
		return alloteeName;
	}
	public void setAlloteeName(String alloteeName)
	{
		this.alloteeName=alloteeName;
	}
	
	public String getOfficeOrder()
	{
		return officeOrder;
	}
	public void setOfficeOrder(String officeOrder)
	{
		this.officeOrder=officeOrder;
	}
	
	public String getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(String orderDate)
	{
		this.orderDate=orderDate;
	}
	
	public String getAgenda()
	{
		return agenda;
	}
	public void setAgenda(String agenda)
	{
		this.agenda=agenda;
	}
	
	public String getBoardMeeting()
	{
		return boardMeeting;
	}
	public void setBoardMeeting(String boardMeeting)
	{
		this.boardMeeting=boardMeeting;
	}
	
	public String getMeetingDate()
	{
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate)
	{
		this.meetingDate=meetingDate;
	}
	
	public void setLocation(String location)
	{
		this.location=location;
	}
	public String getLocation()
	{
		return location;
	}
	
	public String getDepartment()
	{
		return department;
	}
	public void setDepartment(String department)
	{
		this.department=department;
	}
}