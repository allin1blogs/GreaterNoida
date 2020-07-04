package com.ak.utils;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ak.beans.Keys;
import com.ak.modals.EM;
import com.ak.modals.EM2;
import com.ak.modals.EM3;
import com.ak.modals.Finance;
import com.ak.modals.General;
import com.ak.modals.HR;
import com.ak.modals.Land;
import com.ak.modals.Law;
import com.ak.modals.Marketing;
import com.ak.modals.Planning;
import com.ak.modals.Planning2;
import com.ak.modals.Project;
import com.ak.modals.ProjectTech;
import com.ak.modals.Systems;
import com.ak.modals.UE;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/*
 *	@Author
 *	Swapril Tyagi 
 *Updated By:Preeti Rani
 *  Started 20-Jun-2020 to till
*/

@Component("utils")
public class Utils
{
	@Autowired
	private Keys keys;
	
	public static String getDate()
	{
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    	Date today = Calendar.getInstance().getTime();
    	return df.format(today);
	}
	
	private  String getYear()
	{
		String year=null;
		year=getDate().substring(getDate().lastIndexOf("/")+1);
		return year;
	}
	
	private  String getMonth()
	{
		String mon=getDate().substring(getDate().indexOf(".")+1,getDate().lastIndexOf("."));
		if(mon.equals("01"))
			return "Jan";
		else if(mon.equals("02"))
			return "Feb";
		else if(mon.equals("03"))
			return "Mar";
		else if(mon.equals("04"))
			return "Apr";
		else if(mon.equals("05"))
			return "May";
		else if(mon.equals("06"))
			return "June";
		else if(mon.equals("07"))
			return "July";
		else if(mon.equals("08"))
			return "Aug";
		else if(mon.equals("09"))
			return "Sep";
		else if(mon.equals("10"))
			return "Oct";
		else if(mon.equals("11"))
			return "Nov";
		else
			return "Dec";
	}
	
	public static String getTime()
	{
		DateFormat df = new SimpleDateFormat("hh:mm:ss");
    	Date today = Calendar.getInstance().getTime();
    	return df.format(today);
	}
	
	public static String getMacAddress()
	{
		String mac=null;
		try
		{
			InetAddress ip=InetAddress.getLocalHost();
			NetworkInterface network=NetworkInterface.getByInetAddress(ip);
			byte[] m=network.getHardwareAddress();
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<m.length;i++)
				sb.append(String.format("%02X%s",m[i],(i<m.length-1) ? "-" : ""));
			mac=sb.toString();
		}
		catch(Exception e)
		{mac="N.A.";}
		return mac;
	}
	
	public static String getDeviceName()
	{
		return System.getProperty("user.name");
	}
	
	public String[] getHeader(String department)
	{
		if(department.equals("Residential") || department.equals("Institutional") || department.equals("IT") || department.equals("Group Housing") || department.equals("Builder") || department.equals("Commercial") || department.equals("Industry"))
			return keys.getGeneralHeader();
		else if(department.equals("Finance"))
			return keys.getFinanceHeader();
		else if(department.equals("Project") || department.equals("Sewage") || department.equals("Water"))
			return keys.getProjectHeader();
		
		else if(department.equals("EM") || department.equals("EM3") || department.equals("HortiCulture") || department.equals("Urban"))
			return keys.getEmHeader();
		else if(department.equals("Law"))
			return keys.getLawHeader();
		else if(department.equals("Land"))
			return keys.getLandHeader();
		else if(department.equals("Planning(Residential)"))
			return keys.getPlnResHeader();
		else if(department.equals("Planning(Industry)"))
			return keys.getPlnIndHeader();
		/*
		 * else if(department.equals("Health")) return keys.
		 */
		
		else if(department.equals("HR"))
			return keys.getHRHeader();		
		else if(department.equals("UE"))
			return keys.getUEHeader();
		else if(department.equals("EM2"))
			return keys.getEM2Header();
		
		else if(department.equals("ProjectTech"))
			return keys.getProjectTechHeader();		
		else if(department.equals("Planning2"))
			return keys.getPlanning2Header();
		else if(department.equals("Systems"))
			return keys.getSystemHeader();
		else if(department.equals("Marketing"))
			return keys.getMarketingHeader();
		
		return null;
	}
	
	public String generateFilePath()
	{
		String path=keys.getDataLocation();
		//String path="G:/GreaterNoidaFiles/";
		File dir=new File(path);
		if(!dir.exists())
			dir.mkdir();
		path=path+getYear()+"/";
		dir=new File(path);
		if(!dir.exists())
			dir.mkdir();
		path=path+getMonth()+"/";
		dir=new File(path);
		if(!dir.exists())
			dir.mkdir();
		path=path+new Random().nextInt(20)+"/";
		dir=new File(path);
		if(!dir.exists())
			dir.mkdir();
		return path;
	}
	
	public ArrayList<String> generateGeneralParams(General general)
	{
		ArrayList<String> params=new ArrayList<String>();
		params.add(general.getDepartment()+"@department");
		if(general.getAllotmentNo().trim().length()>0)
			params.add(general.getAllotmentNo()+"@allotmentNo");
		if(!general.getScheme().trim().equals("Select"))
			params.add(general.getScheme()+"@scheme");
		if(general.getSector()!=null && general.getSector().trim().length()>0)
			params.add(general.getSector()+"@sector");
		if(general.getAlloteeName().trim().length()>0)
			params.add(general.getAlloteeName()+"@alloteeName");
		if(general.getPlotNo().trim().length()>0)
			params.add(general.getPlotNo()+"@plotNo");
		if(general.getPlotSize().trim().length()>0)
			params.add(general.getPlotSize()+"@plotSize");
		if(general.getFts().trim().length()>0)
			params.add(general.getFts()+"@fts");
		return params;
	}
	
	public ArrayList<String> generateFinanceParams(Finance finance)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(finance.getBankName().trim().length()>0)
			params.add(finance.getBankName()+"@bankName");
		if(finance.getBranchName().trim().length()>0)
			params.add(finance.getBranchName()+"@branchName");
		if(finance.getSector().trim().length()>0)
			params.add(finance.getSector()+"@sector");
		if(finance.getStatement().trim().length()>0)
			params.add(finance.getStatement()+"@statement");
		if(finance.getRegisterName().trim().length()>0)
			params.add(finance.getRegisterName()+"@registerName");
		if(finance.getSubdepartment().trim().length()>0)
			params.add(finance.getSubdepartment()+"@subdepartment");
		return params;
	}
	
	
	
	
	
	public ArrayList<String> generateProjectParams(Project project)
	{
		ArrayList<String> params=new ArrayList<String>();
		params.add(project.getDepartment()+"@department");
		if(!project.getWorkCircle().trim().equals("Select"))
			params.add(project.getWorkCircle()+"@workCircle");
		if(project.getWorkName().trim().length()>0)
			params.add(project.getWorkName()+"@workName");
		if(project.getContractorName().trim().length()>0)
			params.add(project.getContractorName()+"@contractorName");
		if(project.getOpaFts().trim().length()>0)
			params.add(project.getOpaFts()+"@opaFts");
		if(project.getYear().trim().length()>0)
			params.add(project.getYear()+"@year");
		return params;
	}
	
	public ArrayList<String> generatePlanParams(Planning planning)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(!planning.getSubDepartment().equalsIgnoreCase("Select"))
			params.add(planning.getSubDepartment()+"@subDepartment");
		if(planning.getCategory().trim().length()>0)
			params.add(planning.getCategory()+"@category");
		if(planning.getAllotmentNo().trim().length()>0)
			params.add(planning.getAllotmentNo()+"@allotmentNo");
		if(planning.getBpNo().trim().length()>0)
			params.add(planning.getBpNo()+"@bpNo");
		if(planning.getApplicantName().trim().length()>0)
			params.add(planning.getApplicantName()+"@applicantName");
		if(planning.getSector().trim().length()>0)
			params.add(planning.getSector()+"@sector");
		if(planning.getPlotNo().trim().length()>0)
			params.add(planning.getPlotNo()+"@plotNo");
		if(planning.getBlockNo().trim().length()>0)
			params.add(planning.getBlockNo()+"@blockNo");
		if(planning.getPlotSize().trim().length()>0)
			params.add(planning.getPlotSize()+"@plotSize");
		return params;
	}
	
	public ArrayList<String> generateEMParams(EM em)
	{
		ArrayList<String> params=new ArrayList<String>();
		System.out.println("value:"+params);
		if(em.getSector()!=null && em.getSector().trim().length()>0)
			params.add(em.getSector()+"@sector");
		if(em.getCategory()!=null && em.getCategory().trim().length()>0)
			params.add(em.getCategory()+"@category");
		if(em.getOpaFts()!=null && em.getOpaFts().trim().length()>0)
			params.add(em.getOpaFts()+"@opaFts");
		if(em.getWorkName()!=null && em.getWorkName().trim().length()>0)
			params.add(em.getWorkName()+"@workName");
		if(em.getContractorName()!=null && em.getContractorName().trim().length()>0)
			params.add(em.getContractorName()+"@contractorName");
		if(em.getFileNo()!=null && em.getFileNo().trim().length()>0)
			params.add(em.getFileNo()+"@fileNo");
		if(em.getDepartment()!=null && em.getDepartment().trim().length()>0)
			params.add(em.getDepartment()+"@department");
		return params;
	}
	
	public ArrayList<String> generateEM3Params(EM3 em)
	{
		ArrayList<String> params=new ArrayList<String>();
		System.out.println("value:"+params);
		if(em.getSector()!=null && em.getSector().trim().length()>0)
			params.add(em.getSector()+"@sector");
		if(em.getCategory()!=null && em.getCategory().trim().length()>0)
			params.add(em.getCategory()+"@category");
		if(em.getOpa_Fts()!=null && em.getOpa_Fts().trim().length()>0)
			params.add(em.getOpa_Fts()+"@opa_Fts");
		if(em.getWorkName()!=null && em.getWorkName().trim().length()>0)
			params.add(em.getWorkName()+"@workName");
		if(em.getContractorName()!=null && em.getContractorName().trim().length()>0)
			params.add(em.getContractorName()+"@contractorName");
		if(em.getFileNo()!=null && em.getFileNo().trim().length()>0)
			params.add(em.getFileNo()+"@fileNo");
		if(em.getDepartment()!=null && em.getDepartment().trim().length()>0)
			params.add(em.getDepartment()+"@department");
		return params;
	}
	
	
	/*------------------------------------SIX NEW DEPARTMENTS----------------------------------------------*/
	public ArrayList<String> generateHRParams(HR hr)
	{
		ArrayList<String> params=new ArrayList<String>();
		
		if(hr.getLine_No()!=null && hr.getLine_No().trim().length()>0)
			params.add(hr.getLine_No()+"@line_No");		
		if(hr.getFileNo()!=null && hr.getFileNo().trim().length()>0)
			params.add(hr.getFileNo()+"@fileNo");		
		if(hr.getFileCode()!=null && hr.getFileCode().trim().length()>0)
			params.add(hr.getFileCode()+"@fileCode");	
		
		if(hr.getFile_Subject()!=null && hr.getFile_Subject().trim().length()>0)
			params.add(hr.getFile_Subject()+"@file_Subject");		
		if(hr.getOpaFts()!=null && hr.getOpaFts().trim().length()>0)
			params.add(hr.getOpaFts()+"@opaFts");		
		if(hr.getNo_Of_Noteeing()!=null && hr.getNo_Of_Noteeing().trim().length()>0)
			params.add(hr.getNo_Of_Noteeing()+"@no_Of_Noteeing");		
		if(hr.getTotal_No_Of_Pages()!=null && hr.getTotal_No_Of_Pages().trim().length()>0)
			params.add(hr.getTotal_No_Of_Pages()+"@total_No_Of_Pages");
		return params;
	}
	
	
	
	public ArrayList<String> generateUEParams(UE ue)
	{    System.out.println("generateUEParams:1");
		ArrayList<String> params=new ArrayList<String>();
		 System.out.println("2");
		if(ue.getName_Of_Work()!=null && ue.getName_Of_Work().trim().length()>0)
			 System.out.println("3");
			params.add(ue.getName_Of_Work()+"@name_Of_Work");	
			 System.out.println("4");
		if(ue.getContractor_Name()!=null && ue.getContractor_Name().trim().length()>0)
			 System.out.println("5");
			params.add(ue.getContractor_Name()+"@contractor_Name");		
			 System.out.println("6");
		if(ue.getDepartment()!=null && ue.getDepartment().trim().length()>0)
			 System.out.println("7");
			params.add(ue.getDepartment()+"@department");	
			 System.out.println("8");
		if(ue.getFile_Number()!=null && ue.getFile_Number().trim().length()>0)
			 System.out.println("9");
			params.add(ue.getFile_Number()+"@file_Number");		
			 System.out.println("10");
		if(ue.getNo_Of_Cros()!=null && ue.getNo_Of_Cros().trim().length()>0)
			 System.out.println("2");
			params.add(ue.getNo_Of_Cros()+"@no_Of_Cros");		
		
		
		return params;
	}
	
	public ArrayList<String> generateEM2Params(EM2 em2)
	{
		ArrayList<String> params=new ArrayList<String>();
		
		if(em2.getCategory()!=null && em2.getCategory().trim().length()>0)
			params.add(em2.getCategory()+"@category");		
		if(em2.getOpaFts()!=null && em2.getOpaFts().trim().length()>0)
			params.add(em2.getOpaFts()+"@opaFts");			
		if(em2.getName_Of_Work()!=null && em2.getName_Of_Work().trim().length()>0)
			params.add(em2.getName_Of_Work()+"@name_Of_Work");	
		
		if(em2.getContractor_Name()!=null && em2.getContractor_Name().trim().length()>0)
			params.add(em2.getName_Of_Work()+"@contractor_Name");		
		if(em2.getDepartment()!=null && em2.getDepartment().trim().length()>0)
			params.add(em2.getDepartment()+"@department");		
		if(em2.getFile_number()!=null && em2.getFile_number().trim().length()>0)
			params.add(em2.getFile_number()+"@file_number");		
		
		return params;
	}
	
	
	public ArrayList<String> generateSystemParams(Systems sy)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(sy.getLotNo()!=null && sy.getLotNo().trim().length()>0)
			params.add(sy.getLotNo()+"@lotNo");
		
		if(sy.getClerk_Name()!=null && sy.getClerk_Name().trim().length()>0)
			params.add(sy.getClerk_Name()+"@clerk_Name");
		
		if(sy.getFile_No()!=null && sy.getFile_No().trim().length()>0)
			params.add(sy.getFile_No()+"@file_No");
		
		if(sy.getDepartment()!=null && sy.getDepartment().trim().length()>0)
			params.add(sy.getDepartment()+"@department");
		
		if(sy.getTotal_No_Of_Pages()!=null && sy.getTotal_No_Of_Pages().trim().length()>0)
			params.add(sy.getTotal_No_Of_Pages()+"@Total_No_Of_Pages");
		
		if(sy.getNo_Of_NoteSheet()!=null && sy.getNo_Of_NoteSheet().trim().length()>0)
			params.add(sy.getNo_Of_NoteSheet()+"@no_Of_NoteSheet");
		
		if(sy.getSubjectName()!=null && sy.getSubjectName().trim().length()>0)
			params.add(sy.getSubjectName()+"@subjectName");
		
		if(sy.getOptFts()!=null && sy.getOptFts().trim().length()>0)
			params.add(sy.getOptFts()+"@optFts");
		
		
		return params;
	}
	
	public ArrayList<String> generateMarketingParams(Marketing mk)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(mk.getLot_No()!=null && mk.getLot_No().trim().length()>0)
			params.add(mk.getLot_No()+"@lot_No");
		
		if(mk.getClerk_Name()!=null && mk.getClerk_Name().trim().length()>0)
			params.add(mk.getClerk_Name()+"@clerk_Name");
		
		if(mk.getFile_No()!=null && mk.getFile_No().trim().length()>0)
			params.add(mk.getFile_No()+"@file_No");
		
		if(mk.getDepartment()!=null && mk.getDepartment().trim().length()>0)
			params.add(mk.getDepartment()+"@department");
		
		if(mk.getTotal_No_Of_Pages()!=null && mk.getTotal_No_Of_Pages().trim().length()>0)
			params.add(mk.getTotal_No_Of_Pages()+"@Total_No_Of_Pages");
		
		if(mk.getNo_Of_NoteSheet()!=null && mk.getNo_Of_NoteSheet().trim().length()>0)
			params.add(mk.getNo_Of_NoteSheet()+"@no_Of_NoteSheet");
		
		if(mk.getApplicant_Name()!=null && mk.getApplicant_Name().trim().length()>0)
			params.add(mk.getApplicant_Name()+"@applicant_Name");
		
		if(mk.getFts_No_Opa_No()!=null && mk.getFts_No_Opa_No().trim().length()>0)
			params.add(mk.getFts_No_Opa_No()+"@fts_No_Opa_No");
		
		
		return params;
	}
	
	public ArrayList<String> generateLandParams(Land mk)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(mk.getFileNo()!=null && mk.getFileNo().trim().length()>0)
			params.add(mk.getFileNo()+"@fileNo");
		
		if(mk.getFilesub()!=null && mk.getFilesub().trim().length()>0)
			params.add(mk.getFilesub()+"@fileSub");
		
		if(mk.getOpaFts()!=null && mk.getOpaFts().trim().length()>0)
			params.add(mk.getOpaFts()+"@opaFts");
		
		if(mk.getDepartment()!=null && mk.getDepartment().trim().length()>0)
			params.add(mk.getDepartment()+"@department");
		
		if(mk.getServey_letter()!=null && mk.getServey_letter().trim().length()>0)
			params.add(mk.getServey_letter()+"@servey_letter");
		
		if(mk.getNotifection()!=null && mk.getNotifection().trim().length()>0)
			params.add(mk.getNotifection()+"@notifection");
		
		if(mk.getTotal_pages()!=null && mk.getTotal_pages().trim().length()>0)
			params.add(mk.getTotal_pages()+"@total_pages");
		
		if(mk.getNo_of_notsheet()!=null && mk.getNo_of_notsheet().trim().length()>0)
			params.add(mk.getNo_of_notsheet()+"@no_of_notsheet");
		
		
		return params;
	}
	
	
	
	public ArrayList<String> generatePlanning2Params(Planning2 plan)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(plan.getLot_No()!=null && plan.getLot_No().trim().length()>0)
			params.add(plan.getLot_No()+"@lot_No");		
		if(plan.getClerk_Name()!=null && plan.getClerk_Name().trim().length()>0)
			params.add(plan.getClerk_Name()+"@clerk_Name");		
		if(plan.getCategory()!=null && plan.getCategory().trim().length()>0)
			params.add(plan.getCategory()+"@category");
		
		if(plan.getAllotmentNo()!=null && plan.getAllotmentNo().trim().length()>0)
			params.add(plan.getAllotmentNo()+"@allotmentNo");		
		if(plan.getApplicantName()!=null && plan.getApplicantName().trim().length()>0)
			params.add(plan.getApplicantName()+"@applicantName");			
		if(plan.getOpaFts()!=null && plan.getOpaFts().trim().length()>0)
			params.add(plan.getOpaFts()+"@opaFts");
		
		
		return params;
	}
	
	
	public ArrayList<String> generateProjectTechParams(ProjectTech pt)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(pt.getSector()!=null && pt.getSector().trim().length()>0)
			params.add(pt.getSector()+"@sector");		
		if(pt.getCategory()!=null && pt.getCategory().trim().length()>0)
			params.add(pt.getCategory()+"@category");		
		if(pt.getName_Of_Work()!=null && pt.getName_Of_Work().trim().length()>0)
			params.add(pt.getName_Of_Work()+"@name_Of_Work");
		
		if(pt.getContractor_Name()!=null && pt.getContractor_Name().trim().length()>0)
			params.add(pt.getContractor_Name()+"@contractor_Name");		
		if(pt.getDepartment()!=null && pt.getDepartment().trim().length()>0)
			params.add(pt.getDepartment()+"@department");			
		if(pt.getFileNumber()!=null && pt.getFileNumber().trim().length()>0)
			params.add(pt.getFileNumber()+"@fileNumber");
		
		
		return params;
	}
	
	
	
	
	/*-----------------------------------------------------------------------------------------------------*/
	
	
	public ArrayList<String> generateLawParams(Law law)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(law.getCourtName()!=null && law.getCourtName().trim().length()>0)
			params.add(law.getCourtName()+"@courtName");
		if(law.getPetitionNo()!=null && law.getPetitionNo().trim().length()>0)
			params.add(law.getPetitionNo()+"@petitionNo");
		if(law.getPartyName()!=null && law.getPartyName().trim().length()>0)
			params.add(law.getPartyName()+"@partyName");
		if(law.getPetitionerAdvocate()!=null && law.getPetitionerAdvocate().trim().length()>0)
			params.add(law.getPetitionerAdvocate()+"@petitionerAdvocate");
		if(law.getRespondentAdvocate()!=null && law.getRespondentAdvocate().trim().length()>0)
			params.add(law.getRespondentAdvocate()+"@respondentAdvocate");
		if(law.getYear()!=null && law.getYear().trim().length()>0)
			params.add(law.getYear()+"@year");
		if(law.getRelatedDepartment()!=null && law.getRelatedDepartment().trim().length()>0)
			params.add(law.getRelatedDepartment()+"@relatedDepartment");
		return params;
	}
	
	public String getAgendaLocation(String agendaNo,String fileNo)
	{
		File file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/Idx 00"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/Idx-00"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/idx 00"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/idx-00"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/INDEX "+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/00"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/Idx-0"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/Idx 0"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/idx-0"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/idx 0"+fileNo+".pdf");
		if(!file.exists())
			file=new File(keys.getAgendaLocation()+"Board Meeting/"+agendaNo+"/0"+fileNo+".pdf");
		System.out.println("Returning is "+file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(fileNo)));
		return file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(fileNo));
	}
}