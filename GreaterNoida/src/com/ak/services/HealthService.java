package com.ak.services;

import java.util.ArrayList;

import com.ak.modals.Health;

/*
 *	@Author
 *	Preeti Rani
*/

public interface HealthService {
	public boolean isAllotmentNoExists(String optFts);

	public void insertOrUpdateHealth(Health Health);

	public ArrayList<Health> retrieveHealth(ArrayList<String> params, String userId);

	public Health retrieveHealth(int sno);

	public ArrayList<Health> retrieveHealth(String[] snos);
	
    public ArrayList<Health> retrieveHealthRecords(ArrayList<String> params);
    
    public Health getHealthRecord(int sno);
    
    public String getHealthLocation(String department,int sno);
    
    public String getLocation(int sno,String department);//Location
	
   public ArrayList<Health> retrieveHealthRecords(String[] snos);
}
