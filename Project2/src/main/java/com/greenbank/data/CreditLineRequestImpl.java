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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Employee;
import com.greenbank.utils.HibernateUtil;

@Component
public class CreditLineRequestImpl implements CreditLineRequestDao {
	
	@Autowired
	private HibernateUtil hu;
  
	public CreditLineRequestImpl() {
		super();
	}

	@Override
	public List<CreditLineRequest> getRequestsByManager(Employee manager) {
		ArrayList<CreditLineRequest> requests = null;
		Session session = hu.getSession();
		String hqlString = "from com.greenbank.beans.CreditLineRequest req where req.id=:id";
		Query<CreditLineRequest> query = session.createQuery(hqlString, CreditLineRequest.class);
        query.setParameter(":id", manager.getId());
		requests = new ArrayList<CreditLineRequest>(query.getResultList());
		return requests;
	}	
	
	@Override
	public List<CreditLineRequest> getRequestsByManagerID(int id) {
		ArrayList<CreditLineRequest> requests = null;
		Session session = hu.getSession();
		String hqlString = "from com.greenbank.beans.CreditLineRequest req where req.id=:id";
		Query<CreditLineRequest> query = session.createQuery(hqlString, CreditLineRequest.class);
        query.setParameter(":id", id);
		requests = new ArrayList<CreditLineRequest>(query.getResultList());
		return requests;
	}

	@Override
	public List<CreditLineRequest> getRequestsAvailableToAll() {
		ArrayList<CreditLineRequest> requests = null;
		Session session = hu.getSession();
		String query = "from com.greenbank.beans.CreditLineRequest req where req.employeeApprover=null";
		Query<CreditLineRequest> q = session.createQuery(query, CreditLineRequest.class);
		requests = new ArrayList<CreditLineRequest>(q.getResultList());
		return requests;
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
