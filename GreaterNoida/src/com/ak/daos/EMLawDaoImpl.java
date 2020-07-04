package com.ak.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ak.modals.EM;
import com.ak.modals.EM2;
import com.ak.modals.EM3;
import com.ak.modals.Law;
import com.ak.modals.Planning2;
import com.ak.modals.ProjectTech;
import com.ak.modals.Systems;
import com.ak.modals.UE;




@Repository("emlDao")
public class EMLawDaoImpl implements EMLawDao
{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Override
	public void insertOrUpdateEMRecord(EM em)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(em);
	}

	@Override
	public void insertOrUpdateLawRecord(Law law)
	{   System.out.println("dataInsertedLaw:");
		sessionFactory.getCurrentSession().saveOrUpdate(law);		
	}

	@Override
	public void insertOrUpdateProjectTechRecord(ProjectTech pt) {
		System.out.println("dataInsertedProjectTech:");
		sessionFactory.getCurrentSession().saveOrUpdate(pt);	
		
	}
	
	
	@Override
	public void insertOrUpdateSystemRecord(Systems sy) {
		System.out.println("dataInserted:Systems");
		sessionFactory.getCurrentSession().saveOrUpdate(sy);	
		
	}
/*-------------------------Planning2----------------------------*/
	@Override
	public void insertOrUpdatePlanning2Record(Planning2 planning2) {
		System.out.println("dataInserted Planning2:");
		sessionFactory.getCurrentSession().saveOrUpdate(planning2);	
		
	}
	

	@Override
	public void insertOrUpdateUERecord(UE ue) {
		System.out.println("dataInserted UE:");
		sessionFactory.getCurrentSession().saveOrUpdate(ue);
		
	}

	
	
	
	@Override
	public void insertOrUpdateEM2Record(EM2 em2) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(em2);
		
	}
	
	@Override
	public void insertOrUpdateEM3Record(EM3 em) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(em);
		
	}

	@Override
	public boolean isPetitionNoexists(String petitionNo)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select petitionNo from EM where petitionNo='"+petitionNo+"'").list().isEmpty())
			return true;
		return false;
	}

	
	
	
	@Override
	public ArrayList<EM> retrieveEMRecords(ArrayList<String> params)
	{   System.out.println("EM Retrieve Records:1");
		ArrayList<EM> records=new ArrayList<EM>();
		System.out.println("EM Retrieve Records:2");
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From EM em where (em."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (em."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From EM em";
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((EM)it.next());
		return records;
	}

	@Override
	public ArrayList<EM2> retrieveEM2Records(ArrayList<String> params)
	{   System.out.println("EM2 Retrieve Records");
		ArrayList<EM2> records=new ArrayList<EM2>();
		System.out.println("2");
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From EM2 em where (em."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (em."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From EM2 em";
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((EM2)it.next());
		return records;
	}
	
	@Override
	public ArrayList<UE> retrieveUERecords(ArrayList<String> params) {
		 System.out.println("UE Retrieve Records");
			ArrayList<UE> records=new ArrayList<UE>();
			String q=null;
			if(!params.isEmpty())
			{
				for(String param:params)
				{
					if(q==null)
						q="From UE ue where (ue."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or ue."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or ue."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
					else
						q=q+" and (ue."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or ue."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or ue."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				}
			}
			else
				q="From UE ue";
			List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
			for(Iterator it=list.iterator();it.hasNext();)
				records.add((UE)it.next());
			return records;
	}
	@Override
	public ArrayList<Law> retrieveLawRecords(ArrayList<String> params)
	{
		
		System.out.println("retrieveLawRecords:1");
		ArrayList<Law> records=new ArrayList<Law>();
		System.out.println("retrieveLawRecords:2");
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From Law law where (law."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (law."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From Law law";
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((Law)it.next());
		return records;
	}

	



	@Override
	public ArrayList<Planning2> retrievePlanning2Records(ArrayList<String> params) {
		System.out.println("retrievePlanning2Records:1");
		ArrayList<Planning2> records=new ArrayList<Planning2>();
		System.out.println("retrievePlanning2Records:2");
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From Planning2 law where (law."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (law."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From Planning2 law";
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((Planning2)it.next());
		return records;
	}
	
	
	
	@Override
	public ArrayList<Systems> retrieveSystemRecords(ArrayList<String> params) {
		System.out.println("retrieveSystemRecords:1");
		ArrayList<Systems> records=new ArrayList<Systems>();
		System.out.println("retrieveSystemRecords:2");
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From Systems law where (law."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (law."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From Systems law";
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((Systems)it.next());
		return records;
	}
	
	
	@Override
	public String getLocation(int sno,String department)
	{     System.out.println("getLocation:");
		for(Iterator it=sessionFactory.getCurrentSession().createSQLQuery("Select location from "+department+" where sno="+sno+"").list().iterator();it.hasNext();)
			return (String)it.next();
		return null;
	}

	@Override
	public EM getEMRecord(int sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From EM em where em.sno="+sno+"").list().iterator();it.hasNext();)
			return (EM)it.next();
		return null;
	}
	
	@Override
	public EM3 getEM3Record(int sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From EM3 em where em.sno="+sno+"").list().iterator();it.hasNext();)
			return (EM3)it.next();
		return null;
	}

	@Override
	public Law getLawRecord(int sno)
	{    System.out.println("getLawRecord(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Law law where law.sno="+sno+"").list().iterator();it.hasNext();)
			return (Law)it.next();
		return null;
	}

	@Override
	public ProjectTech retrieveProjectTechRecords(int snos) {
		System.out.println("retrieveProjectTechRecords(int snos):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From ProjectTech law where law.sno="+snos+"").list().iterator();it.hasNext();)
			return (ProjectTech)it.next();
		return null;
	}
	
	@Override
	public ArrayList<EM> retrieveEMRecords(String[] snos)
	{
		ArrayList<EM> records=new ArrayList<EM>();
		session=sessionFactory.getCurrentSession();
		for(String sno:snos)
		{
			for(Iterator it=session.createQuery("From EM em where em.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((EM)it.next());
		}
		return records;
	}

	@Override
	public ArrayList<Law> retrieveLawRecords(String[] snos)
	{  System.out.println("retrieveLawRecords:post method:1");
		ArrayList<Law> records=new ArrayList<Law>();
		System.out.println("retrieveLawRecords(String[] snos):2");
		session=sessionFactory.getCurrentSession();
		for(String sno:snos)
			for(Iterator it=session.createQuery("From Law law where law.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((Law)it.next());
		return records;
	}

	

	@Override
	public ArrayList<Planning2> retrievePlanning2Records(String[] snos) {
		 System.out.println("retrievePlanning2Records:post method:1");
			ArrayList<Planning2> records=new ArrayList<Planning2>();
			System.out.println("retrievePlanning2Records(String[] snos):2");
			session=sessionFactory.getCurrentSession();
			for(String sno:snos)
				for(Iterator it=session.createQuery("From Planning2 law where law.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
					records.add((Planning2)it.next());
			return records;
	}

	
	
	@Override
	public ArrayList<ProjectTech> retrieveProjectTechRecords(String[] snos) {
		System.out.println("retrieveProjectTechRecords:post method:1");
		ArrayList<ProjectTech> records=new ArrayList<ProjectTech>();
		System.out.println("retrieveProjectTechRecords(String[] snos):2");
		session=sessionFactory.getCurrentSession();
		for(String sno:snos)
			for(Iterator it=session.createQuery("From ProjectTech law where law.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((ProjectTech)it.next());
		return records;
	}
	@Override
	public ArrayList<Systems> retrieveSystemRecords(String[] snos) {
		System.out.println("post method:1");
		ArrayList<Systems> records=new ArrayList<Systems>();
		System.out.println("2");
		session=sessionFactory.getCurrentSession();
		for(String sno:snos)
			for(Iterator it=session.createQuery("From Systems law where law.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((Systems)it.next());
		return records;
	}
	
	@Override
	public Law retrieveLawRecords(int sno) {
		System.out.println("retrieveLawRecords(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Law law where law.sno="+sno+"").list().iterator();it.hasNext();)
			return (Law)it.next();
		return null;	
	}

	@Override
	public Planning2 retrievePlanning2Records(int snos) {
		System.out.println("retrieveLawRecords(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Planning2 law where law.sno="+snos+"").list().iterator();it.hasNext();)
			return (Planning2)it.next();
		return null;	
	}
	
	
	@Override
	public Systems retrieveSystemRecords(int sno) {
		System.out.println("retrieveSystemsRecords(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Systems law where law.sno="+sno+"").list().iterator();it.hasNext();)
			return (Systems)it.next();
		return null;	
	}

	
	
	@Override
	public Systems getSystemRecord(int sno) {
		System.out.println("getSystemRecor:1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Systems law where law.sno="+sno+"").list().iterator();it.hasNext();)
			return (Systems)it.next();
		return null;	
	}

	@Override
	public ArrayList<ProjectTech> retrieveProjectTechRecords(ArrayList<String> params) {
		System.out.println("retrieveProjectTechRecords:1");
		ArrayList<ProjectTech> records=new ArrayList<ProjectTech>();
		System.out.println("retrieveProjectTechRecords:2");
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From ProjectTech law where (law."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (law."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or law."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From ProjectTech law";
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((ProjectTech)it.next());
		return records;
	}



	@Override
	public EM2 retrieveEM2Records(int snos) {
		System.out.println("retrieveEM2Records(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From EM2 law where law.sno="+snos+"").list().iterator();it.hasNext();)
			return (EM2)it.next();
		return null;
	}
	
	@Override
	public EM3 retrieveEM3Records(int snos) {
		System.out.println("retrieveEM3Records(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From EM3 law where law.sno="+snos+"").list().iterator();it.hasNext();)
			return (EM3)it.next();
		return null;
	}



	@Override
	public ArrayList<EM2> retrieveEM2Records(String[] snos) {
		System.out.println("retrieveEM2Records:post method:1");
		ArrayList<EM2> records=new ArrayList<EM2>();
		System.out.println("retrieveEM2Records(String[] snos):2");
		session=sessionFactory.getCurrentSession();
		for(String sno:snos)
			for(Iterator it=session.createQuery("From EM2 law where law.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((EM2)it.next());
		return records;
	}



	@Override
	public UE retrieveUERecords(int snos) {
		System.out.println("retrieveUERecords(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From UE law where law.sno="+snos+"").list().iterator();it.hasNext();)
			return (UE)it.next();
		return null;	
	}



	@Override
	public ArrayList<UE> retrieveUERecords(String[] snos) {
		System.out.println("retrieveUERecords:post method:1");
		ArrayList<UE> records=new ArrayList<UE>();
		System.out.println("retrieveUERecords(String[] snos):2");
		session=sessionFactory.getCurrentSession();
		for(String sno:snos)
			for(Iterator it=session.createQuery("From UE law where law.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((UE)it.next());
		return records;
	}

	@Override
	public ArrayList<EM3> retrieveEM3Records(ArrayList<String> params) {
		 System.out.println("EM3 Retrieve Records");
			ArrayList<EM3> records=new ArrayList<EM3>();
			System.out.println("2");
			String q=null;
			if(!params.isEmpty())
			{
				for(String param:params)
				{
					if(q==null)
						q="From EM3 em where (em."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
					else
						q=q+" and (em."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or em."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				}
			}
			else
				q="From EM3 em";
			System.out.println("===============================================================");
			List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
			for(Iterator it=list.iterator();it.hasNext();)
				records.add((EM3)it.next());
			return records;
	}



	
}