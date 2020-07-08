package com.ak.daos;

import java.util.ArrayList;

import com.ak.modals.Health;

public interface HealthDao {

	public boolean isOptFtsNoExists(String optFts);

	public void insertOrUpdateHealth(Health Health);

	public ArrayList<Health> retrieveHealth(ArrayList<String> params, String userId);

	public Health retrieveHealth(int sno);

	public ArrayList<Health> retrieveHealth(String[] snos);// 2

	public ArrayList<Health> retrieveHealthRecords(ArrayList<String> params);

	public Health getHealthRecord(int sno);

	public String getHealthLocation(String department, int sno);

	public String getLocation(int sno, String department);

	public ArrayList<Health> retrieveHealthRecords(String[] snos);

}
