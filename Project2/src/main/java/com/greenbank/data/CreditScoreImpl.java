package com.greenbank.data;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.CreditScore;
import com.greenbank.beans.Customer;
import com.greenbank.utils.HibernateUtil;

@Component
public class CreditScoreImpl implements CreditScoreDao {

	@Autowired
	private HibernateUtil hu;
	
	@Override
	public CreditScore getCreditScore(Customer customer) {
		Session s = hu.getSession();
		CreditScore c;
		String query = "from CreditScore c where c.customer.id=:id";
		Query<CreditScore> q = s.createQuery(query, CreditScore.class);
		q.setParameter("id", customer.getId());
		c = q.getSingleResult();
		s.close();
		return c;
	}

	@Override
	public int addCreditScore(CreditScore score) {
		Session s = hu.getSession();
		org.hibernate.Transaction t = s.beginTransaction();
		int id = 0;
		try {
			id = (int) s.save(score);
			t.commit();
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
		return id;
	}

}
