package com.ak.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ak.modals.Agenda;
import com.ak.modals.General;
import com.ak.modals.Planning;

import java.util.Iterator;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Repository("genAgePlanDao")
public class GenAgeDaoPlanImpl implements GenAgePlanDao
{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Override
	public boolean isAllotmentNoExists(String allotmentNo,String dep)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select allotmentNo from General where allotmentNo='"+allotmentNo+"' && department='"+dep+"'").list().isEmpty())
			return true;
		return false;
	}
	
	@Override
	public boolean isAllotmentNo1Exists(String allotmentNo,String dep)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select allotmentNo from Planning where allotmentNo='"+allotmentNo+"' && SubDepartment='"+dep+"'").list().isEmpty())
			return true;
		return false;
	}
	
	@Override
	public boolean isAllotmentNo2Exists(String allotmentNo,String dep)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select bpNo from Planning where bpNo='"+allotmentNo+"' && SubDepartment='"+dep+"'").list().isEmpty())
			return true;
		return false;
	}
	
	@Override
	public boolean isAllotmentNo3Exists(String allotmentNo,String dep)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select bpNo from Planning where bpNo='"+allotmentNo+"' && SubDepartment='"+dep+"'").list().isEmpty())
			return true;
		return false;
	}
	
	@Override
	public boolean isAllotmentNo4Exists(String allotmentNo,String dep)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select bn_No from Planning where bn_No='"+allotmentNo+"' && SubDepartment='"+dep+"'").list().isEmpty())
			return true;
		return false;
	}

	@Override
	public void insertOrUpdateGen(General general)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(general);
	}

	@Override
	public ArrayList<General> retrieveGen(ArrayList<String> params,String userId)
	{
		ArrayList<General> records=new ArrayList<General>();
		boolean sectorFlage=false;
		String q="From General general where general.department='"+params.get(0).substring(0,params.get(0).indexOf("@"))+"'";
		for(int i=1;i<params.size();i++)
		{
			
			if(params.get(i).substring(params.get(i).indexOf("@")+1).equals("scheme"))
			{
				sectorFlage=true;
				q=q+" and general.scheme='"+params.get(i).substring(0,params.get(i).indexOf("@"))+"'";
			}
			else
				q=q+"and (general."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '"+params.get(i).substring(0,params.get(i).indexOf("@"))+"%' or general."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '%"+params.get(i).substring(0,params.get(i).indexOf("@"))+"%' or general."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '%"+params.get(i).substring(0,params.get(i).indexOf("@"))+"')";
		}
		session=sessionFactory.getCurrentSession();
		if(!sectorFlage)
		{
			ArrayList<String> sectors=new ArrayList<String>();
			List list=session.createSQLQuery("Select sector from UserDepartments where userId='"+userId+"' and department='"+params.get(0).substring(0,params.get(0).indexOf("@"))+"'").list();
			for(Iterator it=list.iterator();it.hasNext();)
				sectors.add((String)it.next());
			for(int i=0;i<sectors.size();i++)
			{
				if(sectors.get(i)!=null && !sectors.get(i).equals("null"))
				{
					if(i==0)
						q=q+" and (general.scheme='"+sectors.get(i)+"'";
					else
						q=q+" or general.scheme='"+sectors.get(i)+"'";
				}
			}
			q=q+")";
		}
		List list=session.createQuery(q).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((General)it.next());
		return records;
	}

	@Override
	public General retrieveGen(int sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From General general where general.sno="+sno+"").list().iterator();it.hasNext();)
			return (General)it.next();
		return null;
	}
	
	@Override
	public ArrayList<General> retrieveGen(String[] snos)
	{
		ArrayList<General> records=new ArrayList<General>();
		session=sessionFactory.getCurrentSession();
		for(String sno: snos)
		{
			for(Iterator it=session.createQuery("From General general where general.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((General)it.next());
		}
		return records;
	}

	@Override
	public ArrayList<Agenda> retrieveAge(String description)
	{
		ArrayList<Agenda> agendas=new ArrayList<Agenda>();
		String q="From Agenda agenda";
		if(description!=null && description.trim().length()>0)
			q="From Agenda agenda where agenda.description='"+description+"' or agenda.description like '"+description+"%' or agenda.description like '%"+description+"%' or agenda.description like '%"+description+"'";
		List list=sessionFactory.getCurrentSession().createQuery(q).list();
		for(Iterator it=list.iterator();it.hasNext();)
			agendas.add((Agenda)it.next());
		return agendas;
	}

	@Override
	public boolean isBpNoExists(String bpNo)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select sno from Planning where bpNo='"+bpNo+"'").list().isEmpty())
			return true;
		return false;
	}

	@Override
	public void insertOrUpdatePlan(Planning planning)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(planning);		
	}

	@Override
	public ArrayList<Planning> retrievePlanRecords(ArrayList<String> params)
	{
		ArrayList<Planning> records=new ArrayList<Planning>();
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From Planning planning where (planning."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or planning."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or planning."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (planning."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or planning."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or planning."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From Planning planning";
		List list=sessionFactory.getCurrentSession().createQuery(q).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((Planning)it.next());
		return records;
	}

	@Override
	public Planning retrievePlanRecord(int sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Planning planning where planning.sno="+sno+"").list().iterator();it.hasNext();)
			return (Planning)it.next();
		return null;
	}
	
	@Override
	public ArrayList<Planning> retrievePlanRecord(String[] snos)
	{
		ArrayList<Planning> records=new ArrayList<Planning>();
		session=sessionFactory.getCurrentSession();
		for(String sno: snos)
		{
			for(Iterator it=session.createQuery("From Planning planning where planning.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((Planning)it.next());
		}
		return records;
	}
	
	@Override
	public String getLocation(String department,int sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createSQLQuery("Select location from "+department+" where sno="+sno+"").list().iterator();it.hasNext();)
			return (String)it.next();
		return null;
	}
}