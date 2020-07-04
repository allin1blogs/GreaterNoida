package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Land")
public class Land {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SNO")
	private int sno;

	@Column(name = "Date")
	private String date;

	@Column(name = "AccountNo")
	private String accountNo;

	@Column(name = "FileNo")
	private String fileNo;

	@Column(name = "FileSub")
	private String filesub;

	@Column(name = "FileType")
	private String fileType;

	@Column(name = "Line")
	private String line;

	/*
	 * @Column(name="Department") private String department;
	 */

	@Column(name = "Location")
	private String location;

	@Column(name = "OpaFts")
	private String opaFts;

	@Column(name = "Village")
	private String village;

	@Column(name = "Year")
	private String year;

	@Column(name = "No_Of_Notsheet")
	private String no_of_notsheet;

	@Column(name = "No_Of_Cos")
	private String no_of_cos;

	@Column(name = "No_Of_A3")
	private String no_of_a3;

	@Column(name = "Department")
	private String department;

	@Column(name = "No_Of_A2")
	private String no_of_a2;

	@Column(name = "No_Of_A1")
	private String no_of_a1;

	@Column(name = "No_Of_A0")
	private String no_of_a0;

	@Column(name = "Total_Pages")
	private String total_pages;

	@Column(name = "Servey_Letter")
	private String servey_letter;

	@Column(name = "Notifection")
	private String notifection;

	@Column(name = "Morgeg_Letter")
	private String morgeg_letter;

	@Column(name = "Map_11")
	private String map_11;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getDate() {
		return date;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public String getFileNo() {
		return fileNo;
	}

	public String getFilesub() {
		return filesub;
	}

	public String getFileType() {
		return fileType;
	}

	public String getLine() {
		return line;
	}

	public String getLocation() {
		return location;
	}

	public String getOpaFts() {
		return opaFts;
	}

	public String getVillage() {
		return village;
	}

	public String getYear() {
		return year;
	}

	public String getNo_of_notsheet() {
		return no_of_notsheet;
	}

	public String getNo_of_cos() {
		return no_of_cos;
	}

	public String getNo_of_a3() {
		return no_of_a3;
	}

	public String getNo_of_a2() {
		return no_of_a2;
	}

	public String getNo_of_a1() {
		return no_of_a1;
	}

	public String getNo_of_a0() {
		return no_of_a0;
	}

	public String getTotal_pages() {
		return total_pages;
	}

	public String getServey_letter() {
		return servey_letter;
	}

	public String getNotifection() {
		return notifection;
	}

	public String getMorgeg_letter() {
		return morgeg_letter;
	}

	public String getMap_11() {
		return map_11;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public void setFilesub(String filesub) {
		this.filesub = filesub;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setOpaFts(String opaFts) {
		this.opaFts = opaFts;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setNo_of_notsheet(String no_of_notsheet) {
		this.no_of_notsheet = no_of_notsheet;
	}

	public void setNo_of_cos(String no_of_cos) {
		this.no_of_cos = no_of_cos;
	}

	public void setNo_of_a3(String no_of_a3) {
		this.no_of_a3 = no_of_a3;
	}

	public void setNo_of_a2(String no_of_a2) {
		this.no_of_a2 = no_of_a2;
	}

	public void setNo_of_a1(String no_of_a1) {
		this.no_of_a1 = no_of_a1;
	}

	public void setNo_of_a0(String no_of_a0) {
		this.no_of_a0 = no_of_a0;
	}

	public void setTotal_pages(String total_pages) {
		this.total_pages = total_pages;
	}

	public void setServey_letter(String servey_letter) {
		this.servey_letter = servey_letter;
	}

	public void setNotifection(String notifection) {
		this.notifection = notifection;
	}

	public void setMorgeg_letter(String morgeg_letter) {
		this.morgeg_letter = morgeg_letter;
	}

	public void setMap_11(String map_11) {
		this.map_11 = map_11;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
