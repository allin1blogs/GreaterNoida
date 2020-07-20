package com.ak.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ak.modals.Health;

@Repository("HealthDao")
public class HealthDaoImpl implements HealthDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Override
	public void insertOrUpdateHealth(Health Health) {
		sessionFactory.getCurrentSession().saveOrUpdate(Health);
	}

	@Override
	public boolean isOptFtsNoExists(String FTS_NO_OPA_NO) {
		if (!sessionFactory.getCurrentSession()
				.createSQLQuery("Select optFts from Health where optFts='" + FTS_NO_OPA_NO + "'").list().isEmpty())
			return true;
		return false;
	}

	@Override
	public Health retrieveHealth(int sno) {
		System.out.println("retrieveHealthRecords(int Sno):1");
		for (Iterator it = sessionFactory.getCurrentSession().createQuery("From Health m where m.Sno=" + sno + "")
				.list().iterator(); it.hasNext();)
			return (Health) it.next();
		return null;
	}

	@Override
	public ArrayList<Health> retrieveHealth(String[] Snos) {
		ArrayList<Health> records = new ArrayList<Health>();
		session = sessionFactory.getCurrentSession();
		for (String Sno : Snos) {
			for (Iterator it = session.createQuery("From Health m where m.Sno=" + Integer.parseInt(Sno) + "").list()
					.iterator(); it.hasNext();)
				records.add((Health) it.next());
		}
		return records;
	}

	@Override
	public ArrayList<Health> retrieveHealth(ArrayList<String> params, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Health> retrieveHealthRecords(ArrayList<String> params) {
		System.out.println("Health Retrieve Records:1");
		ArrayList<Health> records = new ArrayList<Health>();
		System.out.println("Health Retrieve Records:2");
		String q = null;

		if (!params.isEmpty()) {
			for (String param : params) {
				if (q == null)
					q = "From Health m where (m." + param.substring(param.indexOf("@") + 1) + " like '"
							+ param.substring(0, param.indexOf("@")) + "%' or m."
							+ param.substring(param.indexOf("@") + 1) + " like '%"
							+ param.substring(0, param.indexOf("@")) + "' or m."
							+ param.substring(param.indexOf("@") + 1) + " like '%"
							+ param.substring(0, param.indexOf("@")) + "%')";
				else
					q = q + " and (m." + param.substring(param.indexOf("@") + 1) + " like '"
							+ param.substring(0, param.indexOf("@")) + "%' or m."
							+ param.substring(param.indexOf("@") + 1) + " like '%"
							+ param.substring(0, param.indexOf("@")) + "' or m."
							+ param.substring(param.indexOf("@") + 1) + " like '%"
							+ param.substring(0, param.indexOf("@")) + "%')";
			}
		} else
			q = "From Health m";
		List<Health> list = sessionFactory.getCurrentSession().createQuery(q).list();
		for (Iterator<Health> it = list.iterator(); it.hasNext();)
			records.add((Health) it.next());
		return records;
	}

	@Override
	public Health getHealthRecord(int Sno) {
		System.out.println("getHealthRecord(int Sno):1");
		for (Iterator it = sessionFactory.getCurrentSession().createQuery("From Health hl where hl.sno=" + Sno + "").list().iterator(); it.hasNext();)
			return (Health) it.next();
		return null;
	}

	@Override
	public String getHealthLocation(String department, int Sno) {
		System.out.println("getLocation: Health");
		for (Iterator it = sessionFactory.getCurrentSession()
				.createSQLQuery("Select location from " + department + " where Sno=" + Sno + "").list().iterator(); it
						.hasNext();)
			return (String) it.next();
		return null;
	}

	@Override
	public String getLocation(int Sno, String department) {
		System.out.println("getLocation:");
		for (Iterator it = sessionFactory.getCurrentSession()
				.createSQLQuery("Select location from " + department + " where Sno=" + Sno + "").list().iterator(); it
						.hasNext();)
			return (String) it.next();
		return null;
	}

	@Override
	public ArrayList<Health> retrieveHealthRecords(String[] snos) {
		System.out.println("post method:1");
		ArrayList<Health> records = new ArrayList<Health>();
		System.out.println("2");
		session = sessionFactory.getCurrentSession();
		for (String sno : snos)
			for (Iterator it = session.createQuery("From Health law where law.sno=" + Integer.parseInt(sno) + "")
					.list().iterator(); it.hasNext();)
				records.add((Health) it.next());
		return records;
	}


}