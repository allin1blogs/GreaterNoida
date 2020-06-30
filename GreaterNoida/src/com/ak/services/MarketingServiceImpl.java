package com.ak.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.daos.MarketingDao;
import com.ak.modals.Marketing;

/*
 *	@Author
 *	Preeti Rani
*/

@Service("marketingService")
@Transactional
public class MarketingServiceImpl implements MarketingService {
	@Autowired
	private MarketingDao marketingDao;

	@Override
	public boolean isAllotmentNoExists(String optFts) {
		return marketingDao.isOptFtsNoExists(optFts);
	}

	@Override
	public void insertOrUpdateMarketing(Marketing marketing) {
		marketingDao.insertOrUpdateMarketing(marketing);
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
	public ArrayList<Marketing> retrieveMarketingRecords(ArrayList<String> params) {
		return marketingDao.retrieveMarketingRecords(params);
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
		return marketingDao.getLocation(sno,department);
	}

	@Override
	public ArrayList<Marketing> retrieveMarketingRecords(String[] snos) {
		return marketingDao.retrieveMarketingRecords(snos);
	}
	
	
}