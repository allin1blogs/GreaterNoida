package com.ak.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.daos.LandDao;
import com.ak.daos.MarketingDao;
import com.ak.modals.Land;
import com.ak.modals.Marketing;

/*
 *	@Author
 *	Preeti Rani
*/

@Service("landService")
@Transactional
public class LandServiceImpl implements LandService {
	@Autowired
	private MarketingDao marketingDao;
	
	@Autowired
	private LandDao landDao;

	@Override
	public boolean isAllotmentNoExists(String optFts) {
		return marketingDao.isOptFtsNoExists(optFts);
	}

	@Override
	public void insertOrUpdateLand(Land land) {
		landDao.insertOrUpdateLand(land);
	}

	@Override
	public ArrayList<Marketing> retrieveMarketing(ArrayList<String> params, String userId) {
		return marketingDao.retrieveMarketing(params, userId);
	}

	@Override
	public Marketing retrieveMarketing(int sno) {
		return marketingDao.retrieveMarketing(sno);
	}

	@Override
	public ArrayList<Marketing> retrieveMarketing(String[] snos) {
		return marketingDao.retrieveMarketing(snos);
	}

	@Override
	public ArrayList<Land> retrieveLandRecords(ArrayList<String> params) {
		return landDao.retrieveLandRecords(params);
	}
	
	@Override
	public Marketing getMarketingRecord(int sno)
	{
		return marketingDao.getMarketingRecord(sno);
	}


	@Override
	public String getMarketingLocation(String department,int sno)
	{
		return marketingDao.getMarketingLocation(department,sno);
	}	
	
	@Override
	public String getLocation(int sno,String department)
	{
		return landDao.getLocation(sno,department);
	}

	@Override
	public ArrayList<Marketing> retrieveMarketingRecords(String[] snos) {
		return marketingDao.retrieveMarketingRecords(snos);
	}
	
	
}