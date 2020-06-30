

import java.util.ArrayList;


import com.ak.modals.HR;

/*
public interface EMLawService
{
	public void insertOrUpdateEMRecord(EM em);
	public boolean isPetitionNoexists(String petitionNo);
	public void insertOrUpdateLawRecord(Law law);
	public ArrayList<EM> retrieveEMRecords(ArrayList<String> params);
	public ArrayList<Law> retrieveLawRecords(ArrayList<String> params);
	public String getLocation(int sno,String department);
	public EM getEMRecord(int sno);
	public Law getLawRecord(int sno);
	public ArrayList<EM> retrieveEMRecords(String[] snos);
	public ArrayList<Law> retrieveLawRecords(String[] snos);
}
*/


public interface HRService {
	
	public ArrayList<HR> retrieveHRRecords(ArrayList<String> params);
	//public ArrayList<HR> retrieveHRRecords(String[] snos);

}
