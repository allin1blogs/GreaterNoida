package com.ak.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.daos.GenAgePlanDao;
import com.ak.modals.Agenda;
import com.ak.modals.General;
import com.ak.modals.Planning;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Service("genAgePlanService")
@Transactional
public class GenAgePlanServiceImpl implements GenAgePlanService
{
	@Autowired
	private GenAgePlanDao genAgePlanDao;

	@Override
	public boolean isAllotmentNoExists(String allotmentNo)
	{
		return genAgePlanDao.isAllotmentNoExists(allotmentNo);
	}
	
	@Override
	public boolean isAllotmentNo1Exists(String allotmentNo)
	{
		return genAgePlanDao.isAllotmentNo1Exists(allotmentNo);
	}
	
	@Override
	public boolean isAllotmentNo2Exists(String allotmentNo)
	{
		return genAgePlanDao.isAllotmentNo2Exists(allotmentNo);
	}
	
	@Override
	public boolean isAllotmentNo3Exists(String allotmentNo)
	{
		return genAgePlanDao.isAllotmentNo3Exists(allotmentNo);
	}
	
	@Override
	public boolean isAllotmentNo4Exists(String allotmentNo)
	{
		return genAgePlanDao.isAllotmentNo4Exists(allotmentNo);
	}

	@Override
	public void insertOrUpdateGen(General general)
	{
		genAgePlanDao.insertOrUpdateGen(general);		
	}

	@Override
	public ArrayList<General> retrieveGen(ArrayList<String> params,String userId)
	{
		return genAgePlanDao.retrieveGen(params,userId);
	}

	@Override
	public General retrieveGen(int sno)
	{
		return genAgePlanDao.retrieveGen(sno);
	}
	
	@Override 
	public ArrayList<General>retrieveGen(String[] snos)
	{
		return genAgePlanDao.retrieveGen(snos);
	}

	@Override
	public ArrayList<Agenda> retrieveAge(String description)
	{
		return genAgePlanDao.retrieveAge(description);
	}
	
	@Override
	public String getLocation(String department,int sno)
	{
		return genAgePlanDao.getLocation(department,sno);
	}

	@Override
	public boolean isBpNoExists(String bpNo)
	{
		return genAgePlanDao.isBpNoExists(bpNo);
	}

	@Override
	public void insertOrUpdatePlan(Planning planning)
	{
		genAgePlanDao.insertOrUpdatePlan(planning);		
	}

	@Override
	public ArrayList<Planning> retrievePlanRecords(ArrayList<String> params)
	{
		return genAgePlanDao.retrievePlanRecords(params);
	}

	@Override
	public Planning retrievePlanRecord(int sno)
	{
		return genAgePlanDao.retrievePlanRecord(sno);
	}
	
	@Override
	public ArrayList<Planning> retrievePlan(String[] snos)
	{
		return genAgePlanDao.retrievePlanRecord(snos);
	}
}