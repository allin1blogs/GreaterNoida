package com.ak.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ak.beans.Keys;
import com.ak.daos.OracleData;
import com.ak.modals.General;
import com.ak.modals.UserDepartments;
import com.ak.modals.Users;
import com.ak.services.CommonService;
import com.ak.services.GenAgePlanService;
import com.ak.services.UserService;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Controller
public class UserController
{
	@Autowired
	private ModelInitializer modelInitializer;
	@Autowired
	private Keys keys;
	@Autowired
	private UserService userService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private GenAgePlanService genAgePlanService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String authUser(ModelMap model,HttpServletRequest request,@RequestParam("userId")String userId,@RequestParam("password")String password)
	{
		String flage=userService.authUser(userId,password);
		if(flage.equals("Success"))
		{
			HttpSession session=request.getSession();
			session.setAttribute("uBean",userService.getUserBean(userId));
			commonService.insertLogs(userId,"Logged In");
			return "redirect:/home";
		}
		else
		{
			if(flage.equals("Disabled"))
				model.addAttribute("msg","Your UserId has been disabled<br>Contact your Administrator");
			else
				model.addAttribute("msg","Wrong UserId/Password");
			return "index";
		}
	}
	
	@RequestMapping(value="/sync",method=RequestMethod.GET)
	public String sync(HttpServletRequest request)
	{
		ArrayList<General> records=userService.getAllRecords("IT");
		for(General general:records)
		{
			General gen=OracleData.getGen(general.getAllotmentNo(),"IT");
			if(gen!=null)
			{
				general.setScheme(gen.getScheme());
				general.setSector(gen.getSector());
				general.setAlloteeName(gen.getAlloteeName());
				general.setPlotNo(gen.getPlotNo());
				general.setPlotSize(gen.getPlotSize());
				genAgePlanService.insertOrUpdateGen(general);
			}
		}
		return null;
	}

	/*  ======================================SaveUser=============================== */	
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public String addUser(HttpServletRequest request,@ModelAttribute("userForm")Users user,@RequestParam("department")String[] departments,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		if(userService.isUserIdExists(user.getUserId()))
			flashAttributes.addFlashAttribute("msg","This is UserId already exists!");
		else
		{
			userService.insertOrUpdateUser(user);
			if(user.getUserType().equals("Administrator"))
			{
				ArrayList<UserDepartments> userDepartments=new ArrayList<UserDepartments>();
				for(String department:departments)
				{
					UserDepartments userDepartment=new UserDepartments();
					userDepartment.setDepartment(department);
					userDepartments.add(userDepartment);
					userDepartment.setUserId(user.getUserId());
				}
			}
			if(user.getUserType().equals("User"))
			{
				String[] sectors=request.getParameterValues("sector");
				ArrayList<UserDepartments> userDepartments=new ArrayList<UserDepartments>();
				for(String sector:sectors)
				{
					if(!sector.equals("") && sector.length()>0 && sector.indexOf("(")!=-1 && sector.indexOf(")")!=-1)
					{
						UserDepartments userDepartment=new UserDepartments();
						System.out.println("Sector is "+sector);
						userDepartment.setDepartment(sector.substring(sector.lastIndexOf("(")+1,sector.lastIndexOf(")")));
						userDepartment.setSector(sector.substring(0,sector.lastIndexOf("(")));
						userDepartment.setUserId(user.getUserId());
						userDepartments.add(userDepartment);
					}
				}
				userService.insertOrUpdateUserDepartments(userDepartments);
			}
			flashAttributes.addFlashAttribute("msg","UserId is created successfully.");
			commonService.insertLogs(uId,"Created UserId:"+uId+".");
		}
		return "redirect:/users";
	}
	
	@RequestMapping(value="/userDetails",method=RequestMethod.POST)
	public String userDetails(ModelMap model,HttpServletRequest request,@RequestParam("userId")String userId)
	{
		model=modelInitializer.initializeModel(model,request);
		if(model==null)
			return "error";
		Users user=userService.getUserDetails(userId);
		model.addAttribute("userForm",user);
		if(userId.equals(modelInitializer.getId(request)))
			model.addAttribute("read","false");
		else
			model.addAttribute("read","true");
		ArrayList<String> selectedDepartments=new ArrayList<String>();
		ArrayList<String> unSelectedDepartments=new ArrayList<String>();
		ArrayList<String> selectedSectors=new ArrayList<String>();
		ArrayList<String> unSelectedSectors=new ArrayList<String>();
		if(user.getUserType().equals("Super Administrator"))
		{
			selectedDepartments=commonService.getAllDepartments();
			for(String department:selectedDepartments)
				selectedSectors.addAll(commonService.getAllSectors(department));
		}
		else
		{
			ArrayList<UserDepartments> userDepartments=commonService.getAllDepartments(userId);
			if(modelInitializer.getIdModule(request).equals("Super Administrator"))
			{
				String[] unDepts=keys.getDepartments();
				for(String unDept:unDepts) {
					unSelectedDepartments.add(unDept);
					
				}
				for(int i=0;i<userDepartments.size();i++) {
					System.out.println("==================="+userDepartments.size());
				if(unSelectedDepartments.contains(userDepartments.get(i).getDepartment()))
					unSelectedDepartments.remove(userDepartments.get(i).getDepartment());
				}
			}
			else
			{
				unSelectedDepartments=modelInitializer.getDepartments(request);
				for(UserDepartments userDept:userDepartments)
				{
					if(unSelectedDepartments.contains(userDept.getDepartment()))
						unSelectedDepartments.remove(userDept.getDepartment());
				}
			}
			for(UserDepartments userDepartment:userDepartments)
			{
				if(!selectedDepartments.contains(userDepartment.getDepartment()))
					selectedDepartments.add(userDepartment.getDepartment());
			}
			if(user.getUserType().equals("Administrator"))
			{
				for(String department:selectedDepartments)
					selectedSectors.addAll(commonService.getAllSectors(department));
			}
			else
			{
				for(UserDepartments userDepartment:userDepartments)
				{
					if(!selectedSectors.contains(userDepartment.getSector()))
						selectedSectors.add(userDepartment.getSector()+"("+userDepartment.getDepartment()+")");
				}
			}
		}
		model.addAttribute("selectedDepartments",selectedDepartments);
		model.addAttribute("unSelectedDepartments",unSelectedDepartments);
		model.addAttribute("selectedSectors",selectedSectors);
		model.addAttribute("unSelectedSectors",unSelectedSectors);
		return "users/userDetails";
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public String updateUser(HttpServletRequest request,@ModelAttribute("userForm")Users user,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		String[] departments=request.getParameterValues("department");
		String[] sectors=request.getParameterValues("sector");
		userService.insertOrUpdateUser(user);
		if(user.getUserType().equals("Administrator"))
		{
			ArrayList<UserDepartments> userDepartments=new ArrayList<UserDepartments>();
			for(String department:departments)
			{
				UserDepartments userDepartment=new UserDepartments();
				userDepartment.setDepartment(department);
				userDepartments.add(userDepartment);
				userDepartment.setUserId(user.getUserId());
			}
			userService.insertOrUpdateUserDepartments(userDepartments);
		}
		if(user.getUserType().equals("User"))
		{
			ArrayList<UserDepartments> userDepartments=new ArrayList<UserDepartments>();
			if(!(sectors==null)) {
			for(String sector:sectors)
			{
				if(!sector.equals("") && sector.length()>0 && sector.indexOf("(")!=-1 && sector.indexOf(")")!=-1)
				{
					UserDepartments userDepartment=new UserDepartments();
					System.out.println("Sector is "+sector);
					userDepartment.setDepartment(sector.substring(sector.lastIndexOf("(")+1,sector.lastIndexOf(")")));
					userDepartment.setSector(sector.substring(0,sector.indexOf("(")));
					userDepartment.setUserId(user.getUserId());
					userDepartments.add(userDepartment);
				}
			}
			}
			else {
				UserDepartments userDepartment=new UserDepartments();
				userDepartment.setUserId(user.getUserId());
				userDepartment.setDepartment(departments[0]);
				userDepartment.setSector("NA");
				userDepartments.add(userDepartment);
			}
			userService.insertOrUpdateUserDepartments(userDepartments);
		}
		flashAttributes.addFlashAttribute("msg","UserId is updated successfully.");
		commonService.insertLogs(uId,"Updated UserId:"+uId+".");
		return "redirect:/users";
	}
	
	@RequestMapping(value="/removeUser",method=RequestMethod.GET)
	public String removeUser(HttpServletRequest request,@RequestParam("userId")String userId,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		userService.removeUser(userId);
		commonService.insertLogs(uId,"Removed UserId:"+userId+".");
		flashAttributes.addFlashAttribute("msg","UserId has been removed successfully.");
		return "redirect:/users";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "index";
		commonService.insertLogs(uId,"Logged Out...");
		HttpSession session=request.getSession(false);
		session.removeAttribute("uBean");
		session.invalidate();
		return "index";
	}
}