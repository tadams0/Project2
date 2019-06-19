package com.greenbank.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.Account;
import com.greenbank.beans.AccountTransaction;
import com.greenbank.utils.HibernateUtil;

@Component
public class AccountTransactionHibernate implements AccountTransactionDAO {

	@Autowired
	private HibernateUtil hu;
	
	@Autowired
	private AccountHibernate accountHibernate;
	
	private Logger log = Logger.getLogger(AccountTransactionHibernate.class);
	
	@Override
	public int addTransaction(AccountTransaction at) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		int id = 0;
		try {
			id = (int) s.save(at);
			t.commit();
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
		return id;
	}

	@Override
	public AccountTransaction getTransaction(AccountTransaction at) {
		Session s = hu.getSession();
		AccountTransaction ret = s.get(AccountTransaction.class, at.getId());
		s.close();
		return ret;
	}

	@Override
	public AccountTransaction getTransaction(int i) {
		Session s = hu.getSession();
		AccountTransaction ret = s.get(AccountTransaction.class, i);
		s.close();
		return ret;
	}

	@Override
	public AccountTransaction getTransactionByAccountId(Account account) {
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<AccountTransaction> query = critBuilder.createQuery(AccountTransaction.class);
		Root<AccountTransaction> root = query.from(AccountTransaction.class);
		query.select(root).where(critBuilder.equal(root.get("id"), account.getId()));
		Query<AccountTransaction> q = s.createQuery(query);
		s.close();
		return q.getSingleResult();
	}

	@Override
	public ArrayList<AccountTransaction> getTransactionsByAccountId(Account account) {
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<AccountTransaction> query = critBuilder.createQuery(AccountTransaction.class);
		Root<AccountTransaction> root = query.from(AccountTransaction.class);
		query.select(root).where(critBuilder.equal(root.get("account"), account));
		ArrayList<AccountTransaction> transactionList = (ArrayList<AccountTransaction>) s.createQuery(query).getResultList();
		s.close();
		return transactionList;
	}

	@Override
	public ArrayList<AccountTransaction> getTransactionsByAccountId(int i) {
		Account account = new Account();
		account.setId(i);
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<AccountTransaction> query = critBuilder.createQuery(AccountTransaction.class);
		Root<AccountTransaction> root = query.from(AccountTransaction.class);
		query.select(root).where(critBuilder.equal(root.get("account"), account));
		ArrayList<AccountTransaction> transactionList = (ArrayList<AccountTransaction>) s.createQuery(query).getResultList();
		s.close();
		return transactionList;
	}
	

	@Override
	public Set<AccountTransaction> getTransactions() {
		Session s = hu.getSession();
		String query = "from Transaction";
		log.trace(query);
		Query<AccountTransaction> q = s.createQuery(query, AccountTransaction.class);
		List<AccountTransaction> transactionList = q.getResultList();
		Set<AccountTransaction> transactionSet = new HashSet<AccountTransaction>();
		transactionSet.addAll(transactionList);
		log.trace(transactionList);
		s.close();
		return transactionSet;
	}

	@Override
	public void updateTransaction(AccountTransaction at) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(at.getId());
		t.commit();
		s.close();
	}

	@Override
	public void deleteTransaction(AccountTransaction at) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(at.getId());
		t.commit();
		s.close();

	}

}
