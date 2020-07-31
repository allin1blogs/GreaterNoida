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
		ArrayList<Project> records=proFinService.retrievePro(params);
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
			Files.write(Paths.get(keys.getRepository()+project.getOpaFts()+"R.pdf"),correspondence.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+project.getOpaFts()+"R.pdf",keys.getRepository()+project.getOpaFts()+"R.pdf",project.getLocation()+project.getOpaFts()+"R.pdf");
			new File("C:/Resources/"+project.getOpaFts()+"R.pdf").delete();new File(keys.getRepository()+project.getOpaFts()+"R.pdf").delete();
		}
		proFinService.insertOrUpdatePro(project);
		flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
		commonService.insertLogs(uId,"Updated File of Project with Id:"+project.getOpaFts()+".");
		return "redirect:/updateFile?department=Project&sno="+project.getSno();
	}
	
	@RequestMapping(value="/updateFin",method=RequestMethod.POST)
	public String updateFin(HttpServletRequest request,@ModelAttribute("financeForm")Finance finance,@RequestParam("noteSheet")MultipartFile noteSheet,@RequestParam("correspondence")MultipartFile correspondence,RedirectAttributes flashAttributes)throws IOException
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		if(finance.getSubdepartment().equals("Bank Statement")) {
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
		{
			if(!noteSheet.getOriginalFilename().equals(finance.getAccountNo()+".pdf") && !noteSheet.getOriginalFilename().equals(finance.getStatement()+".pdf"))
			{
				flashAttributes.addFlashAttribute("msg","File name should be as AccountNo.pdf/Statement.pdf");
				return "redirect:/updateFile?department=Finance&sno="+finance.getSno();
			}
		}
		if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
		{
			if(new File(finance.getLocation()+finance.getAccountNo()+".pdf").exists() && noteSheet.getOriginalFilename().equals(finance.getAccountNo()+".pdf")){
			new File(finance.getLocation()+finance.getAccountNo()+".pdf").renameTo(new File("C:/Resources/"+finance.getAccountNo()+".pdf"));
			Files.write(Paths.get(keys.getRepository()+finance.getAccountNo()+".pdf"),noteSheet.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+finance.getAccountNo()+".pdf",keys.getRepository()+finance.getAccountNo()+".pdf",finance.getLocation()+finance.getAccountNo()+".pdf");
			new File("C:/Resources/"+finance.getAccountNo()+".pdf").delete();new File(keys.getRepository()+finance.getAccountNo()+".pdf").delete();
	     	}
			else if(new File(finance.getLocation()+finance.getStatement()+".pdf").exists() && noteSheet.getOriginalFilename().equals(finance.getStatement()+".pdf")){
				new File(finance.getLocation()+finance.getStatement()+".pdf").renameTo(new File("C:/Resources/"+finance.getStatement()+".pdf"));
				Files.write(Paths.get(keys.getRepository()+finance.getStatement()+".pdf"),noteSheet.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+finance.getStatement()+".pdf",keys.getRepository()+finance.getStatement()+".pdf",finance.getLocation()+finance.getStatement()+".pdf");
				new File("C:/Resources/"+finance.getStatement()+".pdf").delete();new File(keys.getRepository()+finance.getStatement()+".pdf").delete();
		     	}
			else {
				flashAttributes.addFlashAttribute("msg","File name should not be Proper");
				return "redirect:/updateFile?department=Finance&sno="+finance.getSno();

			}
			}
		}
		else if(finance.getSubdepartment().equals("Loan")) {
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				if(!noteSheet.getOriginalFilename().equals(finance.getAccountNo()+"L.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Notesheet name should be as AccountNoL.pdf");
					return "redirect:/updateFile?department=Finance&sno="+finance.getSno();
				}
			}
			if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
			{
				if(!correspondence.getOriginalFilename().equals(finance.getAccountNo()+"R.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Correspondence name should be as AccountNoR.pdf");
					return "redirect:/updateFile?department=Finance&sno="+finance.getSno();
				}
			}
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				new File(finance.getLocation()+finance.getAccountNo()+"L.pdf").renameTo(new File("C:/Resources/"+finance.getAccountNo()+"L.pdf"));
				Files.write(Paths.get(keys.getRepository()+finance.getAccountNo()+"L.pdf"),noteSheet.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+finance.getAccountNo()+"L.pdf",keys.getRepository()+finance.getAccountNo()+"L.pdf",finance.getLocation()+finance.getAccountNo()+"L.pdf");
				new File("C:/Resources/"+finance.getAccountNo()+"L.pdf").delete();new File(keys.getRepository()+finance.getAccountNo()+"L.pdf").delete();
			}
			if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
			{
				new File(finance.getLocation()+finance.getAccountNo()+"R.pdf").renameTo(new File("C:/Resources/"+finance.getAccountNo()+"R.pdf"));
				Files.write(Paths.get(keys.getRepository()+finance.getAccountNo()+"R.pdf"),correspondence.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+finance.getAccountNo()+"R.pdf",keys.getRepository()+finance.getAccountNo()+"R.pdf",finance.getLocation()+finance.getAccountNo()+"R.pdf");
				new File("C:/Resources/"+finance.getAccountNo()+"R.pdf").delete();new File(keys.getRepository()+finance.getAccountNo()+"R.pdf").delete();
			}
		}
		else {
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				if(!noteSheet.getOriginalFilename().equals(finance.getCodeNo()+"L.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Notesheet name should be as CodeNoL.pdf");
					return "redirect:/updateFile?department=Finance&sno="+finance.getSno();
				}
			}
			if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
			{
				if(!correspondence.getOriginalFilename().equals(finance.getCodeNo()+"R.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Correspondence name should be as CodeNoL.pdf");
					return "redirect:/updateFile?department=Finance&sno="+finance.getSno();
				}
			}
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				new File(finance.getLocation()+finance.getCodeNo()+"L.pdf").renameTo(new File("C:/Resources/"+finance.getCodeNo()+"L.pdf"));
				Files.write(Paths.get(keys.getRepository()+finance.getCodeNo()+"L.pdf"),noteSheet.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+finance.getCodeNo()+"L.pdf",keys.getRepository()+finance.getCodeNo()+"L.pdf",finance.getLocation()+finance.getCodeNo()+"L.pdf");
				new File("C:/Resources/"+finance.getCodeNo()+"L.pdf").delete();new File(keys.getRepository()+finance.getCodeNo()+"L.pdf").delete();
			}
			if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
			{
				new File(finance.getLocation()+finance.getCodeNo()+"R.pdf").renameTo(new File("C:/Resources/"+finance.getCodeNo()+"R.pdf"));
				Files.write(Paths.get(keys.getRepository()+finance.getCodeNo()+"R.pdf"),correspondence.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+finance.getCodeNo()+"R.pdf",keys.getRepository()+finance.getCodeNo()+"R.pdf",finance.getLocation()+finance.getCodeNo()+"R.pdf");
				new File("C:/Resources/"+finance.getCodeNo()+"R.pdf").delete();new File(keys.getRepository()+finance.getCodeNo()+"R.pdf").delete();
			}
		}
		proFinService.insertOrUpdateFin(finance);
		flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
		commonService.insertLogs(uId,"Updated File of finance with Id:"+finance.getCodeNo()+".");
		System.out.println("redirect:/updateFin?department=Finance&sno="+finance.getSno());
		return "redirect:/updateFile?department=Finance&sno="+finance.getSno();
	}
	
	
}