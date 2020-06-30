package com.ak.controllers;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.ak.beans.Keys;
import com.ak.beans.UserBean;
import com.ak.modals.EM;
import com.ak.modals.EM2;
import com.ak.modals.EM3;
import com.ak.modals.Finance;
import com.ak.modals.General;
import com.ak.modals.HR;
import com.ak.modals.Law;
import com.ak.modals.Marketing;
import com.ak.modals.Systems;
import com.ak.modals.UE;
import com.ak.modals.Planning;
import com.ak.modals.Planning2;
import com.ak.modals.Project;
import com.ak.modals.ProjectTech;
import com.ak.modals.UserDepartments;
import com.ak.services.CommonService;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Component("modelInitializer")
public class ModelInitializer
{
	@Autowired
	private Keys keys;
	@Autowired
	private CommonService commonService;
	
	public ModelMap initializeModel(ModelMap model,HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		if(session==null || getId(request)==null)
			return null;
		model.addAttribute("user",getId(request));
		model=getRights(model,request);
		model.addAttribute("departments",getDepartments(request));
		return model;
	}
	
	public String getId(HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		if(session==null)
			return null;
		UserBean uBean=(UserBean)session.getAttribute("uBean");
		if(uBean==null)
			return null;
		return uBean.getUserId();
	}
	
	public String getIdModule(HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		UserBean uBean=(UserBean)session.getAttribute("uBean");
		return uBean.getUserType();
	}
	
	public ModelMap getRights(ModelMap model,HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		UserBean uBean=(UserBean)session.getAttribute("uBean");
		if(uBean.getUpload())
			model.addAttribute("upload","1");
		if(uBean.getView())
			model.addAttribute("view","1");
		if(uBean.getDownload())
			model.addAttribute("download","1");
		if(uBean.getPrint())
			model.addAttribute("print","1");
		if(uBean.getUpdate())
			model.addAttribute("update","1");
		if(uBean.getReport())
			model.addAttribute("report","1");
		if(uBean.getLogs())
			model.addAttribute("track","1");
		if(uBean.getAgenda())
			model.addAttribute("agenda","1");
		return model;
	}
	
	public ArrayList<String> getDepartments(HttpServletRequest request)
	{
		ArrayList<String> departments=new ArrayList<String>();;
		UserBean uBean=(UserBean)(request.getSession(false).getAttribute("uBean"));
		if(uBean.getUserType().equals("Super Administrator"))
		{
			String[] depts=keys.getDepartments();
			for(String dept:depts)
				departments.add(dept);
		}
		else
		{
			ArrayList<UserDepartments> userDepartments=commonService.getAllDepartments(uBean.getUserId());
			for(UserDepartments userDepartment:userDepartments)
			{
				if(!departments.contains(userDepartment.getDepartment()))
					departments.add(userDepartment.getDepartment());
			}
		}
		return departments;
	}
	
	public ModelMap getDepartmentObject(ModelMap model,String department)
	{
		if(department.equals("General") || department.equals("Residential") || department.equals("Institutional") || department.equals("IT") || department.equals("Group Housing") || department.equals("Builder") || department.equals("Commercial") || department.equals("Industry") || department.equals("Abadi 6%"))
		{
			General general=new General();
			general.setDepartment(department);
			model.addAttribute("generalForm",general);
		}
		if(department.equals("Finance"))
			model.addAttribute("financeForm",new Finance());
		
		if(department.equals("Project") || department.equals("Sewage") || department.equals("Water") ) {
			Project project=new Project();
			project.setDepartment(department);
			model.addAttribute("projectForm",new Project());
		}
		if(department.equals("Planning") || department.equals("Planning(Policies)"))
		{
			model.addAttribute("planningForm",new Planning());
			if(department.equals("Planning(Policies)"))
				model.addAttribute("pol","1");
		}
		if(department.equals("EM")) {
			model.addAttribute("emForm",new EM());
		}
		if(department.equals("EM3")) {
			model.addAttribute("em3Form",new EM3());
		}
		
		if(department.equals("Planning2"))
			model.addAttribute("planning2Form",new Planning2());
		if(department.equals("EM2"))
			model.addAttribute("EM2Form",new EM2());
		if(department.equals("Law"))
			model.addAttribute("lawForm",new Law());
		
		
		if(department.equals("Systems"))
			model.addAttribute("SystemForm",new Systems());
		
		if(department.equals("Marketing"))
			model.addAttribute("MarketingForm",new Marketing());

		if(department.equals("HR"))
			model.addAttribute("HRForm",new HR());
		
		if(department.equals("UE"))
			model.addAttribute("UEForm",new UE());
		
		if(department.equals("ProjectTech"))
			model.addAttribute("ProjectTechForm",new ProjectTech());
		
		return model;
	}
	
	public String getView(String department)
	{
		if(department.equals("Residential") || department.equals("Institutional") || department.equals("IT") || department.equals("Group Housing") || department.equals("Builder") || department.equals("Commercial") || department.equals("Industry") || department.equals("Abadi 6%"))
			return "General";
		else if(department.equals("Finance"))
			return "Finance";
		else if(department.equals("Project") || department.equals("Sewage") || department.equals("Water"))
			return "Project";
		else if(department.equals("Planning") || department.equals("Planning(Policies)"))
			return "Planning";
		else if(department.equals("EM"))
			return "EM";
		else if(department.equals("EM2"))
			return "EM2";
		else if(department.equals("EM3"))
			return "EM3";
		else if(department.equals("Law"))
			return "Law";
		else if(department.equals("Systems"))
			return "Systems";
		else if(department.equals("Marketing"))
			return "Marketing";
		else if (department.equals("HR"))
			return "HR";
		
		else if (department.equals("Planning2"))
			return "Planning2";
		else if (department.equals("UE"))
			return "UE";
		
		
		else if (department.equals("ProjectTech"))
			return "ProjectTech";
		
		else
			return "constr";
	}
}