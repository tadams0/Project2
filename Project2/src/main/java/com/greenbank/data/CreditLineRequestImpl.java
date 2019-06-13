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
		return getRequestsByID("employee_id", manager.getId());
	}

	@Override
	public List<CreditLineRequest> getRequestsAvailableToAll() {
		return getRequestsByID("employee_id", 0);
	}
 
	private ArrayList<CreditLineRequest> getRequestsByID(String column, int id) {
		/*
		Session s = hu.getSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<CreditLineRequest> criteria = builder.createQuery(CreditLineRequest.class);
		
		Root<CreditLineRequest> root = criteria.from(CreditLineRequest.class);
		
		criteria.select(root);//.where(builder.equal(root.get(column), ""+id));
		
		ArrayList<CreditLineRequest> requests =  (ArrayList<CreditLineRequest>) s.createQuery(criteria).getResultList();
		return new ArrayList<CreditLineRequest>(requests);
		*/
		// HQL - an interface of Hibernate
		/*
		 * Hibernate Query Language An object based query language for querying
		 * relational databases without any knowledge of their underlying schemas or of
		 * SQL. We can perform DQL and DML
		 */
		ArrayList<CreditLineRequest> requests = null;
		Session s = hu.getSession();
		String query = "from com.greenbank.beans.CreditLineRequest";
		Query<CreditLineRequest> q = s.createQuery(query, CreditLineRequest.class);
		requests = new ArrayList<CreditLineRequest>(q.getResultList());
		
		System.out.println("Request ARRAY: ");
		System.out.println(requests);
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
