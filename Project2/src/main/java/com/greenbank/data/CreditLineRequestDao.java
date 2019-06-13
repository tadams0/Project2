package com.greenbank.data;

import java.util.List;
import java.util.Set;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Employee;

public interface CreditLineRequestDao {
	List<CreditLineRequest> getRequestsByManager(Employee manager);
	List<CreditLineRequest> getRequestsByManagerID(int id);
	List<CreditLineRequest> getRequestsAvailableToAll();
	int addRequest(CreditLineRequest req);
}
