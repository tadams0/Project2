package com.greenbank.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Customer;
import com.greenbank.beans.UserInfo;
import com.greenbank.utils.HibernateUtil;

@Component
public class CustomerImpl implements CustomerDAO{

	@Autowired
	private HibernateUtil hu;
	
	public CustomerImpl() {
		super();
	}
	
	
	@Override
	public void addCustomer(Customer customer) {
		Session s = hu.getSession();
		org.hibernate.Transaction t = s.beginTransaction();
		int id = 0;
		try {
			System.out.println(customer);
			id = (int) s.save(customer);
			t.commit();
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
	}
	
	@Override
	public Customer getCustomerByInfoId(int userInfoId) {
		System.out.println("Util " + hu);
		Session s = hu.getSession();
		Customer c = null;
		try
		{
			String query = "from Customer c where c.userInfo.id=:id";
			Query<Customer> q = s.createQuery(query, Customer.class);
			q.setParameter("id", userInfoId);
			c = q.getSingleResult();
		}
		catch (Exception e)
		{
			c = null;
		}
		
		return c;
	}
	
	@Override
	public Customer getCustomer(Customer cust) {
		Session s = hu.getSession();
		Customer c;
		
		if(cust.getId()!=0) {
			c = s.get(Customer.class, cust.getId());
		} else {
			String query = "from Customer c where c.username=:username and c.password=:password";
			Query<Customer> q = s.createQuery(query, Customer.class);
			q.setParameter("username", cust.getUsername());
			q.setParameter("password", cust.getPassword());
			c = q.getSingleResult();
		}
		return c;
	}

	@Override
	public Customer getCustomerById(int id) {
		Session s = hu.getSession();
		Customer c;
		
		if(id!=0) {
			c = s.get(Customer.class, id);
		} else {
			String query = "from Customer c where c.id=:id";
			Query<Customer> q = s.createQuery(query, Customer.class);
			q.setParameter("id", id);
			c = q.getSingleResult();
		}
		return c;
	}

	@Override
	public Set<Customer> getCustomers() {
		Session s = hu.getSession();
		String query = "from customer";
		Query<Customer> q = s.createQuery(query, Customer.class);
		List<Customer> custList = q.getResultList();
		Set<Customer> custSet = new HashSet<Customer>();
		custSet.addAll(custList);
		return custSet;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		Session s = hu.getSession();
		 Transaction t = s.beginTransaction();
		 s.delete(customer.getId());
		 t.commit();
		 s.close();		
	}

	@Override
	public void updateCustomer(Customer customer) {
		Session s = hu.getSession();
		 Transaction t = s.beginTransaction();
		 s.update(customer.getId());
		 t.commit();
		 s.close();
	}

}
