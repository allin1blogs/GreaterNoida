package com.ak.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Planning2")
public class Planning2 {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Sno")
    private int	sno;
	
	@Column(name="Date")
	private String date;
	
	@Column(name="Lot_No")
	private String	lot_No;
	
	@Column(name="Clerk_Name")
	private String clerk_Name;
	
	@Column(name="Types_Of_Files")
	private String	Types_Of_Files;
	
	@Column(name="Category")
	private String	category;
	
	@Column(name="BP_No")
	private String	BP_No;
	
	@Column(name="AllotmentNo")
	private String	allotmentNo;
	
	@Column(name="ApplicantName")
	private String	applicantName;
	
	@Column(name="PlotNo_HouseNo")
	private String	 PlotNo_HouseNo;
	
	@Column(name="Plot_Size")
	private String	 Plot_Size;
	
	@Column(name="Block_No")
	private String	Block_No;
	
	
	@Column(name="Secotr")
	private String	 Secotr;
	
	@Column(name="FTS_NO_OPA_No")
	private String	opaFts;
	
	@Column(name="No_Of_NoteSheet")
	private String	No_Of_NoteSheet;
	
	@Column(name="No_Of_Cros")
	private String No_Of_Cros;	
	
	@Column(name="No_Of_A0")
	private String  No_Of_A0;
	
	@Column(name="No_Of_A1")
	private String	No_Of_A1;
	
	@Column(name="No_Of_A2")
	private String	No_Of_A2;
	
	@Column(name="No_Of_A3")
	private String  No_Of_A3;	
	
	@Column(name="Total_No_Of_Pages")
	private String	Total_No_Of_Pages;

	@Column(name="Location")
	private String	Location;
	
	

	
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getTypes_Of_Files() {
		return Types_Of_Files;
	}

	public void setTypes_Of_Files(String types_Of_Files) {
		Types_Of_Files = types_Of_Files;
	}

	
	public String getBP_No() {
		return BP_No;
	}

	public void setBP_No(String bP_No) {
		BP_No = bP_No;
	}

	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLot_No() {
		return lot_No;
	}

	public void setLot_No(String lot_No) {
		this.lot_No = lot_No;
	}

	public String getClerk_Name() {
		return clerk_Name;
	}

	public void setClerk_Name(String clerk_Name) {
		this.clerk_Name = clerk_Name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAllotmentNo() {
		return allotmentNo;
	}

	public void setAllotmentNo(String allotmentNo) {
		this.allotmentNo = allotmentNo;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getPlotNo_HouseNo() {
		return PlotNo_HouseNo;
	}

	public void setPlotNo_HouseNo(String plotNo_HouseNo) {
		PlotNo_HouseNo = plotNo_HouseNo;
	}

	public String getPlot_Size() {
		return Plot_Size;
	}

	public void setPlot_Size(String plot_Size) {
		Plot_Size = plot_Size;
	}

	public String getBlock_No() {
		return Block_No;
	}

	public void setBlock_No(String block_No) {
		Block_No = block_No;
	}

	public String getSecotr() {
		return Secotr;
	}

	public void setSecotr(String secotr) {
		Secotr = secotr;
	}

	
	public String getOpaFts() {
		return opaFts;
	}

	public void setOpaFts(String opaFts) {
		this.opaFts = opaFts;
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

	public String getNo_Of_A0() {
		return No_Of_A0;
	}

	public void setNo_Of_A0(String no_Of_A0) {
		No_Of_A0 = no_Of_A0;
	}

	public String getNo_Of_A1() {
		return No_Of_A1;
	}

	public void setNo_Of_A1(String no_Of_A1) {
		No_Of_A1 = no_Of_A1;
	}

	public String getNo_Of_A2() {
		return No_Of_A2;
	}

	public void setNo_Of_A2(String no_Of_A2) {
		No_Of_A2 = no_Of_A2;
	}

	public String getNo_Of_A3() {
		return No_Of_A3;
	}

	public void setNo_Of_A3(String no_Of_A3) {
		No_Of_A3 = no_Of_A3;
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
