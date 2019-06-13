package com.greenbank.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.greenbank.beans.Employee;
import com.greenbank.data.EmployeeDAO;
import com.greenbank.utils.HibernateUtil;


@Component
public class EmployeeHibernate implements EmployeeDAO {

	@Autowired
	private HibernateUtil hu;
	private Logger log = Logger.getLogger(CustomerHibernate.class);
	
	@Override
	public void addEmployee(Employee employee) {
		 Session s = hu.getSession();
		 Transaction t = s.beginTransaction();
		 try {
			 s.save(employee);
			 t.commit();
		 }catch(HibernateException e) {
			 t.rollback();
		 }finally {
			 s.close();
		 }
		
	}

	@Override
	public Employee getEmployee(Employee emp) {
		Session s = hu.getSession();
		Employee e;
		if(emp.getId()!=0) {
			e=s.get(Employee.class, emp.getId());
		}else {
			String query = "from employee where e.username=:username and e.user_password=:password";
			Query<Employee> q = s.createQuery(query, Employee.class);
			q.setParameter("username", emp.getUsername());
			q.setParameter("password", emp.getPassword());
			e = q.getSingleResult();
		}
		return e;
	}

	@Override
	public Set<Employee> getEmployees() {
		Session s = hu.getSession();
		String query = "from employee";
		Query<Employee> q = s.createQuery(query, Employee.class);
		List<Employee> empList = q.getResultList();
		Set<Employee> empSet = new HashSet<>();
		empSet.addAll(empList);
		return empSet;
	}

	@Override
	public void deleteEmployee(Employee employee) {
		 Session s = hu.getSession();
		 Transaction t = s.beginTransaction();
		 try {
			 s.delete(employee);
			 t.commit();
		 }catch(HibernateException e) {
			 t.rollback();
		 }finally {
			 s.close();
		 }
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		 Session s = hu.getSession();
		 Transaction t = s.beginTransaction();
		 try {
			 s.save(employee);
			 t.commit();
		 }catch(HibernateException e) {
			 t.rollback();
		 }finally {
			 s.close();
		 }
		 
		
	}

}
