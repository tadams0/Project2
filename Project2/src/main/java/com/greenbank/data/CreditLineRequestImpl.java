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
import com.greenbank.beans.Customer;
import com.greenbank.beans.Employee;
import com.greenbank.utils.HibernateUtil;

@Component
public class CreditLineRequestImpl implements CreditLineRequestDAO {
	
	@Autowired
	private HibernateUtil hu;
  
	public CreditLineRequestImpl() {
		super();
	}

	@Override
	public List<CreditLineRequest> getRequestsByManager(Employee manager) {
		ArrayList<CreditLineRequest> requests = null;
		Session session = hu.getSession();
		String hqlString = "from com.greenbank.beans.CreditLineRequest req where req.id=:id and req.status='PENDING'";
		Query<CreditLineRequest> query = session.createQuery(hqlString, CreditLineRequest.class);
        query.setParameter("id", manager.getId());
		requests = new ArrayList<CreditLineRequest>(query.getResultList());
		session.close();
		return requests;
	}	
	
	@Override
	public List<CreditLineRequest> getRequestsByCustomer(Customer customer) {
		ArrayList<CreditLineRequest> requests = null;
		Session session = hu.getSession();
		String hqlString = "from com.greenbank.beans.CreditLineRequest req where req.customer.id=:id and req.status!='REJECTED'";
		Query<CreditLineRequest> query = session.createQuery(hqlString, CreditLineRequest.class);
        query.setParameter("id", customer.getId());
		requests = new ArrayList<CreditLineRequest>(query.getResultList());
		session.close();
		return requests;
	}
	
	@Override
	public List<CreditLineRequest> getRequestsByManagerID(int id) {
		ArrayList<CreditLineRequest> requests = null;
		Session session = hu.getSession();
		String hqlString = "from com.greenbank.beans.CreditLineRequest req where req.id=:id and req.status='PENDING'";
		Query<CreditLineRequest> query = session.createQuery(hqlString, CreditLineRequest.class);
        query.setParameter("id", id);
		requests = new ArrayList<CreditLineRequest>(query.getResultList());
		session.close();
		return requests;
	}

	@Override
	public List<CreditLineRequest> getRequestsAvailableToAll() {
		ArrayList<CreditLineRequest> requests = null;
		Session session = hu.getSession();
		String query = "from com.greenbank.beans.CreditLineRequest req where req.employeeApprover=null and req.status='PENDING'";
		Query<CreditLineRequest> q = session.createQuery(query, CreditLineRequest.class);
		requests = new ArrayList<CreditLineRequest>(q.getResultList());
		session.close();
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

	@Override
	public int rejectRequest(int requestID) {
		Session s = hu.getSession();
		org.hibernate.Transaction t = s.beginTransaction();
		CreditLineRequest req = s.get(CreditLineRequest.class, requestID);
		req.setStatus("REJECTED");
		int id = 0;
		try {
			s.update(req);
			t.commit();
			id = 1;
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
		return id;
	}

	@Override
	public int approveRequest(int requestID, Employee loggedInEmployee) {
		Session s = hu.getSession();
		org.hibernate.Transaction t = s.beginTransaction();
		CreditLineRequest req = s.get(CreditLineRequest.class, requestID);
		System.out.println("Logged in employee: " + loggedInEmployee);
		System.out.println("Manager: " + loggedInEmployee.getManager());
		if (req.getEmployeeApprover() == null)
		{ //If no employee approver, then escalate to the manager.
			req.setEmployeeApprover(loggedInEmployee.getManager());
		}
		else if (req.getEmployeeApprover().getId() == loggedInEmployee.getId())
		{ //If there is an approver, then that is the manager. So set the status to finally approved.
			req.setStatus("APPROVED");
		}
		
		int id = 0;
		try {
			s.update(req);
			t.commit();
			id = 1;
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
		return id;
	}
	
}
