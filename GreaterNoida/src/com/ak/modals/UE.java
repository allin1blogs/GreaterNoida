package com.ak.modals;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UE")
public class UE {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="Sno")
	 private int sno;
	 
	 @Column(name="NameOfWork")
	 private String  name_Of_Work;
	 
	 
	 @Column(name="Contractor_Name")
	 private String contractor_Name;
	 
	 @Column(name="Department")
	 private String department;
	 
	 @Column(name="File_Number")
	 private String	file_Number;
	 
	 @Column(name="Year")
	 private String	 year;
	 
	 @Column(name="NoteSheet")
	 private String NoteSheet;
	 
	 @Column(name=" No_Of_Cros")
	 private String no_Of_Cros;
	 
	 @Column(name="No_Of_A0_Pages")
	 private String	No_Of_A0_Pages;
	 
	 @Column(name="No_Of_A1_Pages")
	 private String No_Of_A1_Pages; 
	 
	 @Column(name="No_Of_A2_Pages")
	 private String No_Of_A2_Pages;
	 
	 @Column(name="No_Of_A3_Pages")
	 private String No_Of_A3_Pages;
	 
	 @Column(name="Total_NO_Of_Pages")
	 private String	total_NO_Of_Pages;
	 
	 @Column(name="Location")
	 private String Location;
	 
	
	public String getNoteSheet() {
		return NoteSheet;
	}
	public void setNoteSheet(String noteSheet) {
		NoteSheet = noteSheet;
	}
	
	public String getNo_Of_Cros() {
		return no_Of_Cros;
	}
	public void setNo_Of_Cros(String no_Of_Cros) {
		this.no_Of_Cros = no_Of_Cros;
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
	
	public void setLocation(String location) {
		Location = location;
	}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public String getFile_Number() {
		return file_Number;
	}
	public void setFile_Number(String file_Number) {
		this.file_Number = file_Number;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTotal_NO_Of_Pages() {
		return total_NO_Of_Pages;
	}
	public void setTotal_NO_Of_Pages(String total_NO_Of_Pages) {
		this.total_NO_Of_Pages = total_NO_Of_Pages;
	}
	public String getLocation() {
		return Location;
	}
	 
	 
	 
	 
	 
}
