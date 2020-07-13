package com.ak.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ak.modals.HR;
import com.ak.modals.Marketing;



@Repository("HRDao")
public class HRDaoImp implements HRDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	

	@Override
	public void insertOrUpdateHRRecord(HR hr)
	{   System.out.println("dataInsertedHr:");
		sessionFactory.getCurrentSession().saveOrUpdate(hr);		
	}



	

	@Override
	public ArrayList<HR> retrieveHRRecords(ArrayList<String> params)
	{  System.out.println("retrieveHRRecords:1");
		ArrayList<HR> records=new ArrayList<HR>();
		System.out.println("retrieveHRRecords:2");
		String q=null;
		if(!params.isEmpty())
		{
			for(String param:params)
			{
				if(q==null)
					q="From HR hr where (hr."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or hr."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or hr."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
				else
					q=q+" and (hr."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or hr."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"' or hr."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%')";
			}
		}
		else
			q="From HR hr";
		List list=sessionFactory.getCurrentSession().createQuery(q).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((HR)it.next());
		return records;
	}
	@Override
	public HR retrieveHRRecords(int snos) {
		System.out.println("retrieveLawRecords(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From HR law where law.sno="+snos+"").list().iterator();it.hasNext();)
			return (HR)it.next();
		return null;	
	}
	
	@Override
	public boolean isHRExists(String petitionNo)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select file_code from HR where file_code='"+petitionNo+"'").list().isEmpty())
			return true;
		return false;
	}

	@Override
	public ArrayList<HR> retrieveHRRecords(String[] snos) {
		System.out.println("retrieveHRRecords(String[] snos):1");
		ArrayList<HR> records=new ArrayList<HR>();
		System.out.println("retrieveHRRecords(String[] snos):2");
		session=sessionFactory.getCurrentSession();
		for(String sno:snos)
			for(Iterator it=session.createQuery("From HR law where law.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((HR)it.next());
		return records;
	}
	
	@Override
	public HR getHRRecord(int sno) {
		System.out.println("getHRRecord(int sno):1");
		for (Iterator it = sessionFactory.getCurrentSession()
				.createQuery("From HR hr where hr.sno=" + sno + "").list().iterator(); it
						.hasNext();)
			return (HR) it.next();
		return null;
	}

	/*@Override
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
	}*/

/*	@Override
	public String getLocation(int sno,String department)
	{     System.out.println("getLocation:");
		for(Iterator it=sessionFactory.getCurrentSession().createSQLQuery("Select location from "+department+" where sno="+sno+"").list().iterator();it.hasNext();)
			return (String)it.next();
		return null;
	}
*/
	/*@Override
	public EM getEMRecord(int sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From EM em where em.sno="+sno+"").list().iterator();it.hasNext();)
			return (EM)it.next();
		return null;
	}*/

	/*@Override
	public Law getLawRecord(int sno)
	{    System.out.println("getLawRecord(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Law law where law.sno="+sno+"").list().iterator();it.hasNext();)
			return (Law)it.next();
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
	{  System.out.println("retrieveLawRecords(String[] snos):1");
		ArrayList<Law> records=new ArrayList<Law>();
		System.out.println("retrieveLawRecords(String[] snos):2");
		session=sessionFactory.getCurrentSession();
		for(String sno:snos)
			for(Iterator it=session.createQuery("From Law law where law.sno="+Integer.parseInt(sno)+"").list().iterator();it.hasNext();)
				records.add((Law)it.next());
		return records;
	}

	@Override
	public Law retrieveLawRecords(int sno) {
		System.out.println("retrieveLawRecords(int sno):1");
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Law law where law.sno="+sno+"").list().iterator();it.hasNext();)
			return (Law)it.next();
		return null;	
	}*/
}