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
 *  @Update
 *  Preeti Rani
*/

@Entity
@Table(name="Planning")
public class Planning
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="SubDepartment")
	private String subDepartment;
	
	@Column(name="FileType")
	private String fileType;
	
	@Column(name="Category")
	private String category;
	
	@Column(name="AllotmentNo")
	private String allotmentNo;
	
	@Column(name="bpNo")
	private String bpNo;
	
	@Column(name="ApplicantName")
	private String applicantName;
	
	@Column(name="PlotNo")
	private String plotNo;
	
	@Column(name="PlotSize")
	private String plotSize;
	
	@Column(name="Sector")
	private String sector;
	
	@Column(name="BlockNo")
	private String blockNo;
	
	@Column(name="Fts")
	private String fts;
	
	@Column(name="Doa")
	private String doa;
	
	@Column(name="Doc")
	private String doc;
	
	@Column(name="PolicyNo")
	private String policyNo;
	
	@Column(name="Subject")
	private String subject;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="OPA_FTS")
	private String opa_Fts;
	
	@Column(name="BN_NO")
	private String bn_no;
	
	@Column(name="Year")
	private String year;
	
	@Column(name="CLERK_NAME")
	private String clerk_Name;
	
	@Column(name="DEPARTMENT")
	private String department;
	
	@Column(name="FILENO")
	private String fileNo;
	
	
	
	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno=sno;
	}
	
	public void setSubDepartment(String subDepartment)
	{
		this.subDepartment=subDepartment;
	}
	public String getSubDepartment()
	{
		return subDepartment;
	}
	
	public String getFileType()
	{
		return fileType;
	}
	public void setFileType(String fileType)
	{
		this.fileType=fileType;
	}
	
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}
	
	public String getAllotmentNo()
	{
		return allotmentNo;
	}
	public void setAllotmentNo(String allotmentNo)
	{
		this.allotmentNo=allotmentNo;
	}
	
	public String getBpNo()
	{
		return bpNo;
	}
	public void setBpNo(String bpNo)
	{
		this.bpNo=bpNo;
	}
	
	public String getApplicantName()
	{
		return applicantName;
	}
	public void setApplicantName(String applicantName)
	{
		this.applicantName=applicantName;
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
	
	public String getSector()
	{
		return sector;
	}
	public void setSector(String sector)
	{
		this.sector=sector;
	}
	
	public String getBlockNo()
	{
		return blockNo;
	}
	public void setBlockNo(String blockNo)
	{
		this.blockNo=blockNo;
	}
	
	public String getFts()
	{
		return fts;
	}
	public void setFts(String fts)
	{
		this.fts=fts;
	}
	
	public String getDoa()
	{
		return doa;
	}
	public void setDoa(String doa)
	{
		this.doa=doa;
	}
	
	public String getDoc()
	{
		return doc;
	}
	public void setDoc(String doc)
	{
		this.doc=doc;
	}
	
	public void setPolicyNo(String policyNo)
	{
		this.policyNo=policyNo;
	}
	public String getPolicyNo()
	{
		return policyNo;
	}
	
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	public String getSubject()
	{
		return subject;
	}
	
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
	public String getOpa_Fts() {
		return opa_Fts;
	}
	public String getBn_no() {
		return bn_no;
	}
	public String getYear() {
		return year;
	}
	public void setOpa_Fts(String opa_Fts) {
		this.opa_Fts = opa_Fts;
	}
	public void setBn_no(String bn_no) {
		this.bn_no = bn_no;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getClerk_Name() {
		return clerk_Name;
	}
	public void setClerk_Name(String clerk_Name) {
		this.clerk_Name = clerk_Name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	
	
}