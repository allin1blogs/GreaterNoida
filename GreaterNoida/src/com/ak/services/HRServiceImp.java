package com.ak.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ak.daos.HRDao;
import com.ak.modals.HR;


@Service("HRService")
@Transactional
public class HRServiceImp implements HRService
{
	@Autowired
	private HRDao hrd;

	
	@Override
	public HR retrieveHRRecords(int snos) {
		return hrd.retrieveHRRecords(snos);
	}

	@Override
	public ArrayList<HR> retrieveHRRecords(String[] snos) {
		return hrd.retrieveHRRecords(snos);
	}
   
	
	@Override
	public void insertOrUpdateHRRecord(HR hr)
	{
		hrd.insertOrUpdateHRRecord(hr);		
	}

	@Override
	public ArrayList<HR> retrieveHRRecords(ArrayList<String> params) {
		
		return hrd.retrieveHRRecords(params);
	}

	@Override
	public HR retrieveLawRecords(int snos) {
		return hrd.retrieveHRRecords(snos);
	}
	

	
	
	
	}		
