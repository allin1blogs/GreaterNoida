

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.modals.HR;


@Service("HRService1")
@Transactional
public class HRDaoImp implements HRDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	Session session;
	
	@Override
	public ArrayList<HR> retrieveHRRecords(ArrayList<String> params)
	{
		ArrayList<HR> records=new ArrayList<HR>();
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

}


