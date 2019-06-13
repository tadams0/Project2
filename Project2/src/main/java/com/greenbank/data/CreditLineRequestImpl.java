package com.greenbank.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Employee;
import com.greenbank.utils.HibernateUtil;

@Component
public class CreditLineRequestImpl implements CreditLineRequestDao {
	
	@Autowired
	private static HibernateUtil hu;
 
	@Override
	public List<CreditLineRequest> getRequestsByManager(Employee manager) {
		return getRequestsByID("employee_id", manager.getId());
	}

	@Override
	public List<CreditLineRequest> getRequestsAvailableToAll() {
		return getRequestsByID("employee_id", 0);
	}
 
	private ArrayList<CreditLineRequest> getRequestsByID(String column, int id) {
		System.out.println(hu);
		Session s = hu.getSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<CreditLineRequest> criteria = builder.createQuery(CreditLineRequest.class);
		
		Root<CreditLineRequest> root = criteria.from(CreditLineRequest.class);
		
		criteria.select(root).where(builder.equal(root.get(column), ""+id));
		
		ArrayList<CreditLineRequest> requests =  (ArrayList<CreditLineRequest>) s.createQuery(criteria).getResultList();
		return new ArrayList<CreditLineRequest>(requests);
	}

	@Override
	public int addRequest(CreditLineRequest req) {
		Session s = hu.getSession();
		org.hibernate.Transaction t = s.beginTransaction();
		int id = 0;
		try {
			id = (int) s.save(req);
			t.commit();
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
		return id;
	}
	
}
