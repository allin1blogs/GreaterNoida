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
import com.ak.modals.EM;
import com.ak.modals.General;
import com.ak.modals.Health;
import com.ak.modals.Marketing;
import com.ak.modals.Systems;
import com.ak.services.CommonService;
import com.ak.services.HealthService;
import com.ak.services.MarketingService;
import com.ak.utils.FileUtils;
import com.ak.utils.Utils;

/*
 *	@Author 
 * 	Preeti Rani
*/

@Controller
public class MarketingController {
	@Autowired
	private ModelInitializer modelInitializer;
	@Autowired
	private Utils utils;
	@Autowired
	private MarketingService marketingService;
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private HealthService healthService;
	
	@Autowired
	private Keys keys;

	@RequestMapping(value = "/retrieveMarketing", method = RequestMethod.GET)
	public String retrieveMarketing(ModelMap model, HttpServletRequest request,
			@ModelAttribute("MarketingForm")Marketing marketing) {
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,request);
		model.addAttribute("department",marketing.getDepartment());
		if(modelInitializer.getIdModule(request).equals("Super Administrator") || modelInitializer.getIdModule(request).equals("Administrator"))
			model.addAttribute("sectors",commonService.getAllSectors(marketing.getDepartment()));
		else
			model.addAttribute("sectors",commonService.getAllSectors(marketing.getDepartment(),uId));
		ArrayList<String> params=utils.generateMarketingParams(marketing);
		ArrayList<Marketing> records=marketingService.retrieveMarketingRecords(params);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found");
		else
		{
			request.getSession(false).setAttribute("params",params);
			model.addAttribute("records",records);
			model=modelInitializer.getRights(model,request);
		}
		return "departments/Marketing/retrieve";
		}
		@RequestMapping(value = "/retrieveHealth", method = RequestMethod.GET)
	public String retrieveHealth(ModelMap model, HttpServletRequest request,
			@ModelAttribute("HealthForm") Health health) {
		String uId = modelInitializer.getId(request);
		if (uId == null)
			return "error";
		model = modelInitializer.initializeModel(model, request);
		model.addAttribute("department", health.getDepartment());
		if (modelInitializer.getIdModule(request).equals("Super Administrator")
				|| modelInitializer.getIdModule(request).equals("Administrator"))
			model.addAttribute("sectors", commonService.getAllSectors(health.getDepartment()));
		else
			model.addAttribute("sectors", commonService.getAllSectors(health.getDepartment(), uId));
		ArrayList<String> params = utils.generateHealthParams(health);
		ArrayList<Health> records = healthService.retrieveHealthRecords(params);
		if (records.isEmpty())
			model.addAttribute("msg", "No Record Found");
		else {
			request.getSession(false).setAttribute("params", params);
			model.addAttribute("records", records);
			model = modelInitializer.getRights(model, request);
		}
		return "departments/Health/retrieve";
	}

		@RequestMapping(value="/updateMarketing",method=RequestMethod.POST)
		public String updateMarketing(HttpServletRequest request,@ModelAttribute("marketingForm")Marketing marketing,@RequestParam("noteSheet")MultipartFile noteSheet,@RequestParam("correspondence")MultipartFile correspondence,RedirectAttributes flashAttributes)throws IOException
		{
			String uId=modelInitializer.getId(request);
			if(uId==null)
				return "error";
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				if(!noteSheet.getOriginalFilename().equals(marketing.getFts_No_Opa_No()+"L.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Notesheet name should be as OPA/FTSNoL.pdf");
					return "redirect:/updateFile?department=marketing&sno="+marketing.getSno();
				}
			}
			if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
			{
				if(!correspondence.getOriginalFilename().equals(marketing.getFts_No_Opa_No()+"R.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Correspondence name should be as OPA/FTSNoR.pdf");
					return "redirect:/updateFile?department=marketing&sno="+marketing.getSno();
				}
			}
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				new File(marketing.getLocation()+marketing.getFts_No_Opa_No()+"L.pdf").renameTo(new File("C:/Resources/"+marketing.getFts_No_Opa_No()+"L.pdf"));
				Files.write(Paths.get(keys.getRepository()+marketing.getFts_No_Opa_No()+"L.pdf"),noteSheet.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+marketing.getFts_No_Opa_No()+"L.pdf",keys.getRepository()+marketing.getFts_No_Opa_No()+"L.pdf",marketing.getLocation()+marketing.getFts_No_Opa_No()+"L.pdf");
				new File("C:/Resources/"+marketing.getFts_No_Opa_No()+"L.pdf").delete();new File(keys.getRepository()+marketing.getFts_No_Opa_No()+"L.pdf").delete();
			}
			if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
			{
				new File(marketing.getLocation()+marketing.getFts_No_Opa_No()+"R.pdf").renameTo(new File("C:/Resources/"+marketing.getFts_No_Opa_No()+"R.pdf"));
				Files.write(Paths.get(keys.getRepository()+marketing.getFts_No_Opa_No()+"R.pdf"),correspondence.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+marketing.getFts_No_Opa_No()+"R.pdf",keys.getRepository()+marketing.getFts_No_Opa_No()+"R.pdf",marketing.getLocation()+marketing.getFts_No_Opa_No()+"R.pdf");
				new File("C:/Resources/"+marketing.getFts_No_Opa_No()+"R.pdf").delete();new File(keys.getRepository()+marketing.getFts_No_Opa_No()+"R.pdf").delete();
			}
			marketingService.insertOrUpdateMarketing(marketing);
			flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
			commonService.insertLogs(uId,"Updated File of marketing with Id:"+marketing.getFts_No_Opa_No()+".");
			return "redirect:/updateFile?department=Marketing&sno="+marketing.getSno();
		}
	
	
	@RequestMapping(value="/updateHealth",method=RequestMethod.POST)
	public String updateHealth(HttpServletRequest request,@ModelAttribute("HealthForm")Health health,@RequestParam("noteSheet")MultipartFile noteSheet,@RequestParam("correspondence")MultipartFile correspondence,RedirectAttributes flashAttributes)throws IOException
	{
		String uId=modelInitializer.getId(request);
		if(uId==null)
			return "error";
		if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
		{
			if(!noteSheet.getOriginalFilename().equals(health.getOpa_fts()+"L.pdf"))
			{
				flashAttributes.addFlashAttribute("msg","Notesheet name should be as OPA/FTSNoL.pdf");
				return "redirect:/updateFile?department=health&sno="+health.getSno();
			}
		}
		if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
		{
			if(!correspondence.getOriginalFilename().equals(health.getOpa_fts()+"R.pdf"))
			{
				flashAttributes.addFlashAttribute("msg","Correspondence name should be as OPA/FTSNoR.pdf");
				return "redirect:/updateFile?department=health&sno="+health.getSno();
			}
		}
		if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
		{
			new File(health.getLocation()+health.getOpa_fts()+"L.pdf").renameTo(new File("C:/Resources/"+health.getOpa_fts()+"L.pdf"));
			Files.write(Paths.get(keys.getRepository()+health.getOpa_fts()+"L.pdf"),noteSheet.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+health.getOpa_fts()+"L.pdf",keys.getRepository()+health.getOpa_fts()+"L.pdf",health.getLocation()+health.getOpa_fts()+"L.pdf");
			new File("C:/Resources/"+health.getOpa_fts()+"L.pdf").delete();new File(keys.getRepository()+health.getOpa_fts()+"L.pdf").delete();
		}
		if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
		{
			new File(health.getLocation()+health.getOpa_fts()+"R.pdf").renameTo(new File("C:/Resources/"+health.getOpa_fts()+"R.pdf"));
			Files.write(Paths.get(keys.getRepository()+health.getOpa_fts()+"R.pdf"),correspondence.getBytes());
			FileUtils.mergeFiles("C:/Resources/"+health.getOpa_fts()+"R.pdf",keys.getRepository()+health.getOpa_fts()+"R.pdf",health.getLocation()+health.getOpa_fts()+"R.pdf");
			new File("C:/Resources/"+health.getOpa_fts()+"R.pdf").delete();new File(keys.getRepository()+health.getOpa_fts()+"R.pdf").delete();
		}
		healthService.insertOrUpdateHealth(health);
		flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
		commonService.insertLogs(uId,"Updated File of health with Id:"+health.getOpa_fts()+".");
		return "redirect:/updateFile?department=Health&sno="+health.getSno();
	}
}