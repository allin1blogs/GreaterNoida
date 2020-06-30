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
*/

@Entity
@Table(name="Law")
public class Law
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="CourtName")
	private String courtName;
	
	@Column(name="PetitionNo")
	private String petitionNo;
	
	@Column(name="PartyName")
	private String partyName;
	
	@Column(name="PetitionerAdvocate")
	private String petitionerAdvocate;
	
	@Column(name="RespondentAdvocate")
	private String respondentAdvocate;
	
	@Column(name="RelatedDepartment")
	private String relatedDepartment;
	
	@Column(name="Year")
	private String year;
	
	@Column(name="Location")
	private String location;
	
	public void setSno(int sno)
	{
		this.sno=sno;
	}
	public int getSno()
	{
		return sno;
	}
	
	public void setCourtName(String courtName)
	{
		this.courtName=courtName;
	}
	public String getCourtName()
	{
		return courtName;
	}
	
	public void setPetitionNo(String petitionNo)
	{
		this.petitionNo=petitionNo;
	}
	public String getPetitionNo()
	{
		return petitionNo;
	}
	
	public void setPartyName(String partyName)
	{
		this.partyName=partyName;
	}
	public String getPartyName()
	{
		return partyName;
	}
	
	public void setPetitonerAdvocate(String petitionerAdvocate)
	{
		this.petitionerAdvocate=petitionerAdvocate;
	}
	public String getPetitionerAdvocate()
	{
		return petitionerAdvocate;
	}
	
	public void setRespondentAdvocate(String respondentAdvocate)
	{
		this.respondentAdvocate=respondentAdvocate;
	}
	public String getRespondentAdvocate()
	{
		return respondentAdvocate;
	}
	
	public void setRelatedDepartment(String relatedDepartment)
	{
		this.relatedDepartment=relatedDepartment;
	}
	public String getRelatedDepartment()
	{
		return relatedDepartment;
	}
	
	public void setYear(String year)
	{
		this.year=year;
	}
	public String getYear()
	{
		return year;
	}
	
	public void setLocation(String location)
	{
		this.location=location;
	}
	public String getLocation()
	{
		return location;
	}
}