package com.ak.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ak.modals.Logs;
import com.ak.modals.UserDepartments;
import com.ak.modals.Users;
import com.ak.services.CommonService;
import com.ak.services.UserService;

/*
 *	@Author
 *	Swapril Tyagi 
 *  Update By:Ashok Khatri
 *  Update By:Preeti Rani...
*/


@Controller
public class CommonController
{
	@Autowired
	ModelInitializer modelInitializer;
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(ModelMap model,HttpServletRequest request)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		return "home";
	}
	 

	/* =========================USERS======1====================== */
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String users(ModelMap model,HttpServletRequest request)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		if(modelInitializer.getIdModule(request).equals("Super Administrator"))
		{
			if(uId.equals("CEO"))
			{
				model.addAttribute("userForm",userService.getUserDetails(uId));
				System.out.println("===");
				return "users/ceoDetails";
			}
			ArrayList<String> users=commonService.getAllUsers(uId);
			if(!uId.equals("Admin"))
				users.remove("Admin");
			model.addAttribute("users",users);
			model.addAttribute("userForm",new Users());
		}
		else if(modelInitializer.getIdModule(request).equals("Administrator"))
		{  
			model.addAttribute("userForm",new Users());
			
			ArrayList<String> departments=modelInitializer.getDepartments(request);
			ArrayList<String> users=new ArrayList<String>();
			
			for(String department:departments)
				users.addAll(commonService.getAllUsers(department,uId));
			
			
			model.addAttribute("users",users);
		}
		else if(modelInitializer.getIdModule(request).equals("User"))
		{
			model.addAttribute("userForm",userService.getUserDetails(uId));
			ArrayList<UserDepartments> userDepartments=commonService.getAllDepartments(uId);
			ArrayList<String> departments=new ArrayList<String>();
			ArrayList<String> sectors=new ArrayList<String>();
			for(UserDepartments userDepartment:userDepartments)
			{
				if(!departments.contains(userDepartment.getDepartment()))
					departments.add(userDepartment.getDepartment());
				sectors.add(userDepartment.getSector()+"("+userDepartment.getDepartment()+")");
			}
			model.addAttribute("selectedDepartments",departments);
			model.addAttribute("selectedSectors",sectors);
			return "users/userDetailsForUserModule";
		}
		return "users/users";
	}
	
	/*============================================================================= */
	
	
	
	@RequestMapping(value="/uploadManual",method=RequestMethod.GET)
	public String uploadManual(ModelMap model,HttpServletRequest request,@RequestParam("department")String department)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		model=modelInitializer.getDepartmentObject(model,department);
		return "departments/"+modelInitializer.getView(department)+"/upload";
	}
	
	/* ---------------------------------------------------------------------- */
	@RequestMapping(value="/uploadBulk",method=RequestMethod.GET)
	public String uploadBulk(ModelMap model,HttpServletRequest request)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		return "departments/uploadBulk";
	}

	/*-------------------------------------------------------------------------------*/
	@RequestMapping(value="/retrieve",method=RequestMethod.GET)
	public String retrieve(ModelMap model,HttpServletRequest request,@ModelAttribute("department")String department)
	{
		System.out.println("=======");
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		model=modelInitializer.getDepartmentObject(model,department);
		if(modelInitializer.getIdModule(request).equals("Super Administrator") || modelInitializer.getIdModule(request).equals("Administrator"))
			model.addAttribute("sectors",commonService.getAllSectors(department));
		else
			model.addAttribute("sectors",commonService.getAllSectors(department,uId));
		System.out.println("Null_Department");
		if(modelInitializer.getView(department).equals("constr")) {
			return "departments/constr";
		}
			
		else {
			System.out.println("HI============");
			System.out.println("HI============"+"departments/"+modelInitializer.getView(department)+"/retrieve");
			return "departments/"+modelInitializer.getView(department)+"/retrieve";
		}
			
	}
	
	@RequestMapping(value="/agenda",method=RequestMethod.GET)
	public String agenda(ModelMap model,HttpServletRequest request)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		return "agenda/agenda";
	}
	
	@RequestMapping(value="/logs",method=RequestMethod.GET)
	public String logs(ModelMap model,HttpServletRequest request)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		if(modelInitializer.getIdModule(request).equals("Super Administrator"))
			model.addAttribute("users",commonService.getAllUsers(uId));
		else
		{
			ArrayList<String> users=new ArrayList<String>();
			for(String department:modelInitializer.getDepartments(request))
				users.addAll(commonService.getAllUsers(department,uId));
			model.addAttribute("users",users);
		}
		return "tracking/logs";
	}
	
	@RequestMapping(value="/logs",method=RequestMethod.POST)
	public String logs(ModelMap model,HttpServletRequest request,@RequestParam("userId")String userId)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		if(modelInitializer.getIdModule(request).equals("Super Administrator"))
			model.addAttribute("users",commonService.getAllUsers(uId));
		else
		{
			ArrayList<String> users=new ArrayList<String>();
			for(String department:modelInitializer.getDepartments(request))
				users.addAll(commonService.getAllUsers(department,uId));
			model.addAttribute("users",users);
		}
		ArrayList<Logs> logs=commonService.retrieveLogs(userId);
		if(logs.isEmpty())
			model.addAttribute("msg","No Logs Found!");
		else
			model.addAttribute("logs",logs);
		return "tracking/logs";
	}
	
	@RequestMapping(value="/getHelp",method=RequestMethod.GET)
	public String getHelp(HttpServletRequest request,HttpServletResponse response,@RequestParam("help")String help,@RequestParam("key")String key,@RequestParam("value")String value)throws IOException
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		PrintWriter out=response.getWriter();
		String helpBox=commonService.getHelp(help,key,value);
		System.out.println("HelpBox is "+helpBox);
		out.println(helpBox);
		return null;
	}
}