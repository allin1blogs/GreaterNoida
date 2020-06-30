package com.ak.modals;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Systems")
public class Systems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="SNO")
 private int sno;
	
	@Column(name="Date")
 private String	date;
	
	@Column(name="LOT_NO")	
 private String	lotNo;
	
	@Column(name="Clerk_Name")
 private String	clerk_Name;
	
	@Column(name="File_No")
 private String	file_No;
	
	@Column(name="Department")
 private String	department;
	
	@Column(name="FTS_NO_OPA_NO")
 private String	optFts;
	
	@Column(name="SubjectName")
 private String	subjectName;
	
	@Column(name="Year")
 private String	Year;
	
	@Column(name="No_Of_NoteSheet")
 private String	no_Of_NoteSheet;
	
	@Column(name="No_Of_Cros")
 private String	No_Of_Cros;
	
	@Column(name="No_Of_A0_Pages")
 private String	No_Of_A0_Pages;
	
	@Column(name="No_Of_A1_Pages")
 private String	No_Of_A1_Pages;
	
	@Column(name="No_Of_A2_Pages")
 private String	No_Of_A2_Pages;
	
	@Column(name="No_Of_A3_Pages")
 private String	 No_Of_A3_Pages;
	
	@Column(name="Opt_Name")
 private String	Opt_Name;
	
	@Column(name="Total_No_Of_Pages")
 private String	Total_No_Of_Pages;
  
	
	@Column(name="Location")	
 private String Location;


public int getSno() {
	return sno;
}

public void setSno(int sno) {
	this.sno = sno;
}




public String getFile_No() {
	return file_No;
}

public void setFile_No(String file_No) {
	this.file_No = file_No;
}




public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}



public String getLotNo() {
	return lotNo;
}

public void setLotNo(String lotNo) {
	this.lotNo = lotNo;
}

public String getClerk_Name() {
	return clerk_Name;
}

public void setClerk_Name(String clerk_Name) {
	this.clerk_Name = clerk_Name;
}

public String getOptFts() {
	return optFts;
}

public void setOptFts(String optFts) {
	this.optFts = optFts;
}



public String getSubjectName() {
	return subjectName;
}

public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
}

public String getYear() {
	return Year;
}

public void setYear(String year) {
	Year = year;
}


public String getNo_Of_NoteSheet() {
	return no_Of_NoteSheet;
}

public void setNo_Of_NoteSheet(String no_Of_NoteSheet) {
	this.no_Of_NoteSheet = no_Of_NoteSheet;
}

public String getNo_Of_Cros() {
	return No_Of_Cros;
}

public void setNo_Of_Cros(String no_Of_Cros) {
	No_Of_Cros = no_Of_Cros;
}

public String getNo_Of_A0_Pages() {
	return No_Of_A0_Pages;
}

public void setNo_Of_A0_Pages(String no_Of_A0_Pages) {
	No_Of_A0_Pages = no_Of_A0_Pages;
}

public String getNo_Of_A1_Pages() {
	return No_Of_A1_Pages;
}

public void setNo_Of_A1_Pages(String no_Of_A1_Pages) {
	No_Of_A1_Pages = no_Of_A1_Pages;
}

public String getNo_Of_A2_Pages() {
	return No_Of_A2_Pages;
}

public void setNo_Of_A2_Pages(String no_Of_A2_Pages) {
	No_Of_A2_Pages = no_Of_A2_Pages;
}

public String getNo_Of_A3_Pages() {
	return No_Of_A3_Pages;
}

public void setNo_Of_A3_Pages(String no_Of_A3_Pages) {
	No_Of_A3_Pages = no_Of_A3_Pages;
}

public String getOpt_Name() {
	return Opt_Name;
}

public void setOpt_Name(String opt_Name) {
	Opt_Name = opt_Name;
}

public String getTotal_No_Of_Pages() {
	return Total_No_Of_Pages;
}

public void setTotal_No_Of_Pages(String total_No_Of_Pages) {
	Total_No_Of_Pages = total_No_Of_Pages;
}

public String getLocation() {
	return Location;
}

public void setLocation(String location) {
	Location = location;
}
 
}
