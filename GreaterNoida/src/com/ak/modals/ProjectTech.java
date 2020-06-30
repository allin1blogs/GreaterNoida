package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProjectTech")
public class ProjectTech {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="Sno")
	private int sno;
	
	@Column(name="Line_No")
	 private String	line_No;
	 
	@Column(name="Date")
	 private String	date;
	
	@Column(name="Sector")//1
	 private String sector;
	
	@Column(name="Category")//2
	 private String category;
	
	@Column(name=" OPA_FTS")
	 private String opaFts;
	
	@Column(name="Name_Of_Work")//3
	 private String	name_Of_Work;
	
	@Column(name="Contractor_Name")//4
	 private String contractor_Name;
	
	@Column(name="Department")//5
	 private String	department;
	
	@Column(name="FileNumber")//6
	 private String	fileNumber;
	
	@Column(name="Year")
	 private String	year;
	
	@Column(name="No_Of_NoteSheet")
	 private String	No_Of_NoteSheet;
	
	@Column(name="NO_Of_Cros")
	 private String	NO_Of_Cros;
	
	@Column(name="No_Of_A3_Pages")
	 private String	No_Of_A3_Pages;
	
	@Column(name="No_Of_A2_Pages")
	 private String	No_Of_A2_Pages;//
	
	@Column(name="No_Of_A1_Pages")
	 private String	No_Of_A1_Pages;
	
	@Column(name="No_Of_A0_Pages")
	 private String	No_Of_A0_Pages;
	
	
	 @Column(name="Total_No_Of_Pages")
	 private String	total_No_Of_Pages;

	 @Column(name="Location")
	 private String	Location;
	 
	public int getSno() {
		return sno;
	}


	public void setSno(int sno) {
		this.sno = sno;
	}


	
	public String getLine_No() {
		return line_No;
	}


	public void setLine_No(String line_No) {
		this.line_No = line_No;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getOpaFts() {
		return opaFts;
	}


	public void setOpaFts(String opaFts) {
		this.opaFts = opaFts;
	}


	public String getName_Of_Work() {
		return name_Of_Work;
	}


	public void setName_Of_Work(String name_Of_Work) {
		this.name_Of_Work = name_Of_Work;
	}


	public String getContractor_Name() {
		return contractor_Name;
	}


	public void setContractor_Name(String contractor_Name) {
		this.contractor_Name = contractor_Name;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getFileNumber() {
		return fileNumber;
	}


	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getTotal_No_Of_Pages() {
		return total_No_Of_Pages;
	}


	public void setTotal_No_Of_Pages(String total_No_Of_Pages) {
		this.total_No_Of_Pages = total_No_Of_Pages;
	}


	public String getNo_Of_NoteSheet() {
		return No_Of_NoteSheet;
	}


	public void setNo_Of_NoteSheet(String no_Of_NoteSheet) {
		No_Of_NoteSheet = no_Of_NoteSheet;
	}


	public String getNO_Of_Cros() {
		return NO_Of_Cros;
	}


	public void setNO_Of_Cros(String nO_Of_Cros) {
		NO_Of_Cros = nO_Of_Cros;
	}


	public String getNo_Of_A3_Pages() {
		return No_Of_A3_Pages;
	}


	public void setNo_Of_A3_Pages(String no_Of_A3_Pages) {
		No_Of_A3_Pages = no_Of_A3_Pages;
	}


	public String getNo_Of_A2_Pages() {
		return No_Of_A2_Pages;
	}


	public void setNo_Of_A2_Pages(String no_Of_A2_Pages) {
		No_Of_A2_Pages = no_Of_A2_Pages;
	}


	public String getNo_Of_A1_Pages() {
		return No_Of_A1_Pages;
	}


	public void setNo_Of_A1_Pages(String no_Of_A1_Pages) {
		No_Of_A1_Pages = no_Of_A1_Pages;
	}


	public String getNo_Of_A0_Pages() {
		return No_Of_A0_Pages;
	}


	public void setNo_Of_A0_Pages(String no_Of_A0_Pages) {
		No_Of_A0_Pages = no_Of_A0_Pages;
	}


	

	public String getLocation() {
		return Location;
	}


	public void setLocation(String location) {
		Location = location;
	}

	 
	 
}
