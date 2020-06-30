
package com.ak.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ak.modals.HR;
import com.ak.services.HRService;
import com.ak.utils.Utils;

@Controller
public class HRController {
@Autowired
private ModelInitializer modelInitializer;
@Autowired
private Utils utils;
@Autowired
private HRService hrs;


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
	
	
}
