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
@Table(name="Agenda")
public class Agenda
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="AgendaNo")
	private String agendaNo;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="FileNo")
	private String fileNo;
	
	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno=sno;
	}
	
	public String getAgendaNo() 
	{
		return agendaNo;
	}

	public void setAgendaNo(String agendaNo)
	{
		this.agendaNo=agendaNo;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description=description;
	}

	public String getFileNo()
	{
		return fileNo;
	}

	public void setFileNo(String fileNo)
	{
		this.fileNo=fileNo;
	}
}