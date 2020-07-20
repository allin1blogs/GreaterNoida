
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
import com.ak.modals.HR;
import com.ak.modals.Marketing;
import com.ak.services.CommonService;
import com.ak.services.HRService;
import com.ak.utils.FileUtils;
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
@Autowired
private Keys keys;


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
		public String updateHR(HttpServletRequest request, @ModelAttribute("HRForm")HR hr,@RequestParam("noteSheet")MultipartFile noteSheet,@RequestParam("correspondence")MultipartFile correspondence,RedirectAttributes flashAttributes)
				throws IOException {
			String uId=modelInitializer.getId(request);
			if(uId==null)
				return "error";
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				if(!noteSheet.getOriginalFilename().equals(hr.getFileCode()+"L.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Notesheet name should be as FileCodeL.pdf");
					return "redirect:/updateFile?department=HR&sno="+hr.getSno();
				}
			}
			if(correspondence!=null && correspondence.getOriginalFilename().length()>0)
			{
				if(!correspondence.getOriginalFilename().equals(hr.getFileCode()+"R.pdf"))
				{
					flashAttributes.addFlashAttribute("msg","Correspondence name should be as FileCodeR.pdf");
					return "redirect:/updateFile?department=HR&sno="+hr.getSno();
				}
			}
			if(noteSheet!=null && noteSheet.getOriginalFilename().trim().length()>0)
			{
				new File(hr.getLocation()+hr.getFileCode()+"L.pdf").renameTo(new File("C:/Resources/"+hr.getFileCode()+"L.pdf"));
				System.out.println(keys.getRepository()+hr.getFileCode()+"L.pdf");
				System.out.println(noteSheet.getBytes());
				Files.write(Paths.get(keys.getRepository()+hr.getFileCode()+"L.pdf"),noteSheet.getBytes());
				System.out.println("C:/Resources/"+hr.getFileCode()+"L.pdf");
				System.out.println(keys.getRepository()+hr.getFileCode()+"L.pdf");
				System.out.println(hr.getLocation()+hr.getFileCode()+"L.pdf");
				FileUtils.mergeFiles("C:/Resources/"+hr.getFileCode()+"L.pdf",keys.getRepository()+hr.getFileCode()+"L.pdf",hr.getLocation()+hr.getFileCode()+"L.pdf");
				new File("C:/Resources/"+hr.getFileCode()+"L.pdf").delete();new File(keys.getRepository()+hr.getFileCode()+"L.pdf").delete();
			}
			if(correspondence!=null && correspondence.getOriginalFilename().trim().length()>0)
			{
				new File(hr.getLocation()+hr.getFileCode()+"R.pdf").renameTo(new File("C:/Resources/"+hr.getFileCode()+"R.pdf"));
				Files.write(Paths.get(keys.getRepository()+hr.getFileCode()+"R.pdf"),correspondence.getBytes());
				FileUtils.mergeFiles("C:/Resources/"+hr.getFileCode()+"R.pdf",keys.getRepository()+hr.getFileCode()+"R.pdf",hr.getLocation()+hr.getFileCode()+"R.pdf");
				new File("C:/Resources/"+hr.getFileCode()+"R.pdf").delete();new File(keys.getRepository()+hr.getFileCode()+"R.pdf").delete();
			}
			hrs.insertOrUpdateHRRecord(hr);
			flashAttributes.addFlashAttribute("msg","File has been updated successfully.");
			commonService.insertLogs(uId,"Updated File of hr with Id:"+hr.getFileCode()+".");
			return "redirect:/updateFile?department=HR&sno="+hr.getSno();
		}

	
	
}
