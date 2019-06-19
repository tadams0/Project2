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
import com.greenbank.beans.Customer;
import com.greenbank.data.BankAccountDAO;
import com.greenbank.utils.HibernateUtil;

@Component
public class BankAccountImpl implements BankAccountDAO {
	
	@Autowired
	private HibernateUtil hu;
	
	private Logger log = Logger.getLogger(BankAccountImpl.class);

	@Override
	public int addAccount(BankAccount account) {
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
	public BankAccount getAccount(BankAccount account) {
		Session s = hu.getSession();
		BankAccount ret = s.get(BankAccount.class, account.getId());
		s.close();
		return ret;
	}
	
	@Override
	public BankAccount getAccount(int i) {
		Session s = hu.getSession();
		BankAccount ret = s.get(BankAccount.class, i);
		s.close();
		return ret;
	}

	@Override
	public BankAccount getAccountByCustomerId(Customer customer) {
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<BankAccount> query = critBuilder.createQuery(BankAccount.class);
		Root<BankAccount> root = query.from(BankAccount.class);
		query.select(root).where(critBuilder.equal(root.get("customer_id"), customer.getId()));
		Query<BankAccount> q = s.createQuery(query);
		s.close();
		return q.getSingleResult();
	}
	
	@Override
	public ArrayList<BankAccount> getAccountsByCustomerId(Customer customer){
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<BankAccount> query = critBuilder.createQuery(BankAccount.class);
		Root<BankAccount> root = query.from(BankAccount.class);
		query.select(root).where(critBuilder.equal(root.get("id"), customer.getId()));
		ArrayList<BankAccount> accountList = (ArrayList<BankAccount>) s.createQuery(query).getResultList();
		s.close();
		return accountList;
	}
	
	@Override
	public ArrayList<BankAccount> getAccountsByCustomerId(int i){
		Customer customer = new Customer();
		customer.setId(i);
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<BankAccount> query = critBuilder.createQuery(BankAccount.class);
		Root<BankAccount> root = query.from(BankAccount.class);
		query.select(root).where(critBuilder.equal(root.get("primaryAccountHolder"), customer));
		ArrayList<BankAccount> accountList = (ArrayList<BankAccount>) s.createQuery(query).getResultList();
		s.close();
		return accountList;
	}

	@Override
	public Set<BankAccount> getAccounts() {
		Session s = hu.getSession();
		String query = "from Account";
		log.trace(query);
		Query<BankAccount> q = s.createQuery(query, BankAccount.class);
		List<BankAccount> accountList = q.getResultList();
		Set<BankAccount> accountSet = new HashSet<BankAccount>();
		accountSet.addAll(accountList);
		log.trace(accountList);
		s.close();
		return accountSet;
	}

	@Override
	public void updateAccount(BankAccount account) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(account.getId());
		t.commit();
		s.close();
	}

	@Override
	public void deleteAccount(BankAccount account) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(account.getId());
		t.commit();
		s.close();
	}

}
