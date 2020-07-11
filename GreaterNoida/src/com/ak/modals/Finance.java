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
 * @Updated by : Preeti Rani
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
	
	@Column(name="Subdepartment")
	private String subdepartment;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="FileNo")
	private String fileNo;
	
	@Column(name="CodeNo")
	private String codeNo;
	
	@Column(name="Year")
	private String year;
	
	@Column(name="Subject")
	private String subject;
	
	@Column(name="Designation")
	private String designation;
	
	@Column(name="FileType")
	private String fileType;
	
	@Column(name="FileName")
	private String fileName;
	
	@Column(name="PeriodOfYear")
	private String periodOfYear;
	
	@Column(name="PatrawaliSankya")
	private String patrawaliSankya;
	
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
	
	public String getSubdepartment() {
		return subdepartment;
	}
	public void setSubdepartment(String subdepartment) {
		this.subdepartment = subdepartment;
	}
	
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
	public String getFileNo() {
		return fileNo;
	}
	public String getCodeNo() {
		return codeNo;
	}
	public String getYear() {
		return year;
	}
	public String getSubject() {
		return subject;
	}
	public String getDesignation() {
		return designation;
	}
	public String getFileType() {
		return fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public String getPeriodOfYear() {
		return periodOfYear;
	}
	public String getPatrawaliSankya() {
		return patrawaliSankya;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setPeriodOfYear(String periodOfYear) {
		this.periodOfYear = periodOfYear;
	}
	public void setPatrawaliSankya(String patrawaliSankya) {
		this.patrawaliSankya = patrawaliSankya;
	}
	
	
}