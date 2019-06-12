package com.greenbank.beans;

import java.util.Date;

public class Account {
	private int id;
	private String accountType;
	private double balance;
	private Date dateOpened;
	private Date dateClosed;
	private Customer customer;
	
	public Account() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}
	public Date getDateClosed() {
		return dateClosed;
	}
	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dateClosed == null) ? 0 : dateClosed.hashCode());
		result = prime * result + ((dateOpened == null) ? 0 : dateOpened.hashCode());
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
		Account other = (Account) obj;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (dateClosed == null) {
			if (other.dateClosed != null)
				return false;
		} else if (!dateClosed.equals(other.dateClosed))
			return false;
		if (dateOpened == null) {
			if (other.dateOpened != null)
				return false;
		} else if (!dateOpened.equals(other.dateOpened))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", balance=" + balance + ", dateOpened="
				+ dateOpened + ", dateClosed=" + dateClosed + ", customer=" + customer + "]";
	}
	
	
}
