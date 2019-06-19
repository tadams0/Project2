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

import com.greenbank.beans.BankAccount;
import com.greenbank.beans.BankTransaction;
import com.greenbank.utils.HibernateUtil;

@Component
public class AccountTransactionImpl implements AccountTransactionDAO {

	@Autowired
	private HibernateUtil hu;
	
	private Logger log = Logger.getLogger(AccountTransactionImpl.class);
	
	@Override
	public int addTransaction(BankTransaction at) {
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
	public BankTransaction getTransaction(BankTransaction at) {
		Session s = hu.getSession();
		BankTransaction ret = s.get(BankTransaction.class, at.getId());
		s.close();
		return ret;
	}

	@Override
	public BankTransaction getTransaction(int i) {
		Session s = hu.getSession();
		BankTransaction ret = s.get(BankTransaction.class, i);
		s.close();
		return ret;
	}

	@Override
	public BankTransaction getTransactionByAccountId(BankAccount account) {
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<BankTransaction> query = critBuilder.createQuery(BankTransaction.class);
		Root<BankTransaction> root = query.from(BankTransaction.class);
		query.select(root).where(critBuilder.equal(root.get("id"), account.getId()));
		Query<BankTransaction> q = s.createQuery(query);
		s.close();
		return q.getSingleResult();
	}

	@Override
	public ArrayList<BankTransaction> getTransactionsByAccountId(BankAccount account) {
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<BankTransaction> query = critBuilder.createQuery(BankTransaction.class);
		Root<BankTransaction> root = query.from(BankTransaction.class);
		query.select(root).where(critBuilder.equal(root.get("account"), account));
		ArrayList<BankTransaction> transactionList = (ArrayList<BankTransaction>) s.createQuery(query).getResultList();
		s.close();
		return transactionList;
	}

	@Override
	public ArrayList<BankTransaction> getTransactionsByAccountId(int i) {
		BankAccount account = new BankAccount();
		account.setId(i);
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<BankTransaction> query = critBuilder.createQuery(BankTransaction.class);
		Root<BankTransaction> root = query.from(BankTransaction.class);
		query.select(root).where(critBuilder.equal(root.get("account"), account));
		ArrayList<BankTransaction> transactionList = (ArrayList<BankTransaction>) s.createQuery(query).getResultList();
		s.close();
		return transactionList;
	}
	

	@Override
	public Set<BankTransaction> getTransactions() {
		Session s = hu.getSession();
		String query = "from Transaction";
		log.trace(query);
		Query<BankTransaction> q = s.createQuery(query, BankTransaction.class);
		List<BankTransaction> transactionList = q.getResultList();
		Set<BankTransaction> transactionSet = new HashSet<BankTransaction>();
		transactionSet.addAll(transactionList);
		log.trace(transactionList);
		s.close();
		return transactionSet;
	}

	@Override
	public void updateTransaction(BankTransaction at) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(at.getId());
		t.commit();
		s.close();
	}

	@Override
	public void deleteTransaction(BankTransaction at) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(at.getId());
		t.commit();
		s.close();

	}

}
