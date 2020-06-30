package com.ak.modals;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="HR")
public class HR {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SNO")
	private int sno;
	
	@Column(name="LineNo")
	 private String	line_No;
	
	@Column(name="Date")
	 private String	date;
	
	@Column(name="File_No")
	 private String	fileNo;
	
	@Column(name="File_Code")
	 private String	fileCode;
	
	@Column(name="Year")
	 private String	year;
	
	@Column(name="File_Subject")
	 private String	file_Subject;
	
	@Column(name="OPA_FTS")
	 private String	opaFts;
	
	@Column(name="No_Of_Noteeing")
	 private String	no_Of_Noteeing;
	
	@Column(name="Nop_Of_Coss")
	 private String	nop_Of_Coss;
	
	@Column(name="Total_No_Of_Pages")
	 private String	total_No_Of_Pages;
	
	
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
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFile_Subject() {
		return file_Subject;
	}
	public void setFile_Subject(String file_Subject) {
		this.file_Subject = file_Subject;
	}
	
	public String getOpaFts() {
		return opaFts;
	}
	public void setOpaFts(String opaFts) {
		this.opaFts = opaFts;
	}
	public String getNo_Of_Noteeing() {
		return no_Of_Noteeing;
	}
	public void setNo_Of_Noteeing(String no_Of_Noteeing) {
		this.no_Of_Noteeing = no_Of_Noteeing;
	}
	public String getNop_Of_Coss() {
		return nop_Of_Coss;
	}
	public void setNop_Of_Coss(String nop_Of_Coss) {
		this.nop_Of_Coss = nop_Of_Coss;
	}
	public String getTotal_No_Of_Pages() {
		return total_No_Of_Pages;
	}
	public void setTotal_No_Of_Pages(String total_No_Of_Pages) {
		this.total_No_Of_Pages = total_No_Of_Pages;
	}
	private String Location;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	
	
	 
}
