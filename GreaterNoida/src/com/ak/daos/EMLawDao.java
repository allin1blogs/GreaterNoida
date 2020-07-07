package com.ak.daos;

import java.util.ArrayList;

import com.ak.modals.EM;
import com.ak.modals.EM2;
import com.ak.modals.EM3;
import com.ak.modals.Law;
import com.ak.modals.Planning2;
import com.ak.modals.ProjectTech;
import com.ak.modals.Systems;
import com.ak.modals.UE;



public interface EMLawDao
{
	
   
	
	public void insertOrUpdateSystemRecord(Systems sy);
	public void insertOrUpdateProjectTechRecord(ProjectTech pt);//1
	public Systems getSystemRecord(int sno);
	
	
	public void insertOrUpdateEMRecord(EM em);	
	
	
	public ArrayList<EM> retrieveEMRecords(ArrayList<String> params);
	public ArrayList<EM3> retrieveEM3Records(ArrayList<String> params);
	
	public EM2 retrieveEM2Records(int snos);//1
	public EM3 retrieveEM3Records(int snos);//1
	public ArrayList<EM2> retrieveEM2Records(String[] snos);//2	
	
	
	public ArrayList<EM2> retrieveEM2Records(ArrayList<String> params);
	public void insertOrUpdateEM2Record(EM2 em2);
	public void insertOrUpdateEM3Record(EM3 em);

	
	public ArrayList<ProjectTech> retrieveProjectTechRecords(ArrayList<String> params);//1
	public ArrayList<ProjectTech> retrieveProjectTechRecords(String[] snos);//2
	public ProjectTech retrieveProjectTechRecords(int snos);//3
	
	public boolean isPetitionNoexists(String petitionNo);
	
	public void insertOrUpdateLawRecord(Law law);
	public void insertOrUpdateUERecord(UE ue);
	public void insertOrUpdatePlanning2Record(Planning2 planning2);
	
	
	
	public ArrayList<Systems> retrieveSystemRecords(ArrayList<String> params);
	
	public String getLocation(int sno,String department);
	public EM getEMRecord(int sno);
	public EM3 getEM3Record(int sno);
	public Law getLawRecord(int sno);
	public Systems getSystemsRecord(int sno);
	public ArrayList<EM> retrieveEMRecords(String[] snos);
	
	public Law retrieveLawRecords(int snos);//1
	public ArrayList<Law> retrieveLawRecords(String[] snos);//2	
	public ArrayList<Law> retrieveLawRecords(ArrayList<String> params);//3
	public Systems retrieveSystemRecords(int snos);
	
	public ArrayList<Systems> retrieveSystemRecords(String[] snos);	
	public ArrayList<EM3> retrieveEM3Records(String[] snos);	
    
	
	public UE retrieveUERecords(int snos);	//1
    public ArrayList<UE> retrieveUERecords(ArrayList<String> params);//2
    public ArrayList<UE> retrieveUERecords(String[] snos);//3
    
    
    
    public Planning2 retrievePlanning2Records(int snos);//1	
    public ArrayList<Planning2> retrievePlanning2Records(ArrayList<String> params);//2
    public ArrayList<Planning2> retrievePlanning2Records(String[] snos);//2
	
}
