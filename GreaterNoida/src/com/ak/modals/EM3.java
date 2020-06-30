package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="EM3")
public class EM3
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="Sector")
	private String sector;
	
	@Column(name="Category")
	private String category;
	
	@Column(name="Opa_Fts")
	private String opa_Fts;
	
	@Column(name="WorkName")
	private String workName;
	
	@Column(name="ContractorName")
	private String contractorName;
	
	@Column(name="Department")
	private String department;
	
	@Column(name="FileNo")
	private String fileNo;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="No_Of_NoteSheet")
	private String NoOfNoteSheet;
	
	@Column(name="No_Of_Cros")
	private String NoOfCros;
	
	@Column(name="No_Of_A3_Pages")
	private String NoOfA3Pages;
	
	@Column(name="No_Of_A2_Pages")
	private String NoOfA2Pages;
	
	@Column(name="No_Of_A1_Pages")
	private String NoOfA1Pages;
	
	@Column(name="No_Of_A0_Pages")
	private String NoOfA0Pages;
	
	@Column(name="Total_No_Of_Pages")
	private String TotalNoOfPages;
	
	@Column(name="Year")
	private String Year;
	
	
	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno=sno;
	}
	
	public void setSector(String sector)
	{
		this.sector=sector;
	}
	public String getSector()
	{
		return sector;
	}
	
	public void setCategory(String category)
	{
		this.category=category;
	}
	public String getCategory()
	{
		return category;
	}
	
	
	
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getOpa_Fts() {
		return opa_Fts;
	}
	public void setOpa_Fts(String opa_Fts) {
		this.opa_Fts = opa_Fts;
	}
	public void setWorkName(String workName)
	{
		this.workName=workName;
	}
	public String getWorkName()
	{
		return workName;
	}
	
	public void setContractorName(String contractorName)
	{
		this.contractorName=contractorName;
	}
	public String getContractorName()
	{
		return contractorName;
	}
	
	public void setFileNo(String fileNo)
	{
		this.fileNo=fileNo;
	}
	public String getFileNo()
	{
		return fileNo;
	}
	
	public void setDepartment(String department)
	{
		this.department=department;
	}
	public String getDepartment()
	{
		return department;
	}
	
	public void setLocation(String location)
	{
		this.location=location;
	}
	public String getLocation()
	{
		return location;
	}
	public String getNoOfNoteSheet() {
		return NoOfNoteSheet;
	}
	public String getNoOfCros() {
		return NoOfCros;
	}
	public String getNoOfA3Pages() {
		return NoOfA3Pages;
	}
	public String getNoOfA2Pages() {
		return NoOfA2Pages;
	}
	public String getNoOfA1Pages() {
		return NoOfA1Pages;
	}
	public String getNoOfA0Pages() {
		return NoOfA0Pages;
	}
	public String getTotalNoOfPages() {
		return TotalNoOfPages;
	}
	public void setNoOfNoteSheet(String noOfNoteSheet) {
		NoOfNoteSheet = noOfNoteSheet;
	}
	public void setNoOfCros(String noOfCros) {
		NoOfCros = noOfCros;
	}
	public void setNoOfA3Pages(String noOfA3Pages) {
		NoOfA3Pages = noOfA3Pages;
	}
	public void setNoOfA2Pages(String noOfA2Pages) {
		NoOfA2Pages = noOfA2Pages;
	}
	public void setNoOfA1Pages(String noOfA1Pages) {
		NoOfA1Pages = noOfA1Pages;
	}
	public void setNoOfA0Pages(String noOfA0Pages) {
		NoOfA0Pages = noOfA0Pages;
	}
	public void setTotalNoOfPages(String totalNoOfPages) {
		TotalNoOfPages = totalNoOfPages;
	}
	
}