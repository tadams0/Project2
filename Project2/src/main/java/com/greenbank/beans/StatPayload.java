package com.greenbank.beans;

public class StatPayload {
	private long rejectedCreditLineRequests;
	private long rejectedCreditLineRequestsAuto;
	private long approvedCreditLineRequests;
	
	private long rejectedDisputes;
	private long approvedDisputes;
	
	public StatPayload() {
		super();
	}

	public long getRejectedCreditLineRequests() {
		return rejectedCreditLineRequests;
	}

	public long getRejectedCreditLineRequestsAuto() {
		return rejectedCreditLineRequestsAuto;
	}

	public void setRejectedCreditLineRequestsAuto(long rejectedCreditLineRequestsAuto) {
		this.rejectedCreditLineRequestsAuto = rejectedCreditLineRequestsAuto;
	}

	public long getApprovedCreditLineRequests() {
		return approvedCreditLineRequests;
	}

	public void setApprovedCreditLineRequests(long approvedCreditLineRequests) {
		this.approvedCreditLineRequests = approvedCreditLineRequests;
	}

	public long getRejectedDisputes() {
		return rejectedDisputes;
	}

	public void setRejectedDisputes(long rejectedDisputes) {
		this.rejectedDisputes = rejectedDisputes;
	}

	public long getApprovedDisputes() {
		return approvedDisputes;
	}

	public void setApprovedDisputes(long approvedDisputes) {
		this.approvedDisputes = approvedDisputes;
	}

	public void setRejectedCreditLineRequests(long rejectedCreditLineRequests) {
		this.rejectedCreditLineRequests = rejectedCreditLineRequests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (approvedCreditLineRequests ^ (approvedCreditLineRequests >>> 32));
		result = prime * result + (int) (approvedDisputes ^ (approvedDisputes >>> 32));
		result = prime * result + (int) (rejectedCreditLineRequests ^ (rejectedCreditLineRequests >>> 32));
		result = prime * result + (int) (rejectedCreditLineRequestsAuto ^ (rejectedCreditLineRequestsAuto >>> 32));
		result = prime * result + (int) (rejectedDisputes ^ (rejectedDisputes >>> 32));
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
		StatPayload other = (StatPayload) obj;
		if (approvedCreditLineRequests != other.approvedCreditLineRequests)
			return false;
		if (approvedDisputes != other.approvedDisputes)
			return false;
		if (rejectedCreditLineRequests != other.rejectedCreditLineRequests)
			return false;
		if (rejectedCreditLineRequestsAuto != other.rejectedCreditLineRequestsAuto)
			return false;
		if (rejectedDisputes != other.rejectedDisputes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatPayload [rejectedCreditLineRequests=" + rejectedCreditLineRequests
				+ ", rejectedCreditLineRequestsAuto=" + rejectedCreditLineRequestsAuto + ", approvedCreditLineRequests="
				+ approvedCreditLineRequests + ", rejectedDisputes=" + rejectedDisputes + ", approvedDisputes="
				+ approvedDisputes + "]";
	}
	
	
}
