package com.ak.services;

import java.util.ArrayList;

import com.ak.modals.EM;
import com.ak.modals.EM2;
import com.ak.modals.EM3;
import com.ak.modals.Law;
import com.ak.modals.Planning2;
import com.ak.modals.ProjectTech;
import com.ak.modals.Systems;
import com.ak.modals.UE;



public interface EMLawService
{
	public void insertOrUpdateEMRecord(EM em);
	public void insertOrUpdateEM2Record(EM2 em2);
	public void insertOrUpdateEM3Record(EM3 em);
	
	public boolean isEMExists(String petitionNo);
	public boolean isEHUExists(String petitionNo);
	public boolean isPetitionNoexists(String petitionNo);
	public boolean isSysexists(String petitionNo);
	public boolean isUEExists(String petitionNo);
	public boolean isEM2Exists(String petitionNo);
	public boolean isPTExists(String petitionNo);
	
	public void insertOrUpdateLawRecord(Law law);
	
	public void insertOrUpdateProjectTechRecord(ProjectTech pt);//1
	
	public void insertOrUpdatePlanning2Record(Planning2 planning2);
	public void insertOrUpdateUERecord(UE ue);
	
	public ArrayList<EM> retrieveEMRecords(ArrayList<String> params);	
	public ArrayList<EM3> retrieveEM3Records(ArrayList<String> params);	
	
	public String getLocation(int sno,String department);//Location
	
	
	public EM getEMRecord(int sno);
	public EM2 getEM2Record(int sno);
	public EM3 getEM3Record(int sno);
	public Law getLawRecord(int sno);
	public Systems getSystemsRecord(int sno);
	public UE getUERecord(int sno);
	
	public ArrayList<EM> retrieveEMRecords(String[] snos);
	
	public Law retrieveLawRecords(int snos);
	public EM retrieveEMRecords(int snos);
    public ArrayList<Law> retrieveLawRecords(ArrayList<String> params);
    public ArrayList<Law> retrieveLawRecords(String[] snos);
    
    public Planning2 retrievePlanning2Records(int snos);//1	
    public ArrayList<Planning2> retrievePlanning2Records(ArrayList<String> params);//2
    public ArrayList<Planning2> retrievePlanning2Records(String[] snos);//2
    
    
    public UE retrieveUERecords(int snos);	//1
    public ArrayList<UE> retrieveUERecords(ArrayList<String> params);//2
    public ArrayList<UE> retrieveUERecords(String[] snos);//3
    
    
    public EM2 retrieveEM2Records(int snos);//1
    public EM3 retrieveEM3Records(int snos);//1
    public ArrayList<EM2> retrieveEM2Records(ArrayList<String> params);	//2
    public ArrayList<EM2> retrieveEM2Records(String[] snos);//3
    
    
    public ArrayList<ProjectTech> retrieveProjectTechRecords(String[] snos);//1
    public ProjectTech retrieveProjectTechRecords(int snos);//2
    public ArrayList<ProjectTech> retrieveProjectTechRecords(ArrayList<String> params);//3
    
       public Systems retrieveSystemRecords(int snos);
       public ArrayList<Systems> retrieveSystemRecords(String[] snos);
       public ArrayList<EM3> retrieveEM3Records(String[] snos);
       public ArrayList<Systems> retrieveSystemRecords(ArrayList<String> params);
       public void insertOrUpdateSystemRecord(Systems sy);	
       //public void insertOrUpdateUERecord(UE ue);	
        
      
    
}
