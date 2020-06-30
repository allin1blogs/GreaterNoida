package com.ak.daos;

import java.util.ArrayList;

import com.ak.modals.Marketing;


public interface MarketingDao
{
	
	
	  public boolean isOptFtsNoExists(String optFts);
	  
	  public void insertOrUpdateMarketing(Marketing marketing);
	  
	  public ArrayList<Marketing> retrieveMarketing(ArrayList<String> params,
	  String userId);
	  
	  public Marketing retrieveMarketing(int sno);
	  
	  public ArrayList<Marketing> retrieveMarketing(String[] snos);//2
	  
	  public ArrayList<Marketing> retrieveMarketingRecords(ArrayList<String> params);
	 	
	public Marketing getMarketingRecord(int sno);
	
	public String getMarketingLocation(String department,int sno);
	
	public String getLocation(int sno,String department);
	
	public ArrayList<Marketing> retrieveMarketingRecords(String[] snos);

	
	
	
	
}
