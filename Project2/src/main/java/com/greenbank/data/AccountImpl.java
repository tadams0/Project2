package com.greenbank.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbank.beans.Account;
import com.greenbank.beans.Customer;
import com.greenbank.utils.HibernateUtil;
import com.revature.beans.Genre;
import com.revature.beans.User;

public class AccountImpl implements AccountDao {
	
	@Autowired
	private static HibernateUtil hu;

	@Override
	public int addAccount(Account account) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
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

	@Override
	public Account getAccount(Account account) {
		Session s = hu.getSession();
		Account ret = s.get(Account.class, account.getId());
		s.close();
		return ret;
	}

	@Override
	public Account getAccountByCustomerId(Customer customer) {
		Session s = hu.getSession();
		Customer ret = s.get(Customer.class, customer.getId());
		if(ret==null) {
			String query = "from Account a where a.customer_id=:id";
			Query<Account> q = s.createQuery(query, Account.class);
			q.setParameter("id", customer.getId());
			Account account = q.getSingleResult();
		}
		s.close();
		return account;
	}

	@Override
	public Set<Account> getAccounts() {
		Session s = hu.getSession();
		String query = "FROM Account";
		Query<Account> q = s.createQuery(query, Account.class);
		List<Account> accountList = q.getResultList();
		Set<Account> accountSet = new HashSet<Account>();
		accountSet.addAll(accountList);
		return accountSet;
	}

	@Override
	public void updateAccount(Account account) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(account.getId());
		t.commit();
		s.close();
	}

	@Override
	public void deleteAccount(Account account) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(account.getId());
		t.commit();
		s.close();
	}

}
