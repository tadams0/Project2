package com.greenbank.beans;

import javax.persistence.*;

@Entity
@Table(name="credit_score")
public class CreditScore {
	@Id
	@Column(name="credit_score_id")
	@SequenceGenerator(name="creditScoreGenerator",sequenceName="CREDIT_SCORE_SEQ",allocationSize=1)
	@GeneratedValue(generator="creditScoreGenerator", strategy=GenerationType.SEQUENCE)
	private int id;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="credit_score_estimation")
	private int creditScore;
	
	public CreditScore() {
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
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditScore;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
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
		CreditScore other = (CreditScore) obj;
		if (creditScore != other.creditScore)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CreditScore [id=" + id + ", customer=" + customer + ", creditScore=" + creditScore + "]";
	}
	
}
