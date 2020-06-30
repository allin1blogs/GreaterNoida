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
import com.ak.modals.Marketing;
import com.ak.modals.Systems;
import com.ak.services.CommonService;
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

	@RequestMapping(value = "/updateMarketing", method = RequestMethod.POST)
	public String updateMarketing(HttpServletRequest request, @ModelAttribute("MarketingForm") Marketing marketing, RedirectAttributes flashAttributes)
			throws IOException {
		String uId = modelInitializer.getId(request);
		if (uId == null)
			return "error";
		
		
		
		marketingService.insertOrUpdateMarketing(marketing);
		flashAttributes.addFlashAttribute("msg", "File has been updated successfully.");
		commonService.insertLogs(uId,
				"Updated File of " + marketing.getDepartment() + " with Id:" + marketing.getFts_No_Opa_No() + ".");
		return "redirect:/updateFile?department=Marketing&sno=" + marketing.getSno();
	}

}