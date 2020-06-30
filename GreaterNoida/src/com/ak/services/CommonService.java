package com.ak.services;

import java.util.ArrayList;

import com.ak.modals.Logs;
import com.ak.modals.UserDepartments;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface CommonService
{
	public ArrayList<String> getAllDepartments();
	public ArrayList<UserDepartments> getAllDepartments(String userId);
	
	public ArrayList<String> getAllSectors(String department);
	
	public ArrayList<String> getAllSectors(String department,String userId);
	
	public ArrayList<String> getAllUsers(String uId);
	public ArrayList<String> getAllUsers(String department,String uId);
	public String getHelp(String obj,String key,String value);
	public void insertLogs(String userId,String activity);
	public ArrayList<Logs> retrieveLogs(String userId);
}
