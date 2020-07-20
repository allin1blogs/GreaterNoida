package com.ak.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ak.beans.Keys;
import com.ak.modals.Agenda;
import com.ak.modals.General;
import com.ak.modals.Planning;
import com.ak.modals.Planning2;
import com.ak.services.CommonService;
import com.ak.services.GenAgePlanService;
import com.ak.utils.FileUtils;
import com.ak.utils.Utils;

/*
 *	@Author 
 * 	Swapril Tyagi
*/

@Controller
public class GenAgePlanController
{
	@Autowired
	private ModelInitializer modelInitializer;
	@Autowired
	private Utils utils;
	@Autowired
	private GenAgePlanService genAgePlanService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Keys keys;
	
	@RequestMapping(value="/retrieveGen",method=RequestMethod.GET)
	public String retrieveGen(ModelMap model,HttpServletRequest request,@ModelAttribute("generalForm")General general)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		model.addAttribute("department",general.getDepartment());
		if(modelInitializer.getIdModule(request).equals("Super Administrator") || modelInitializer.getIdModule(request).equals("Administrator"))
			model.addAttribute("sectors",commonService.getAllSectors(general.getDepartment()));
		else
			model.addAttribute("sectors",commonService.getAllSectors(general.getDepartment(),uId));
		ArrayList<String> params=utils.generateGeneralParams(general);
		ArrayList<General> records=genAgePlanService.retrieveGen(params,uId);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found");
		else
		{
			request.getSession(false).setAttribute("params",params);
			model.addAttribute("records",records);
			model=modelInitializer.getRights(model,request);
		}
		return "departments/General/retrieve";
	}
	
	@RequestMapping(value="/updateGen",method=RequestMethod.POST)
	public String updateGen(HttpServletRequest request,@ModelAttribute("generalForm")General general,@RequestParam("noteSheet")MultipartFile noteSheet,@RequestParam("correspondence")MultipartFile correspondence,RedirectAttributes flashAttributes)throws IOException
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
		{
			if(!noteSheet.getOriginalFilename().equals(general.getAllotmentNo()+"L.pdf"))
			{
				flashAttributes.addFlashAttribute("msg","Notesheet name should be as AllotmentNoL.pdf");
				return "redirect:/updateFile?department=General&sno="+general.getSno();
			}
		}
		if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
		{
			if(!correspondence.getOriginalFilename().equals(general.getAllotmentNo()+"R.pdf"))
			{
				flashAttributes.addFlashAttribute("msg","Correspondence name should be as AllotmentNoR.pdf");
				return "redirect:/updateFile?department=General&sno="+general.getSno();
			}
		}
		if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
		{
			new File(general.getLocation()+general.getAllotmentNo()+"L.pdf").renameTo(new File("C:/Resources/"+general.getAllotmentNo()+"L.pdf"));
			Files.write(Paths.get(keys.getRepository()+general.getAllotmentNo()+"L.pdf"),noteSheet.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+general.getAllotmentNo()+"L.pdf",keys.getRepository()+general.getAllotmentNo()+"L.pdf",general.getLocation()+general.getAllotmentNo()+"L.pdf");
			new File("C:/Resources/"+general.getAllotmentNo()+"L.pdf").delete();new File(keys.getRepository()+general.getAllotmentNo()+"L.pdf").delete();
		}
		if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
		{
			new File(general.getLocation()+general.getAllotmentNo()+"R.pdf").renameTo(new File("C:/Resources/"+general.getAllotmentNo()+"R.pdf"));
			Files.write(Paths.get(keys.getRepository()+general.getAllotmentNo()+"R.pdf"),correspondence.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+general.getAllotmentNo()+"R.pdf",keys.getRepository()+general.getAllotmentNo()+"R.pdf",general.getLocation()+general.getAllotmentNo()+"R.pdf");
			new File("C:/Resources/"+general.getAllotmentNo()+"R.pdf").delete();new File(keys.getRepository()+general.getAllotmentNo()+"R.pdf").delete();
		}
		genAgePlanService.insertOrUpdateGen(general);
		flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
		commonService.insertLogs(uId,"Updated File of "+general.getDepartment()+" with Id:"+general.getAllotmentNo()+".");
		return "redirect:/updateFile?department=General&sno="+general.getSno();
	}
	
	@RequestMapping(value="/retrieveAgenda",method=RequestMethod.GET)
	public String retrieveAgenda(ModelMap model,HttpServletRequest request,@ModelAttribute("description")String description)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		ArrayList<Agenda> agendas=genAgePlanService.retrieveAge(description);
		if(agendas.isEmpty())
			model.addAttribute("msg","No Agenda Found!");
		else
		{
			request.getSession(false).setAttribute("description",description);
			model.addAttribute("agendas",agendas);
		}
		return "agenda/agenda";
	}
	
	@RequestMapping(value="/viewAgendaFile",method=RequestMethod.GET)
	public String viewAgendaFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("agendaNo")String agendaNo,@RequestParam("fileNo")String fileNo)throws IOException
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		PrintWriter out=response.getWriter();
		FileUtils.viewFile(fileNo,request.getServletContext().getRealPath("/")+"staticResources/pdfs/",utils.getAgendaLocation(agendaNo,fileNo),modelInitializer.getId(request),true);
		out.println(fileNo);
		return null;
	}
	
	@RequestMapping(value="/downloadAgendaFile",method=RequestMethod.GET)
	public String downloadAgendaFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("agendaNo")String agendaNo,@RequestParam("fileNo")String fileNo)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		FileUtils.downloadFile(response,fileNo+".pdf",utils.getAgendaLocation(agendaNo,fileNo),false,request.getServletContext().getRealPath("/")+"staticResources/pdfs/",modelInitializer.getId(request));
		return null;
	}
	
	@RequestMapping(value="/delAgView",method=RequestMethod.GET)
	public String delAgView(ModelMap model,HttpServletRequest request)
	{
		for(File file:new File(request.getServletContext().getRealPath("/")+"/staticResources/pdfs/").listFiles())
			file.delete();
		model=modelInitializer.initializeModel(model,request);
		if(model==null)
			return "error";
		model.addAttribute("agendas",genAgePlanService.retrieveAge((String)request.getSession(false).getAttribute("description")));
		return "agenda/agenda";
	}
	
	@RequestMapping(value="/retrievePlan",method=RequestMethod.GET)
	public String retrievePlan(ModelMap model,HttpServletRequest request,@ModelAttribute("planningForm")Planning planning)
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		ArrayList<String> params=utils.generatePlanParams(planning);
		ArrayList<Planning> records=genAgePlanService.retrievePlanRecords(params);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found!");
		else
		{
			model=modelInitializer.getRights(model,request);
			request.getSession(false).setAttribute("params",params);
			model.addAttribute("records",records);
		}
		return "departments/Planning/retrieve";
	}
	
	
	
	
	@RequestMapping(value="/updatePlan",method=RequestMethod.POST)
	public String updatePlan(HttpServletRequest request,@ModelAttribute("planForm")Planning planning,@RequestParam("noteSheet")MultipartFile noteSheet,@RequestParam("correspondence")MultipartFile correspondence,RedirectAttributes flashAttributes)throws IOException
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		String subd=planning.getSubDepartment();
		System.out.println(subd);
		if(subd.equals("Building NOC")) {
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				if(!noteSheet.getOriginalFilename().equals(planning.getBn_no()+"L.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Notesheet name should be as Bn_noL.pdf");
					return "redirect:/updateFile?department=Planning&sno="+planning.getSno();
				}
			}
			if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
			{
				if(!correspondence.getOriginalFilename().equals(planning.getBn_no()+"R.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Correspondence name should be as Bn_noR.pdf");
					return "redirect:/updateFile?department=Planning&sno="+planning.getSno();
				}
			}
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				new File(planning.getLocation()+planning.getBn_no()+"L.pdf").renameTo(new File("C:/Resources/"+planning.getBn_no()+"L.pdf"));
				Files.write(Paths.get(keys.getRepository()+planning.getBn_no()+"L.pdf"),noteSheet.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+planning.getBn_no()+"L.pdf",keys.getRepository()+planning.getBn_no()+"L.pdf",planning.getLocation()+planning.getBn_no()+"L.pdf");
				new File("C:/Resources/"+planning.getBn_no()+"L.pdf").delete();new File(keys.getRepository()+planning.getBn_no()+"L.pdf").delete();
			}
			if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
			{
				new File(planning.getLocation()+planning.getBn_no()+"R.pdf").renameTo(new File("C:/Resources/"+planning.getBn_no()+"R.pdf"));
				Files.write(Paths.get(keys.getRepository()+planning.getBn_no()+"R.pdf"),correspondence.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+planning.getBn_no()+"R.pdf",keys.getRepository()+planning.getBn_no()+"R.pdf",planning.getLocation()+planning.getBn_no()+"R.pdf");
				new File("C:/Resources/"+planning.getBn_no()+"R.pdf").delete();new File(keys.getRepository()+planning.getBn_no()+"R.pdf").delete();
			}
		}
		else {
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				if(!noteSheet.getOriginalFilename().equals(planning.getBpNo()+"L.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Notesheet name should be as BPNoL.pdf");
					return "redirect:/updateFile?department=Planning&sno="+planning.getSno();
				}
			}
			if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
			{
				if(!correspondence.getOriginalFilename().equals(planning.getBpNo()+"R.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Correspondence name should be as BPNoR.pdf");
					return "redirect:/updateFile?department=Planning&sno="+planning.getSno();
				}
			}
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				new File(planning.getLocation()+planning.getBpNo()+"L.pdf").renameTo(new File("C:/Resources/"+planning.getBpNo()+"L.pdf"));
				Files.write(Paths.get(keys.getRepository()+planning.getBpNo()+"L.pdf"),noteSheet.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+planning.getBpNo()+"L.pdf",keys.getRepository()+planning.getBpNo()+"L.pdf",planning.getLocation()+planning.getBpNo()+"L.pdf");
				new File("C:/Resources/"+planning.getBpNo()+"L.pdf").delete();new File(keys.getRepository()+planning.getBpNo()+"L.pdf").delete();
			}
			if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
			{
				new File(planning.getLocation()+planning.getBpNo()+"R.pdf").renameTo(new File("C:/Resources/"+planning.getBpNo()+"R.pdf"));
				Files.write(Paths.get(keys.getRepository()+planning.getBpNo()+"R.pdf"),correspondence.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+planning.getBpNo()+"R.pdf",keys.getRepository()+planning.getBpNo()+"R.pdf",planning.getLocation()+planning.getBpNo()+"R.pdf");
				new File("C:/Resources/"+planning.getBpNo()+"R.pdf").delete();new File(keys.getRepository()+planning.getBpNo()+"R.pdf").delete();
			}
		}
		genAgePlanService.insertOrUpdatePlan(planning);
		flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
		commonService.insertLogs(uId,"Updated File of Planning with Id:"+planning.getBpNo()+".");
		return "redirect:/updateFile?department=Planning&sno="+planning.getSno();
	}
}