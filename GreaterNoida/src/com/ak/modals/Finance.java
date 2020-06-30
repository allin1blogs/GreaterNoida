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
@Table(name="Finance")
public class Finance
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="Category")
	private String category;
	
	@Column(name="BankName")
	private String bankName;
	
	@Column(name="BranchName")
	private String branchName;
	
	@Column(name="AccountNo")
	private String accountNo;
	
	@Column(name="PeriodOfStatement")
	private String statement;
	
	@Column(name="ClerkName")
	private String clerkName;
	
	@Column(name="RegisterName")
	private String registerName;
	
	@Column(name="Sector")
	private String sector;
	
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
	
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}
	
	public String getBankName()
	{
		return bankName;
	}
	public void setBankName(String bankName)
	{
		this.bankName=bankName;
	}
	
	public String getBranchName()
	{
		return branchName;
	}
	public void setBranchName(String branchName)
	{
		this.branchName=branchName;
	}
	
	public String getAccountNo()
	{
		return accountNo;
	}
	public void setAccountNo(String accountNo)
	{
		this.accountNo=accountNo;
	}
	
	public String getStatement()
	{
		return statement;
	}
	public void setStatement(String statement)
	{
		this.statement=statement;
	}
	
	public String getClerkName()
	{
		return clerkName;
	}
	public void setClerkName(String clerkName)
	{
		this.clerkName=clerkName;
	}
	
	public String getRegisterName()
	{
		return registerName;
	}
	public void setRegisterName(String registerName)
	{
		this.registerName=registerName;
	}
	
	public String getSector()
	{
		return sector;
	}
	public void setSector(String sector)
	{
		this.sector=sector;
	}
	
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
}