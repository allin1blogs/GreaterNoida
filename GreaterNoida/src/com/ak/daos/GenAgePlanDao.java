package com.ak.daos;

import java.util.ArrayList;

import com.ak.modals.Agenda;
import com.ak.modals.General;
import com.ak.modals.Planning;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface GenAgePlanDao
{
	public boolean isAllotmentNoExists(String allotmentNo);
	public boolean isAllotmentNo1Exists(String allotmentNo,String dep);
	public boolean isAllotmentNo2Exists(String allotmentNo,String dep);
	public boolean isAllotmentNo3Exists(String allotmentNo,String dep);
	public boolean isAllotmentNo4Exists(String allotmentNo,String dep);
	public void insertOrUpdateGen(General general);
	public ArrayList<General> retrieveGen(ArrayList<String> params,String userId);
	public General retrieveGen(int sno);
	public ArrayList<General> retrieveGen(String[] snos);
	public ArrayList<Agenda> retrieveAge(String description);
	public boolean isBpNoExists(String bpNo);
	public void insertOrUpdatePlan(Planning planning);
	public ArrayList<Planning> retrievePlanRecords(ArrayList<String> params);
	public Planning retrievePlanRecord(int sno);
	public ArrayList<Planning> retrievePlanRecord(String[] snos);
	public String getLocation(String department,int sno);
}
