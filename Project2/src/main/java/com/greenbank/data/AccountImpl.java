package com.greenbank.data;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.Account;
import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Customer;
import com.greenbank.utils.HibernateUtil;

@Component
public class AccountImpl implements AccountDao{

	@Autowired
	private HibernateUtil hu;
	
	public AccountImpl() {
		super();
	}
	
	@Override
	public int addAccount(Account account) {
		Session s = hu.getSession();
		org.hibernate.Transaction t = s.beginTransaction();
		int id = 0;
		try {
			id = (int) s.save(account);
			t.commit();
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
		return id;
	}
}
