package com.ak.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.daos.HealthDao;
import com.ak.modals.Health;

/*
 *	@Author
 *	Preeti Rani
*/

@Service("HealthService")
@Transactional
public class HealthServiceImpl implements HealthService {
	@Autowired
	private HealthDao HealthDao;

	@Override
	public boolean isAllotmentNoExists(String optFts) {
		return HealthDao.isOptFtsNoExists(optFts);
	}

	@Override
	public void insertOrUpdateHealth(Health Health) {
		HealthDao.insertOrUpdateHealth(Health);
	}

	@Override
	public ArrayList<Health> retrieveHealth(ArrayList<String> params, String userId) {
		return HealthDao.retrieveHealth(params, userId);
	}

	@Override
	public Health retrieveHealth(int sno) {
		return HealthDao.retrieveHealth(sno);
	}

	@Override
	public ArrayList<Health> retrieveHealth(String[] snos) {
		return HealthDao.retrieveHealth(snos);
	}

	@Override
	public ArrayList<Health> retrieveHealthRecords(ArrayList<String> params) {
		return HealthDao.retrieveHealthRecords(params);
	}
	
	@Override
	public Health getHealthRecord(int sno)
	{
		return HealthDao.getHealthRecord(sno);
	}


	@Override
	public String getHealthLocation(String department,int sno)
	{
		return HealthDao.getHealthLocation(department,sno);
	}	
	
	@Override
	public String getLocation(int sno,String department)
	{
		return HealthDao.getLocation(sno,department);
	}

	@Override
	public ArrayList<Health> retrieveHealthRecords(String[] snos) {
		return HealthDao.retrieveHealthRecords(snos);
	}
	
	
}