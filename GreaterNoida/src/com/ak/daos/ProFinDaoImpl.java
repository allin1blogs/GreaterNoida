package com.ak.daos;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ak.modals.Finance;
import com.ak.modals.General;
import com.ak.modals.Project;

import java.util.Iterator;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Repository("proFinDao")
public class ProFinDaoImpl implements ProFinDao
{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Override
	public boolean isFinExists(String statement,String param2)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select sno from Finance where "+param2+"='"+statement+"'").list().isEmpty())
			return true;
		return false;
	}
	
	@Override
	public boolean isProExists(String statement)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select Opa_Fts from Project where Opa_Fts='"+statement+"'").list().isEmpty())
			return true;
		return false;
	}
	
	@Override
	public void insertOrUpdateFin(Finance finance)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(finance);		
	}

	@Override
	public ArrayList<Finance> retrieveFin(ArrayList<String> params)
	{     System.out.println("retrieve finance record to generate report");
		ArrayList<Finance> records=new ArrayList<Finance>();
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From Finance finance where (finance."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or finance."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or finance."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (finance."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or finance."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or finance."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From Finance finance";
		List list=sessionFactory.getCurrentSession().createQuery(q).list();
		for(Iterator it=list.iterator();it.hasNext();) {
			records.add((Finance)it.next());
            System.out.println(records.get(0).getSno());
		}
		return records;
	}

	@Override
	public Finance retrieveFin(int sno)
	{    System.out.println("retrieve fin");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Finance finance where finance.sno="+sno+"").list().iterator();it.hasNext();)
			return (Finance)it.next();
		return null;
	}
	
	@Override
	public ArrayList<Finance> retrieveFin(String[] snos)
	{   System.out.println("finance report post method:2");
		ArrayList<Finance> records=new ArrayList<Finance>();
		 System.out.println("finance report post method:3");
		session=sessionFactory.getCurrentSession();
		for(String sno: snos)
		{
			for(Iterator it=session.createQuery("From Finance finance where finance.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((Finance)it.next());
		}
		return records;
	}

	@Override
	public void insertOrUpdatePro(Project project)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(project);		
	}

	@Override
	public ArrayList<Project> retrievePro(ArrayList<String> params)
	{
		ArrayList<Project> records=new ArrayList<Project>();
		boolean sectorFlage=false;
		String q="From Project project where project.department='"+params.get(0).substring(0,params.get(0).indexOf("@"))+"'";
		for(int i=1;i<params.size();i++)
		{
			
			if(params.get(i).substring(params.get(i).indexOf("@")+1).equals("scheme"))
			{
				sectorFlage=true;
				q=q+" and project.scheme='"+params.get(i).substring(0,params.get(i).indexOf("@"))+"'";
			}
			else
				q=q+"and (project."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '"+params.get(i).substring(0,params.get(i).indexOf("@"))+"%' or project."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '%"+params.get(i).substring(0,params.get(i).indexOf("@"))+"%' or project."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '%"+params.get(i).substring(0,params.get(i).indexOf("@"))+"')";
		}
		session=sessionFactory.getCurrentSession();
		/*
		 * if(!sectorFlage) { ArrayList<String> sectors=new ArrayList<String>(); List
		 * list=session.
		 * createSQLQuery("Select sector from UserDepartments where userId='"
		 * +userId+"' and department='"+params.get(0).substring(0,params.get(0).indexOf(
		 * "@"))+"'").list(); for(Iterator it=list.iterator();it.hasNext();)
		 * sectors.add((String)it.next()); for(int i=0;i<sectors.size();i++) {
		 * if(sectors.get(i)!=null && !sectors.get(i).equals("null")) { if(i==0)
		 * q=q+" and (project.scheme='"+sectors.get(i)+"'"; else
		 * q=q+" or project.scheme='"+sectors.get(i)+"'"; } } q=q+")"; }
		 */
		List list=session.createQuery(q).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((Project)it.next());
		return records;
	}

	@Override
	public Project retrievePro(int sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Project project where project.sno="+sno+"").list().iterator();it.hasNext();)
			return (Project)it.next();
		return null;
	}
	
	@Override
	public ArrayList<Project> retrievePro(String[] snos)
	{
		ArrayList<Project> records=new ArrayList<Project>();
		session=sessionFactory.getCurrentSession();
		for(String sno: snos)
		{
			for(Iterator it=session.createQuery("From Project project where project.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((Project)it.next());
		}
		return records;
	}

	@Override
	public String getProFinLocation(String department,int sno)
	{    System.out.println("getLocation: Finance");
		for(Iterator it=sessionFactory.getCurrentSession().createSQLQuery("Select location from "+department+" where sno="+sno+"").list().iterator();it.hasNext();)
			return (String)it.next();
		return null;
	}	
	
	@Override
	public ArrayList<Finance> retrieveFin(ArrayList<String> params,String userId)
	{
		ArrayList<Finance> records=new ArrayList<Finance>();
		boolean sectorFlage=false;
		String q="From Finance Finance where Finance.subdepartment='"+params.get(0).substring(0,params.get(0).indexOf("@"))+"'";
		for(int i=1;i<params.size();i++)
		{
			
			if(params.get(i).substring(params.get(i).indexOf("@")+1).equals("scheme"))
			{
				sectorFlage=true;
				q=q+" and Finance.scheme='"+params.get(i).substring(0,params.get(i).indexOf("@"))+"'";
			}
			else
				q=q+"and (Finance."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '"+params.get(i).substring(0,params.get(i).indexOf("@"))+"%' or Finance."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '%"+params.get(i).substring(0,params.get(i).indexOf("@"))+"%' or Finance."+params.get(i).substring(params.get(i).indexOf("@")+1)+" like '%"+params.get(i).substring(0,params.get(i).indexOf("@"))+"')";
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
						q=q+" and (Finance.scheme='"+sectors.get(i)+"'";
					else
						q=q+" or Finance.scheme='"+sectors.get(i)+"'";
				}
			}
			q=q+")";
		}
		List list=session.createQuery(q).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((Finance)it.next());
		return records;
	}
}