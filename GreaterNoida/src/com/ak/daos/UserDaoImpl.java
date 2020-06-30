package com.ak.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ak.beans.UserBean;
import com.ak.modals.General;
import com.ak.modals.UserDepartments;
import com.ak.modals.Users;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Repository("userDao")
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Override
	public String authUser(String userId,String password)
	{
		String flage="Null";
		for(Iterator it=sessionFactory.getCurrentSession().createSQLQuery("Select disable from Users where userId='"+userId+"' and password='"+password+"'").list().iterator();it.hasNext();)
		{
			if((Boolean)it.next())
				flage="Disabled";
			else
				flage="Success";
		}
		return flage;
	}

	@Override
	public UserBean getUserBean(String userId)
	{
		UserBean uBean=new UserBean();
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Users user where user.userId='"+userId+"'").list().iterator();it.hasNext();)
		{
			Users user=(Users)it.next();
			uBean.setUserId(user.getUserId());
			uBean.setUserType(user.getUserType());
			uBean.setUpload(user.getUpload());
			uBean.setView(user.getView());
			uBean.setDownload(user.getDownload());
			uBean.setPrint(user.getPrint());
			uBean.setUpdate(user.getUpdate());
			uBean.setReport(user.getReport());
			uBean.setAgenda(user.getAgenda());
			uBean.setLogs(user.getLogs());
		}
		return uBean;
	}

	@Override
	public boolean isUserIdExists(String userId)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select sno from Users where userId='"+userId+"'").list().isEmpty())
			return true;
		return false;
	}

	@Override
	public void insertOrUpdateUser(Users user)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(user);		
	}

	@Override
	public void insertOrUpdateUserDepartments(ArrayList<UserDepartments> userDepartments)
	{
		session=sessionFactory.getCurrentSession();
		session.createSQLQuery("Delete from UserDepartments where userId='"+userDepartments.get(0).getUserId()+"'").executeUpdate();
		for(UserDepartments userDepartment:userDepartments)
			session.save(userDepartment);
	}
/*=============================================================================*/
	@Override
	public Users getUserDetails(String userId)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Users user where user.userId='"+userId+"'").list().iterator();it.hasNext();)
			return (Users)it.next();
		return null;
	}

	/*
	 ============================================================================= */
	@Override
	public void removeUser(String userId)
	{
		session=sessionFactory.getCurrentSession();
		session.createSQLQuery("Delete from Users where userId='"+userId+"'").executeUpdate();
		session.createSQLQuery("Delete from UserDepartments where userId='"+userId+"'").executeUpdate();
	}

	@Override
	public ArrayList<General> getAllRecords(String department)
	{
		ArrayList<General> records=new ArrayList<General>();
		List list=sessionFactory.getCurrentSession().createQuery("From General general where general.department='"+department+"'").list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((General)it.next());
		return records;
	}
}