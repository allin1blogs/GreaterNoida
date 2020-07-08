package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Health")
public class Health {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Sno")
	private int sno;

	@Column(name = "Category")
	private String category;

	@Column(name = "ContractorName")
	private String contractorName;

	@Column(name = "Department")
	private String department;

	@Column(name = "FileNo")
	private String fileNo;

	@Column(name = "Location")
	private String location;

	@Column(name = "Opa_Fts")
	private String opa_fts;

	@Column(name = "Sector")
	private String sector;

	@Column(name = "WorkName")
	private String workName;

	@Column(name = "Year")
	private String year;

	@Column(name = "ContrName")
	private String contrName;

	@Column(name = "OpaFts")
	private String opafts;

	@Column(name = "Scheme")
	private String scheme;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOpa_fts() {
		return opa_fts;
	}

	public void setOpa_fts(String opa_fts) {
		this.opa_fts = opa_fts;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getContrName() {
		return contrName;
	}

	public void setContrName(String contrName) {
		this.contrName = contrName;
	}

	public String getOpafts() {
		return opafts;
	}

	public void setOpafts(String opafts) {
		this.opafts = opafts;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
}
