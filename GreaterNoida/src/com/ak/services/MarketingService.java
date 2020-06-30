package com.ak.services;

import java.util.ArrayList;

import com.ak.modals.Marketing;

/*
 *	@Author
 *	Preeti Rani
*/

public interface MarketingService {
	public boolean isAllotmentNoExists(String optFts);

	public void insertOrUpdateMarketing(Marketing marketing);

	public ArrayList<Marketing> retrieveMarketing(ArrayList<String> params, String userId);

	public Marketing retrieveMarketing(int sno);

	public ArrayList<Marketing> retrieveMarketing(String[] snos);
	
    public ArrayList<Marketing> retrieveMarketingRecords(ArrayList<String> params);
    
    public Marketing getMarketingRecord(int sno);
    
    public String getMarketingLocation(String department,int sno);
    
    public String getLocation(int sno,String department);//Location
	
   public ArrayList<Marketing> retrieveMarketingRecords(String[] snos);
}
