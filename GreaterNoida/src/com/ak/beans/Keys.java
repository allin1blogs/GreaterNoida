package com.ak.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 *	@Author
 *	Swapril Tyagi 
 *  Updated By:Ashok Khatri
*/

@Component("keys")
public class Keys
{
	@Value("#{'${departments}'.split(',')}")
	private String[] departments;
	
	@Value("#{'${GeneralHeader}'.split(',')}")
	private String[] generalHeader;
	
	@Value("#{'${FinanceHeader}'.split(',')}")
	private String[] financeHeader;
	
	@Value("#{'${ProjectHeader}'.split(',')}")
	private String[] projectHeader;
	
	@Value("#{'${EMHeader}'.split(',')}")
	private String[] emHeader;
	
	@Value("#{'${LawHeader}'.split(',')}")
	private String[] lawHeader;
	
	@Value("#{'${Planning(Residential)Header}'.split(',')}")
	private String[] plnResHeader;
	
	@Value("#{'${Planning(Industry)Header}'.split(',')}")
	private String[] plnIndHeader;
	
	/*----------------------------------------*/
	@Value("#{'${HRHeader}'.split(',')}")
	private String[] HRHeader;	
	
	@Value("#{'${EM2Header}'.split(',')}")
	private String[] EM2Header;	
	
	@Value("#{'${UEHeader}'.split(',')}")
	private String[] UEHeader;
	
	@Value("#{'${Planning2Header}'.split(',')}")
	private String[] Planning2Header;
	
	
	
	@Value("#{'${ProjectTechHeader}'.split(',')}")
	private String[] ProjectTechHeader;
	
	
	@Value("#{'${SystemHeader}'.split(',')}")
	private String[] SystemHeader;
	
	@Value("#{'${MarketingHeader}'.split(',')}")
	private String[] MarketingHeader;
	
	@Value("#{'${LandHeader}'.split(',')}")
	private String[] LandHeader;
	/*----------------------------------------*/
	
	
	@Value("${dataLocation}")
	private String dataLocation;
	
	@Value("${repository}")
	private String repository;
	
	@Value("${reportPath}")
	private String reportLocation;
	
	@Value("${agenda}")
	private String agendaLocation;
	
	public void setDepartments(String[] departments)
	{
		this.departments=departments;
	}
	public String[] getDepartments()
	{
		return departments;
	}
	
	public void setGeneralHeader(String[] generalHeader)
	{
		this.generalHeader=generalHeader;
	}
	public String[] getGeneralHeader()
	{
		return generalHeader;
	}
	
	public void setFinanceHeader(String[] financeHeader)
	{
		this.financeHeader=financeHeader;
	}
	public String[] getFinanceHeader()
	{
		return financeHeader;
	}
	public String[] getEmHeader() {
		return emHeader;
	}
	public void setEmHeader(String[] emHeader) {
		this.emHeader = emHeader;
	}
	
	public String[] getLawHeader() {
		return lawHeader;
	}
	public void setLawHeader(String[] lawHeader) {
		this.lawHeader = lawHeader;
	}
	
	public String[] getPlnResHeader() {
		return plnResHeader;
	}
	public void setPlnResHeader(String[] plnResHeader) {
		this.plnResHeader = plnResHeader;
	}
	
	public String[] getPlnIndHeader() {
		return plnIndHeader;
	}
	public void setPlnIndHeader(String[] plnIndHeader) {
		this.plnIndHeader = plnIndHeader;
	}
	
	public void setDataLocation(String dataLocation)
	{
		this.dataLocation=dataLocation;
	}
	public String getDataLocation()
	{
		return dataLocation;
	}
	
	public void setRepository(String repository)
	{
		this.repository=repository;
	}
	public String getRepository()
	{
		return repository;
	}
	
	public void setReportLocation(String reportLocation)
	{
		this.reportLocation=reportLocation;
	}
	public String getReportLocation()
	{
		return reportLocation;
	}
	
	public String getAgendaLocation()
	{
		return agendaLocation;
	}
	public void setAgendaLocation(String agendaLocation)
	{
		this.agendaLocation=agendaLocation;
	}
	public String[] getHRHeader() {
		return HRHeader;
	}
	public void setHRHeader(String[] hRHeader) {
		HRHeader = hRHeader;
	}
	public String[] getEM2Header() {
		return EM2Header;
	}
	public void setEM2Header(String[] eM2Header) {
		EM2Header = eM2Header;
	}
	public String[] getUEHeader() {
		return UEHeader;
	}
	public void setUEHeader(String[] uEHeader) {
		UEHeader = uEHeader;
	}
	public String[] getPlanning2Header() {
		return Planning2Header;
	}
	public void setPlanning2Header(String[] planning2Header) {
		Planning2Header = planning2Header;
	}
	
	public String[] getSystemHeader() {
		return SystemHeader;
	}
	public void setSystemHeader(String[] systemHeader) {
		SystemHeader = systemHeader;
	}
	public String[] getProjectHeader() {
		return projectHeader;
	}
	public void setProjectHeader(String[] projectHeader) {
		this.projectHeader = projectHeader;
	}
	public String[] getProjectTechHeader() {
		return ProjectTechHeader;
	}
	public void setProjectTechHeader(String[] projectTechHeader) {
		ProjectTechHeader = projectTechHeader;
	}
	public String[] getMarketingHeader() {
		return MarketingHeader;
	}
	public void setMarketingHeader(String[] marketingHeader) {
		MarketingHeader = marketingHeader;
	}
	public String[] getLandHeader() {
		return LandHeader;
	}
	public void setLandHeader(String[] landHeader) {
		LandHeader = landHeader;
	}
	
	
	
	
}