package com.greenbank.beans;

public class CreditLineRequestOption {
	private int id;
	private String option;
	
	public CreditLineRequestOption() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((option == null) ? 0 : option.hashCode());
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
		CreditLineRequestOption other = (CreditLineRequestOption) obj;
		if (id != other.id)
			return false;
		if (option == null) {
			if (other.option != null)
				return false;
		} else if (!option.equals(other.option))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CreditLineRequestOption [id=" + id + ", option=" + option + "]";
	}
}
