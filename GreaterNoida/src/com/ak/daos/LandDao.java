package com.ak.daos;

import java.util.ArrayList;

import com.ak.modals.Land;
import com.ak.modals.Marketing;


public interface LandDao
{
	
	
	  public boolean isOptFtsNoExists(String optFts);
	  
	  public void insertOrUpdateLand(Land land);
	  
	  public ArrayList<Marketing> retrieveMarketing(ArrayList<String> params,
	  String userId);
	  
	  public Marketing retrieveMarketing(int sno);
	  
	  public ArrayList<Marketing> retrieveMarketing(String[] snos);//2
	  
	  public ArrayList<Land> retrieveLandRecords(ArrayList<String> params);
	 	
	public Land getLandRecord(int sno);
	
	public String getMarketingLocation(String department,int sno);
	
	public String getLocation(int sno,String department);
	
	public ArrayList<Land> retrieveLandRecords(String[] snos);

	
	
	
	
}
