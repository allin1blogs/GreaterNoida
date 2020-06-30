package com.ak.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ak.beans.Keys;
import com.ak.modals.Finance;
import com.ak.modals.Project;
import com.ak.services.CommonService;
import com.ak.services.ProFinService;
import com.ak.utils.FileUtils;
import com.ak.utils.Utils;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Controller
public class ProFinController
{
	@Autowired
	private ModelInitializer modelInitializer;
	@Autowired
	private Utils utils;
	@Autowired
	private ProFinService proFinService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Keys keys;
	
	@RequestMapping(value="/retrieveFin",method=RequestMethod.GET)
	public String retrieveFin(ModelMap model,HttpServletRequest request,@ModelAttribute("financeForm")Finance finance)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		ArrayList<String> params=utils.generateFinanceParams(finance);
		ArrayList<Finance> records=proFinService.retrieveFin(params);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found!");
		else
		{
			request.getSession(false).setAttribute("params",params);
			model.addAttribute("records",records);
			model=modelInitializer.getRights(model,request);
		}
		return "departments/Finance/retrieve";
	}
	
	@RequestMapping(value="/updateFin",method=RequestMethod.POST)
	public String updateFin(HttpServletRequest request,@ModelAttribute("financeForm")Finance finance,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)throws IOException
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		if(file!=null && file.getOriginalFilename().trim().length()>0)
		{
			if(!file.getOriginalFilename().equals(finance.getStatement()+".pdf"))
			{
				flashAttributes.addFlashAttribute("msg","File name should be as PeriodOfStatement.pdf");
				return "redirect:/updateFile?department=Finance&sno="+finance.getSno();
			}
		}
		if(file!=null && file.getOriginalFilename().trim().length()>0)
		{
			new File(finance.getLocation()+finance.getStatement()+".pdf").renameTo(new File("C:/Resources/"+finance.getStatement()+".pdf"));
			Files.write(Paths.get(keys.getRepository()+finance.getStatement()+".pdf"),file.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+finance.getStatement()+".pdf",keys.getRepository()+finance.getStatement()+".pdf",finance.getLocation()+finance.getStatement()+".pdf");
			new File("C:/Resources/"+finance.getStatement()+".pdf").delete();new File(keys.getRepository()+finance.getStatement()+".pdf").delete();
		}
		proFinService.insertOrUpdateFin(finance);
		flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
		commonService.insertLogs(uId,"Updated File of Planning with Id:"+finance.getStatement()+".");
		return "redirect:/updateFile?department=Finance&sno="+finance.getSno();
	}
	
	@RequestMapping(value="/retrievePro",method=RequestMethod.GET)
	public String retrievePro(ModelMap model,HttpServletRequest request,@ModelAttribute("projectForm")Project project)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model.addAttribute("department",project.getDepartment());
		model=modelInitializer.initializeModel(model,request);
		if(modelInitializer.getIdModule(request).equals("Super Administrator") || modelInitializer.getIdModule(request).equals("Administrator"))
			model.addAttribute("sectors",commonService.getAllSectors(project.getDepartment()));
		else
			model.addAttribute("sectors",commonService.getAllSectors("Project",uId));
		ArrayList<String> params=utils.generateProjectParams(project);
		ArrayList<Project> records=proFinService.retrievePro(params,uId);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found!");
		else
		{
			request.getSession(false).setAttribute("params",params);
			model.addAttribute("records",records);
			model=modelInitializer.getRights(model,request);
		}
		return "departments/Project/retrieve";
	}
	
	@RequestMapping(value="/updatePro",method=RequestMethod.POST)
	public String updatePro(HttpServletRequest request,@ModelAttribute("projectForm")Project project,@RequestParam("noteSheet")MultipartFile noteSheet,@RequestParam("correspondence")MultipartFile correspondence,RedirectAttributes flashAttributes)throws IOException
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
		{
			if(!noteSheet.getOriginalFilename().equals(project.getOpaFts()+"L.pdf"))
			{
				flashAttributes.addFlashAttribute("msg","Notesheet name should be as OPA/FTSNoL.pdf");
				return "redirect:/updateFile?department=Project&sno="+project.getSno();
			}
		}
		if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
		{
			if(!correspondence.getOriginalFilename().equals(project.getOpaFts()+"R.pdf"))
			{
				flashAttributes.addFlashAttribute("msg","Correspondence name should be as OPA/FTSNoR.pdf");
				return "redirect:/updateFile?department=Project&sno="+project.getSno();
			}
		}
		if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
		{
			new File(project.getLocation()+project.getOpaFts()+"L.pdf").renameTo(new File("C:/Resources/"+project.getOpaFts()+"L.pdf"));
			Files.write(Paths.get(keys.getRepository()+project.getOpaFts()+"L.pdf"),noteSheet.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+project.getOpaFts()+"L.pdf",keys.getRepository()+project.getOpaFts()+"L.pdf",project.getLocation()+project.getOpaFts()+"L.pdf");
			new File("C:/Resources/"+project.getOpaFts()+"L.pdf").delete();new File(keys.getRepository()+project.getOpaFts()+"L.pdf").delete();
		}
		if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
		{
			new File(project.getLocation()+project.getOpaFts()+"R.pdf").renameTo(new File("C:/Resources/"+project.getOpaFts()+"R.pdf"));
			Files.write(Paths.get(keys.getRepository()+project.getOpaFts()+"R.pdf"),noteSheet.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+project.getOpaFts()+"R.pdf",keys.getRepository()+project.getOpaFts()+"R.pdf",project.getLocation()+project.getOpaFts()+"R.pdf");
			new File("C:/Resources/"+project.getOpaFts()+"R.pdf").delete();new File(keys.getRepository()+project.getOpaFts()+"R.pdf").delete();
		}
		proFinService.insertOrUpdatePro(project);
		flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
		commonService.insertLogs(uId,"Updated File of Project with Id:"+project.getOpaFts()+".");
		return "redirect:/updateFile?department=Project&sno="+project.getSno();
	}
}