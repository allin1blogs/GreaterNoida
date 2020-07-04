
package com.ak.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ak.modals.HR;
import com.ak.modals.Marketing;
import com.ak.services.CommonService;
import com.ak.services.HRService;
import com.ak.utils.Utils;

/*
 *	@Author
 *  Updated By:Preeti Rani
 *  Started 20-Jun-2020 to till
 *  
*/

@Controller
public class HRController {
@Autowired
private ModelInitializer modelInitializer;
@Autowired
private Utils utils;
@Autowired
private HRService hrs;
@Autowired
private CommonService commonService;


@RequestMapping(value="/retrieveHR",method=RequestMethod.GET)
 public String retrieveHR(ModelMap model,HttpServletRequest req,@ModelAttribute("HRForm")HR hr)	{
		String uId=modelInitializer.getId(req);
		if(uId==null)
			return "error";
		model=modelInitializer.initializeModel(model,req);
		System.out.println("HR Department");
		
		ArrayList<String> params=utils.generateHRParams(hr);
		
		ArrayList<HR> records=hrs.retrieveHRRecords(params);
		if(records.isEmpty())
			model.addAttribute("msg","Sorry No Record was Found!");
		else
		{
			req.getSession(false).setAttribute("params",params);
			model.addAttribute("records",records);
			modelInitializer.getRights(model,req);
		}
		return "departments/HR/retrieve";
	}

		@RequestMapping(value = "/updateHR", method = RequestMethod.POST)
		public String updateHR(HttpServletRequest request, @ModelAttribute("HRForm")HR hr, RedirectAttributes flashAttributes)
				throws IOException {
			String uId = modelInitializer.getId(request);
			if (uId == null)
				return "error";
			hrs.insertOrUpdateHRRecord(hr);
			flashAttributes.addFlashAttribute("msg", "File has been updated successfully.");
			commonService.insertLogs(uId,
					"Updated File of " + hr.getSno() + " with Id:" + hr.getFileCode() + ".");
			return "redirect:/updateFile?department=Hr&sno=" + hr.getSno();
		}

	
	
}
