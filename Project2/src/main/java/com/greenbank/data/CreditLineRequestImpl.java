package com.greenbank.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Employee;
import com.greenbank.utils.HibernateUtil;

public class CreditLineRequestImpl implements CreditLineRequestDao {
	private static HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public List<CreditLineRequest> getRequestsByManager(Employee manager) {
		return getRequestsByID("employee_id", manager.getId());
	}

	@Override
	public List<CreditLineRequest> getRequestsAvailableToAll() {
		return getRequestsByID("employee_id", 0);
	}
 
	private ArrayList<CreditLineRequest> getRequestsByID(String column, int id) {
		Session s = hu.getSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<CreditLineRequest> criteria = builder.createQuery(CreditLineRequest.class);
		
		Root<CreditLineRequest> root = criteria.from(CreditLineRequest.class);
		
		criteria.select(root).where(builder.equal(root.get(column), ""+id));
		
		ArrayList<CreditLineRequest> requests =  (ArrayList<CreditLineRequest>) s.createQuery(criteria).getResultList();
		return new ArrayList<CreditLineRequest>(requests);
	}
	
}
