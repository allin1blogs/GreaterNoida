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
@Table(name="Departments")
public class Departments
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="Department")
	private String department;
	
	@Column(name="Sector")
	private String sector;
	
	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno=sno;
	}
	
	public String getDepartment()
	{
		return department;
	}
	public void setDepartment(String department)
	{
		this.department=department;
	}
	
	public String getSector()
	{
		return sector;
	}
	public void setSector(String sector)
	{
		this.sector=sector;
	}
}