package com.greenbank.beans;

public class CreditLineRequest {
	private int id;
	private Customer customer;
	private Employee employee;
	private int creditAPR;
	private int creditMax;
	public CreditLineRequest() {
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getCreditAPR() {
		return creditAPR;
	}
	public void setCreditAPR(int creditAPR) {
		this.creditAPR = creditAPR;
	}
	public int getCreditMax() {
		return creditMax;
	}
	public void setCreditMax(int creditMax) {
		this.creditMax = creditMax;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditAPR;
		result = prime * result + creditMax;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + id;
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
		CreditLineRequest other = (CreditLineRequest) obj;
		if (creditAPR != other.creditAPR)
			return false;
		if (creditMax != other.creditMax)
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
		return true;
	}
	@Override
	public String toString() {
		return "CreditLineRequest [id=" + id + ", customer=" + customer + ", employee=" + employee + ", creditAPR="
				+ creditAPR + ", creditMax=" + creditMax + "]";
	}
	
}
