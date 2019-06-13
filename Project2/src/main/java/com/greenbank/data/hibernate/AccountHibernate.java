package com.greenbank.data.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbank.beans.Account;
import com.greenbank.beans.Customer;
import com.greenbank.data.AccountDao;
import com.greenbank.utils.HibernateUtil;

public class AccountHibernate implements AccountDao {
	
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
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<Account> query = critBuilder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root).where(critBuilder.equal(root.get("customer_id"), customer.getId()));
		Query<Account> q = s.createQuery(query);
		
		return q.getSingleResult();
	}
	
	@Override
	public ArrayList<Account> getAccountsByCustomerId(Customer customer){
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<Account> query = critBuilder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root).where(critBuilder.equal(root.get("customer_id"), customer.getId()));
		ArrayList<Account> accountList = (ArrayList<Account>) s.createQuery(query).getResultList();
		
		return accountList;
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
