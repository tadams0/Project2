package com.greenbank.beans;

import javax.persistence.*;

@Entity
@Table(name="dispute")
public class Dispute {

	@Id
	@Column(name="dispute_id")
	@SequenceGenerator(name="disputeGenerator", sequenceName="DISPUTE_SEQ", allocationSize=1)
	@GeneratedValue(generator="disputeGenerator", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="transaction_id")
	private BankTransaction transaction;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="employee_id")
	private Employee employee; //The approver.
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="status")
	private String status;

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

	public BankTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(BankTransaction transaction) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
				+ employee + ", comments=" + comments + ", status=" + status + "]";
	}
}
