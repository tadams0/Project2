package com.greenbank.beans;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
	@Id
	@Column(name="account_id")
	@SequenceGenerator(name="accountGenerator",sequenceName="ACCOUNT_SEQ",allocationSize=1)
	@GeneratedValue(generator="accountGenerator", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="balance")
	private double balance;

	//@Column(name = "date_opened")
	//@Temporal(TemporalType.DATE)
	private Date dateOpened;

	//@Column(name = "date_closed")
	//@Temporal(TemporalType.DATE)
	private Date dateClosed;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer primaryAccountHolder;
	
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

	public Customer getPrimaryAccountHolder() {
		return primaryAccountHolder;
	}

	public void setPrimaryAccountHolder(Customer primaryAccountHolder) {
		this.primaryAccountHolder = primaryAccountHolder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateClosed == null) ? 0 : dateClosed.hashCode());
		result = prime * result + ((dateOpened == null) ? 0 : dateOpened.hashCode());
		result = prime * result + id;
		result = prime * result + ((primaryAccountHolder == null) ? 0 : primaryAccountHolder.hashCode());
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
		if (primaryAccountHolder == null) {
			if (other.primaryAccountHolder != null)
				return false;
		} else if (!primaryAccountHolder.equals(other.primaryAccountHolder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", balance=" + balance + ", dateOpened="
				+ dateOpened + ", dateClosed=" + dateClosed + ", primaryAccountHolder=" + primaryAccountHolder + "]";
	}
	
}
