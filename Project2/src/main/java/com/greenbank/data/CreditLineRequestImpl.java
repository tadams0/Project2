package com.greenbank.data;

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
	public Set<CreditLineRequest> getRequestsByManager(Employee manager) {
		return getRequestsByID("employee_id", manager.getId());
	}

	@Override
	public Set<CreditLineRequest> getRequestsAvailableToAll() {
		return getRequestsByID("employee_id", 0);
	}
 
	private HashSet<CreditLineRequest> getRequestsByID(String column, int id) {
		Session s = hu.getSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<CreditLineRequest> criteria = builder.createQuery(CreditLineRequest.class);
		Root<CreditLineRequest> root = criteria.from(CreditLineRequest.class);
		criteria.select(root).where(builder.equal(root.get(column), ""+id));
		List<CreditLineRequest> requests =  s.createQuery(criteria).getResultList();
		return new HashSet<CreditLineRequest>(requests);
	}
	
}
