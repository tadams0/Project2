package com.greenbank.data.hibernate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.Customer;
import com.greenbank.data.CustomerDAO;
import com.greenbank.utils.HibernateUtil;

//@Component
public class CustomerHibernate implements CustomerDAO{

	@Autowired
	private HibernateUtil hu;
	private Logger log = Logger.getLogger(CustomerHibernate.class);
	
	@Override
	public void addCustomer(Customer customer) {
		 Session s = hu.getSession();
		 Transaction t = s.beginTransaction();
		 try {
			 s.save(customer);
			 t.commit();
		 }catch(HibernateException e) {
			 t.rollback();
		 }finally {
			 s.close();
		 }
		
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
	
}
