package com.ak.services;

import java.util.ArrayList;
import java.util.Iterator;

import com.ak.modals.Agenda;
import com.ak.modals.General;
import com.ak.modals.Planning;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface GenAgePlanService
{
	public boolean isAllotmentNoExists(String allotmentNo);
	public void insertOrUpdateGen(General general);
	public ArrayList<General> retrieveGen(ArrayList<String> params,String userId);
	public General retrieveGen(int sno);
	public ArrayList<General> retrieveGen(String[] snos);
	public ArrayList<Agenda> retrieveAge(String description);
	public boolean isBpNoExists(String bpNo);
	public void insertOrUpdatePlan(Planning planning);
	public ArrayList<Planning> retrievePlanRecords(ArrayList<String> params);
	public Planning retrievePlanRecord(int sno);
	public ArrayList<Planning> retrievePlan(String[] snos);
	public String getLocation(String department,int sno);
}

/*
@Override
public Planning retrievePlanRecord(int sno)
{
	return genAgePlanDao.retrievePlanRecord(sno);
}

@Override
	public Planning retrievePlanRecord(int sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Planning planning where planning.sno="+sno+"").list().iterator();it.hasNext();)
			return (Planning)it.next();
		return null;
	}


*/