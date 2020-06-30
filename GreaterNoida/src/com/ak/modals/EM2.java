package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EM2")
public class EM2 {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="SNO")
private int	sno;

@Column(name="DATE ")
private String DATE;

@Column(name="SECTOR")
private String	sector;

@Column(name="CATEGORY")
private String 	category;

@Column(name="OPA_FTS")
private String	opaFts;

@Column(name="NameOfWork")
private String	name_Of_Work;

@Column(name="ContractorName")
private String	contractor_Name;

@Column(name="Department")
private String	department;

@Column(name="FileNumber")
private String	file_number;

@Column(name="Year")
private String	year;



@Column(name="No_Of_Note_Sheet")
private String	No_Of_NoteSheet;

@Column(name="No_Of_Cros")
private String	No_Of_Cros;

@Column(name="No_OF_A0_PAGES")
private String	NO_OF_A0_PAGES;

@Column(name="NO_OF_A1_PAGES")
private String	NO_OF_A1_PAGES;

@Column(name="NO_OF_A2_PAGES")
private String	NO_OF_A2_PAGES;

@Column(name="No_Of_A3_PAGES")
private String	No_Of_A3_PAGES;

@Column(name="Total_No_Of_Pages")
private String Total_No_Of_Pages;
  
@Column(name="Location")
private String location;


public int getSno() {
	return sno;
}
public void setSno(int sno) {
	this.sno = sno;
}
public String getDATE() {
	return DATE;
}
public void setDATE(String dATE) {
	DATE = dATE;
}


public String getOpaFts() {
	return opaFts;
}
public void setOpaFts(String opaFts) {
	this.opaFts = opaFts;
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
public String getFile_number() {
	return file_number;
}
public void setFile_number(String file_number) {
	this.file_number = file_number;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getNo_Of_NoteSheet() {
	return No_Of_NoteSheet;
}
public void setNo_Of_NoteSheet(String no_Of_NoteSheet) {
	No_Of_NoteSheet = no_Of_NoteSheet;
}
public String getNo_Of_Cros() {
	return No_Of_Cros;
}
public void setNo_Of_Cros(String no_Of_Cros) {
	No_Of_Cros = no_Of_Cros;
}
public String getNo_Of_A3_PAGES() {
	return No_Of_A3_PAGES;
}
public void setNo_Of_A3_PAGES(String no_Of_A3_PAGES) {
	No_Of_A3_PAGES = no_Of_A3_PAGES;
}
public String getNO_OF_A2_PAGES() {
	return NO_OF_A2_PAGES;
}
public void setNO_OF_A2_PAGES(String nO_OF_A2_PAGES) {
	NO_OF_A2_PAGES = nO_OF_A2_PAGES;
}
public String getNO_OF_A0_PAGES() {
	return NO_OF_A0_PAGES;
}
public void setNO_OF_A0_PAGES(String nO_OF_A0_PAGES) {
	NO_OF_A0_PAGES = nO_OF_A0_PAGES;
}
public String getNO_OF_A1_PAGES() {
	return NO_OF_A1_PAGES;
}
public void setNO_OF_A1_PAGES(String nO_OF_A1_PAGES) {
	NO_OF_A1_PAGES = nO_OF_A1_PAGES;
}
public String getTotal_No_Of_Pages() {
	return Total_No_Of_Pages;
}
public void setTotal_No_Of_Pages(String total_No_Of_Pages) {
	Total_No_Of_Pages = total_No_Of_Pages;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}




}

