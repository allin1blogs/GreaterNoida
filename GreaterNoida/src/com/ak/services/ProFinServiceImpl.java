package com.ak.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.daos.ProFinDao;
import com.ak.modals.Finance;
import com.ak.modals.Project;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Service("proFinService")
@Transactional
public class ProFinServiceImpl implements ProFinService
{
	@Autowired
	private ProFinDao proFinDao;

	@Override
	public boolean isFinExists(String statement,String param2)
	{
		return proFinDao.isFinExists(statement,param2);
	}
	
	@Override
	public boolean isProExists(String statement)
	{
		return proFinDao.isProExists(statement);
	}

	@Override
	public void insertOrUpdateFin(Finance finance)
	{
		proFinDao.insertOrUpdateFin(finance);		
	}

	@Override
	public ArrayList<Finance> retrieveFin(ArrayList<String> params)
	{
		return proFinDao.retrieveFin(params);
	}
	
	@Override
	public ArrayList<Finance> retrieveFin(ArrayList<String> params,String userId)
	{
		return proFinDao.retrieveFin(params,userId);
	}

	@Override
	public Finance retrieveFin(int sno)
	{
		return proFinDao.retrieveFin(sno);
	}
	
	@Override
	public ArrayList<Finance> retrieveFin(String[] snos)
	{
		return proFinDao.retrieveFin(snos);
	}

	@Override
	public void insertOrUpdatePro(Project project)
	{
		proFinDao.insertOrUpdatePro(project);
	}

	@Override
	public ArrayList<Project> retrievePro(ArrayList<String> params)
	{
		return proFinDao.retrievePro(params);
	}

	@Override
	public Project getPro(int sno)
	{
		return proFinDao.retrievePro(sno);
	}
	
	@Override
	public ArrayList<Project> retrievePro(String[] snos)
	{
		return proFinDao.retrievePro(snos);
	}

	@Override
	public String getProFinLocation(String department,int sno)
	{
		return proFinDao.getProFinLocation(department,sno);
	}	
}