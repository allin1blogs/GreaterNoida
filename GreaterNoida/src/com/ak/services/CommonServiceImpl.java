package com.ak.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.daos.CommonDao;
import com.ak.modals.Logs;
import com.ak.modals.UserDepartments;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService
{
	@Autowired
	private CommonDao commonDao;

	@Override
	public ArrayList<String> getAllDepartments()
	{
		return commonDao.getAllDepartments();
	}

	@Override
	public ArrayList<UserDepartments> getAllDepartments(String userId)
	{
		return commonDao.getAllDepartments(userId);
	}
	
	@Override
	public ArrayList<String> getAllSectors(String department)
	{
		return commonDao.getAllSectors(department);
	}
	
	@Override
	public ArrayList<String> getAllSectors(String department,String userId)
	{
		return commonDao.getAllSectors(department,userId);
	}

	@Override
	public ArrayList<String> getAllUsers(String uId)
	{
		return commonDao.getAllUsers(uId);
	}

	@Override
	public ArrayList<String> getAllUsers(String department,String uId)
	{
		return commonDao.getAllUsers(department,uId);
	}

	@Override
	public String getHelp(String obj,String key,String value)
	{
		return commonDao.getHelp(obj,key,value);
	}

	@Override
	public void insertLogs(String userId,String activity)
	{
		commonDao.insertLogs(userId,activity);		
	}

	@Override
	public ArrayList<Logs> retrieveLogs(String userId)
	{
		return commonDao.retrieveLogs(userId);
	}	
}