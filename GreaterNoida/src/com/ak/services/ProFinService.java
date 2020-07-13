package com.ak.services;

import java.util.ArrayList;

import com.ak.modals.Finance;
import com.ak.modals.Project;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface ProFinService
{
	public boolean isFinExists(String statement, String param2);
	public boolean isProExists(String statement);
	public void insertOrUpdateFin(Finance finance);
	public ArrayList<Finance> retrieveFin(ArrayList<String> params);
	public Finance retrieveFin(int sno);
	public ArrayList<Finance> retrieveFin(String[] snos);
	public void insertOrUpdatePro(Project project);
	public ArrayList<Project> retrievePro(ArrayList<String> params,String userId);
	public Project getPro(int sno);
	public ArrayList<Project> retrievePro(String[] snos);
	public String getProFinLocation(String department,int sno);
}
