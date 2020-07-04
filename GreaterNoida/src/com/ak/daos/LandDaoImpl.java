package com.ak.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ak.modals.Land;
import com.ak.modals.Marketing;

@Repository("LandDao")
public class LandDaoImpl implements LandDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Override
	public void insertOrUpdateLand(Land land) {
		sessionFactory.getCurrentSession().saveOrUpdate(land);
	}

	@Override
	public boolean isOptFtsNoExists(String FTS_NO_OPA_NO) {
		if (!sessionFactory.getCurrentSession()
				.createSQLQuery("Select optFts from Marketing where optFts='" + FTS_NO_OPA_NO + "'").list().isEmpty())
			return true;
		return false;
	}

	@Override
	public Marketing retrieveMarketing(int sno) {
		System.out.println("retrieveMarketingRecords(int sno):1");
		for (Iterator it = sessionFactory.getCurrentSession().createQuery("From Marketing m where m.sno=" + sno + "")
				.list().iterator(); it.hasNext();)
			return (Marketing) it.next();
		return null;
	}

	@Override
	public ArrayList<Marketing> retrieveMarketing(String[] snos) {
		ArrayList<Marketing> records = new ArrayList<Marketing>();
		session = sessionFactory.getCurrentSession();
		for (String sno : snos) {
			for (Iterator it = session.createQuery("From Marketing m where m.sno=" + Integer.parseInt(sno) + "").list()
					.iterator(); it.hasNext();)
				records.add((Marketing) it.next());
		}
		return records;
	}

	@Override
	public ArrayList<Marketing> retrieveMarketing(ArrayList<String> params, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Land> retrieveLandRecords(ArrayList<String> params) {
		System.out.println("Land Retrieve Records:1");
		ArrayList<Land> records = new ArrayList<Land>();
		System.out.println("Land Retrieve Records:2");
		String q = null;

		if (!params.isEmpty()) {
			for (String param : params) {
				if (q == null)
					q = "From Land m where (m." + param.substring(param.indexOf("@") + 1) + " like '"
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
			q = "From Land m";
		List list = sessionFactory.getCurrentSession().createQuery(q).setMaxResults(1000).list();
		for (Iterator it = list.iterator(); it.hasNext();)
			records.add((Land) it.next());
		return records;
	}

	@Override
	public Marketing getMarketingRecord(int sno) {
		System.out.println("getMarketingRecord(int sno):1");
		for (Iterator it = sessionFactory.getCurrentSession()
				.createQuery("From Marketing marketing where marketing.sno=" + sno + "").list().iterator(); it
						.hasNext();)
			return (Marketing) it.next();
		return null;
	}

	@Override
	public String getMarketingLocation(String department, int sno) {
		System.out.println("getLocation: Marketing");
		for (Iterator it = sessionFactory.getCurrentSession()
				.createSQLQuery("Select location from " + department + " where sno=" + sno + "").list().iterator(); it
						.hasNext();)
			return (String) it.next();
		return null;
	}

	@Override
	public String getLocation(int sno, String department) {
		System.out.println("getLocation:");
		for (Iterator it = sessionFactory.getCurrentSession()
				.createSQLQuery("Select location from " + department + " where sno=" + sno + "").list().iterator(); it
						.hasNext();)
			return (String) it.next();
		return null;
	}

	@Override
	public ArrayList<Marketing> retrieveMarketingRecords(String[] snos) {
		System.out.println("post method:1");
		ArrayList<Marketing> records = new ArrayList<Marketing>();
		System.out.println("2");
		session = sessionFactory.getCurrentSession();
		for (String sno : snos)
			for (Iterator it = session.createQuery("From Marketing law where law.sno=" + Integer.parseInt(sno) + "")
					.list().iterator(); it.hasNext();)
				records.add((Marketing) it.next());
		return records;
	}

}