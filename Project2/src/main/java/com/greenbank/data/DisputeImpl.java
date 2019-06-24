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
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.BankTransaction;
import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.CreditLineRequestOption;
import com.greenbank.beans.Dispute;
import com.greenbank.beans.Employee;
import com.greenbank.utils.HibernateUtil;

@Component
public class DisputeImpl implements DisputeDao {
	
	@Autowired
	private HibernateUtil hu;

	@Override
	public int addDispute(Dispute dispute) {
		Session s = hu.getSession();
		org.hibernate.Transaction t = s.beginTransaction();
		int id = 0;
		try {
			id = (int) s.save(dispute);
			t.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			s.close();
		}
		return id;
	}

	@Override
	public Dispute getDisputeById(int i) {
		Session s = hu.getSession();
		Dispute ret = s.get(Dispute.class, i);
		s.close();
		return ret;
	}
	
	@Override
	public Dispute getDisputeByTransactionId(int i) {
		BankTransaction tran = new BankTransaction();
		tran.setId(i);
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<Dispute> query = critBuilder.createQuery(Dispute.class);
		Root<Dispute> root = query.from(Dispute.class);
		query.select(root).where(critBuilder.equal(root.get("transaction"), tran));
		Query<Dispute> q = s.createQuery(query);
		Dispute ret = q.uniqueResult();
		s.close();
		return ret;
	}

	@Override
	public Set<Dispute> getDisputes() {
		Session s = hu.getSession();
		String query = "from Dispute";
		Query<Dispute> q = s.createQuery(query, Dispute.class);
		List<Dispute> disputeList = q.getResultList();
		Set<Dispute> disputeSet = new HashSet<Dispute>();
		disputeSet.addAll(disputeList);
		s.close();
		return disputeSet;
	}
	
	@Override
	public ArrayList<Dispute> getDisputesById(int i) {
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<Dispute> query = critBuilder.createQuery(Dispute.class);
		Root<Dispute> root = query.from(Dispute.class);
		query.select(root).where(critBuilder.equal(root.get("id"), i));
		ArrayList<Dispute> disputeList = (ArrayList<Dispute>) s.createQuery(query).getResultList();
		s.close();
		return disputeList;
	}
	
	@Override
	public ArrayList<Dispute> getDisputesNonPending(){
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<Dispute> query = critBuilder.createQuery(Dispute.class);
		Root<Dispute> root = query.from(Dispute.class);
		query.select(root).where(critBuilder.notLike(root.get("status"), "PENDING"));
		ArrayList<Dispute> disputeList = (ArrayList<Dispute>) s.createQuery(query).getResultList();
		s.close();
		return disputeList;
	}
	
	public ArrayList<Dispute> getDisputesPending(){
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<Dispute> query = critBuilder.createQuery(Dispute.class);
		Root<Dispute> root = query.from(Dispute.class);
		query.select(root).where(critBuilder.like(root.get("status"), "PENDING"));
		ArrayList<Dispute> disputeList = (ArrayList<Dispute>) s.createQuery(query).getResultList();
		s.close();
		System.out.println("This method is called.");
		return disputeList;
	}

	@Override
	public void updateDispute(Dispute dispute) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(dispute.getId());
		t.commit();
		s.close();
	}
	
	@Override
	public int rejectDispute(int idNumber, Employee emp) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		Dispute updateDispute = s.get(Dispute.class, idNumber);
		if(updateDispute.getEmployee() != null) {
			updateDispute.setStatus("MANAGER REJECTED");
		}
//		if("MANAGER".equals(updateDispute.getEmployee().getEmployeeType())) {
//			
//		}
		else {
			updateDispute.setStatus("REJECT");
			updateDispute.setEmployee(emp);
		}
	
		int id = 0;
		try {
			s.update(updateDispute);
			t.commit();
			id = 1;
			System.out.println("Transaction Status is Updated to REJECT");
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
		
		return id;
	}
	
	@Override
	public int approveDispute(int idNumber, Employee emp, CreditLineRequestOption option) {
		Session s = hu.getSession();
		org.hibernate.Transaction t = s.beginTransaction();
		Dispute updateDispute = s.get(Dispute.class, idNumber);
		
		if (updateDispute.getEmployee() == null)
		{ 
			System.out.println("Approving as an employee");
			updateDispute.setStatus("APPROVED");
			updateDispute.setEmployee(emp);
		}
		else {
			System.out.println("Approving as a Manager but entering else statement");
			updateDispute.setStatus("MANAGER APPROVED");
			
		}
		int id = 0;
		try {
			s.update(updateDispute);
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
	public void deleteDispute(Dispute dispute) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		s.update(dispute.getId());
		t.commit();
		s.close();
	}
}
