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
		
		ArrayList<Customer> requests = null;
		Session session = hu.getSession();
		String hqlString = "from com.greenbank.beans.CreditLineRequest req where req.id=:id";
		Query<Customer> query = session.createQuery(hqlString, Customer.class);
        query.setParameter(":id", id);
		requests = new ArrayList<Customer>(query.getResultList());
		return requests.get(0);
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
