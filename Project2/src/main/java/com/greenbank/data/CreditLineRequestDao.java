package com.greenbank.data;

import java.util.Set;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Employee;

public interface CreditLineRequestDao {
	Set<CreditLineRequest> getRequestsByManager(Employee manager);
	Set<CreditLineRequest> getRequestsAvailableToAll();
}
