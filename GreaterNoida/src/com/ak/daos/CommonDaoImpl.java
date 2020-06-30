package com.ak.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ak.modals.Logs;
import com.ak.modals.UserDepartments;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Repository("commonDao")
public class CommonDaoImpl implements CommonDao
{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Override
	public ArrayList<String> getAllDepartments()
	{
		ArrayList<String> departments=new ArrayList<String>();
		System.out.println("CommanDao...");
		List list=sessionFactory.getCurrentSession().createSQLQuery("Select distinct department from Departments").list();
		for(Iterator it=list.iterator();it.hasNext();)
			departments.add((String)it.next());
		return departments;
	}

	@Override
	public ArrayList<UserDepartments> getAllDepartments(String userId)
	{
		ArrayList<UserDepartments> departments=new ArrayList<UserDepartments>();
		List list=sessionFactory.getCurrentSession().createQuery("From UserDepartments where userId='"+userId+"'").list();
		for(Iterator it=list.iterator();it.hasNext();)
			departments.add((UserDepartments)it.next());
		return departments;
	}
	
	@Override
	public ArrayList<String> getAllSectors(String department)
	{
		ArrayList<String> sectors=new ArrayList<String>();
		for(String a:sectors) {
			System.out.println(a+" ");
		}
		
		System.out.println("CommanDaoImp");
		List list=sessionFactory.getCurrentSession().createSQLQuery("Select distinct sector from Departments where department='"+department+"'").list();
		for(Iterator it=list.iterator();it.hasNext();)
			sectors.add((String)it.next());
		return sectors;
	}
	
	@Override
	public ArrayList<String> getAllSectors(String department,String userId)
	{    System.out.println("CommanDaoImp:2");
		ArrayList<String> sectors=new ArrayList<String>();
		List list=sessionFactory.getCurrentSession().createSQLQuery("Select distinct sector from UserDepartments where department='"+department+"' and userId='"+userId+"'").list();
		for(Iterator it=list.iterator();it.hasNext();)
			sectors.add((String)it.next());
		return sectors;
	}

	@Override
	public ArrayList<String> getAllUsers(String uId)
	{
		ArrayList<String> users=new ArrayList<String>();
		List list=sessionFactory.getCurrentSession().createSQLQuery("Select userId from Users").list();
		for(Iterator it=list.iterator();it.hasNext();)
			users.add((String)it.next());
		if(!uId.equals("Admin") && !uId.equals("CEO"))
			users.remove("CEO");
		return users;
	}

	@Override
	public ArrayList<String> getAllUsers(String department,String uId)
	{
		ArrayList<String> users=new ArrayList<String>();
		List list=sessionFactory.getCurrentSession().createSQLQuery("Select distinct userId from UserDepartments where department='"+department+"'").list();
		for(Iterator it=list.iterator();it.hasNext();)
			users.add((String)it.next());
		if(!uId.equals("Admin"))
			users.remove("CEO");
		return users;
	}

	@Override
	public String getHelp(String obj,String key,String value)
	{
		session=sessionFactory.getCurrentSession();
		String helpBox="",q=null;
		if(obj.equals("getDescription"))
			q="Select distinct description from Agenda where description like '"+value+"%' or description like '%"+value+"%' or description like '%"+value+"'";
		if(obj.equals("getParams"))
			q="Select distinct "+key.substring(0,key.indexOf("@"))+" from "+key.substring(key.indexOf("@")+1)+" where "+key.substring(0,key.indexOf("@"))+" like '"+value+"%' or "+key.substring(0,key.indexOf("@"))+" like '%"+value+"%' or "+key.substring(0,key.indexOf("@"))+" like '%"+value+"'";
		if(obj.equals("getSectors"))
		{
			for(String department:value.split(","))
			{
				q="Select sector from departments where department='"+department+"'";
				for(Iterator it=session.createSQLQuery(q).list().iterator();it.hasNext();)
					helpBox=helpBox+(String)it.next()+"("+department+")<@>";
			}
			return helpBox;
		}
		if(!obj.equals("getSectors"))
		{
			List list=session.createSQLQuery(q).setMaxResults(10).list();
			for(Iterator it=list.iterator();it.hasNext();)
				helpBox=helpBox+(String)it.next()+"<@>";
		}
		return helpBox;
	}

	@Override
	public void insertLogs(String userId, String activity)
	{
		Logs log=new Logs(userId,activity);
		sessionFactory.getCurrentSession().save(log);
	}

	@Override
	public ArrayList<Logs> retrieveLogs(String userId)
	{
		ArrayList<Logs> logs=new ArrayList<Logs>();
		List list=sessionFactory.getCurrentSession().createQuery("From Logs log where log.userId='"+userId+"'").setMaxResults(500).list();
		for(Iterator it=list.iterator();it.hasNext();)
			logs.add((Logs)it.next());
		return logs;
	}
}