package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *	@Author
 *	Preeti  
 *
*/

@Entity
@Table(name="finSubDepartment")
public class FinSubDepartment
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="Department")
	private String department;
	
	@Column(name="subdepartment")
	private String subdepartment;
	
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
	public String getSubdepartment() {
		return subdepartment;
	}
	public void setSubdepartment(String subdepartment) {
		this.subdepartment = subdepartment;
	}
	
	
}