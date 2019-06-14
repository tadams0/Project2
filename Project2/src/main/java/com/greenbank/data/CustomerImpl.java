package com.greenbank.data;

import java.util.ArrayList;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Customer;
import com.greenbank.utils.HibernateUtil;

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
		return null;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

}
