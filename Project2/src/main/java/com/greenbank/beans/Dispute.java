package com.greenbank.beans;

import javax.persistence.*;

//@Entity
//@Table(name="dispute")
public class Dispute {

	//@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="disputes")
	//@SequenceGenerator(name="disputes", sequenceName="DISPUTE_SEQ", allocationSize=1)
	//@Column(name="dispute_id")
	private int id;
	
	private Customer customer;
	private AccountTransaction transaction;
	private Employee employee; //The approver.
	private String comments;
	
	public Dispute() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public AccountTransaction getTransaction() {
		return transaction;
	}
	public void setTransaction(AccountTransaction transaction) {
		this.transaction = transaction;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + id;
		result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dispute other = (Dispute) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id != other.id)
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Dispute [id=" + id + ", customer=" + customer + ", transaction=" + transaction + ", employee="
				+ employee + ", comments=" + comments + "]";
	}
	
}
