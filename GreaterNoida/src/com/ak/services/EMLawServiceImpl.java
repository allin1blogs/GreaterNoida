package com.ak.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.daos.EMLawDao;

import com.ak.modals.EM;
import com.ak.modals.EM2;
import com.ak.modals.EM3;
import com.ak.modals.Law;
import com.ak.modals.Planning2;
import com.ak.modals.ProjectTech;
import com.ak.modals.Systems;
import com.ak.modals.UE;



@Service("emlService")
@Transactional
public class EMLawServiceImpl implements EMLawService
{
	@Autowired
	private EMLawDao emlDao;
	
	@Override
	public void insertOrUpdateEMRecord(EM em)
	{
		emlDao.insertOrUpdateEMRecord(em);		
	}
	
	@Override
	public void insertOrUpdateEM2Record(EM2 em2)
	{
		emlDao.insertOrUpdateEM2Record(em2);
		
	}
	
	@Override
	public void insertOrUpdateEM3Record(EM3 em)
	{
		emlDao.insertOrUpdateEM3Record(em);
		
	}
	
	@Override
	public void insertOrUpdateProjectTechRecord(ProjectTech pt) {
		  emlDao.insertOrUpdateProjectTechRecord(pt);
		
	}

	@Override
	public void insertOrUpdateUERecord(UE ue) {
		emlDao.insertOrUpdateUERecord(ue);
		
	}
	
	public void insertOrUpdatePlanning2Record(Planning2 planning2) {
		emlDao.insertOrUpdatePlanning2Record(planning2);
	}

	
	
	@Override
	public boolean isPetitionNoexists(String petitionNo)
	{
		return emlDao.isPetitionNoexists(petitionNo);
	}

	@Override
	public void insertOrUpdateLawRecord(Law law)
	{
		emlDao.insertOrUpdateLawRecord(law);		
	}

	@Override
	public ArrayList<EM> retrieveEMRecords(ArrayList<String> params)
	{
		return emlDao.retrieveEMRecords(params);
	}
	
	@Override
	public ArrayList<EM3> retrieveEM3Records(ArrayList<String> params)
	{
		return emlDao.retrieveEM3Records(params);
	}
    
	@Override
	public ArrayList<EM2> retrieveEM2Records(ArrayList<String> params) {
		
		return emlDao.retrieveEM2Records(params);
	}
	
	@Override
	public ArrayList<UE> retrieveUERecords(ArrayList<String> params) {
		
		return emlDao.retrieveUERecords(params);
	}

	
	@Override
	public String getLocation(int sno,String department)
	{
		return emlDao.getLocation(sno,department);
	}

	@Override
	public EM getEMRecord(int sno)
	{
		return emlDao.getEMRecord(sno);
	}
	
	@Override
	public EM3 getEM3Record(int sno)
	{
		return emlDao.getEM3Record(sno);
	}

	@Override
	public Law getLawRecord(int sno)
	{
		return emlDao.getLawRecord(sno);
	}

	@Override
	public ArrayList<EM> retrieveEMRecords(String[] snos)
	{
		return emlDao.retrieveEMRecords(snos);
	}

	@Override
	public ArrayList<Law> retrieveLawRecords(String[] snos) {
		return emlDao.retrieveLawRecords(snos);
	}
	@Override
	public Systems retrieveSystemRecords(int snos) {
		return emlDao.retrieveSystemRecords(snos);
	}
     
	@Override
	public ArrayList<Systems> retrieveSystemRecords(String[] snos) {
		return emlDao.retrieveSystemRecords(snos);
	}
	
	@Override
	public ArrayList<ProjectTech> retrieveProjectTechRecords(String[] snos) {
		return emlDao.retrieveProjectTechRecords(snos);
	}

	
	@Override
	public ArrayList<Law> retrieveLawRecords(ArrayList<String> params)
	{
		return emlDao.retrieveLawRecords(params);
	}

	

	@Override
	public Law retrieveLawRecords(int snos)
	{
		return emlDao.retrieveLawRecords(snos);
	}

	
	@Override
	public ProjectTech retrieveProjectTechRecords(int snos) {
		return emlDao.retrieveProjectTechRecords(snos);
	}
	
	@Override
	public ArrayList<ProjectTech> retrieveProjectTechRecords(ArrayList<String> params) {
		
		return emlDao.retrieveProjectTechRecords(params);
	}  
	 	
	 	
	 	@Override
	 	public void insertOrUpdateSystemRecord(Systems sy)
	 	{
	 		emlDao.insertOrUpdateSystemRecord(sy);		
	 	}

		@Override
		public ArrayList<Systems> retrieveSystemRecords(ArrayList<String> params) {
			return emlDao.retrieveSystemRecords(params);
		}

		@Override
		public EM2 retrieveEM2Records(int snos) {
			return emlDao.retrieveEM2Records(snos);
		}
		
		@Override
		public EM3 retrieveEM3Records(int snos) {
			return emlDao.retrieveEM3Records(snos);
		}


		@Override
		public ArrayList<EM2> retrieveEM2Records(String[] snos) {
			return emlDao.retrieveEM2Records(snos);
		}

		@Override
		public UE retrieveUERecords(int snos) {
			return emlDao.retrieveUERecords(snos);
		}

		@Override
		public ArrayList<UE> retrieveUERecords(String[] snos) {
			return emlDao.retrieveUERecords(snos);
		}

		@Override
		public Planning2 retrievePlanning2Records(int snos) {
			 return emlDao.retrievePlanning2Records(snos);
		}

		@Override
		public ArrayList<Planning2> retrievePlanning2Records(ArrayList<String> params) {
			 return emlDao.retrievePlanning2Records(params);
		}

		@Override
		public ArrayList<Planning2> retrievePlanning2Records(String[] snos) {
			 return emlDao.retrievePlanning2Records(snos);
		}

					
	
	}		
