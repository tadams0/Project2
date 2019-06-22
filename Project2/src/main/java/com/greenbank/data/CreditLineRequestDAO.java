package com.greenbank.data;

import java.util.List;
import java.util.Set;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.CreditLineRequestOption;
import com.greenbank.beans.Customer;
import com.greenbank.beans.Employee;

public interface CreditLineRequestDAO {
	List<CreditLineRequest> getRequestsByManager(Employee manager);
	List<CreditLineRequest> getRequestsByManagerID(int id);
	List<CreditLineRequest> getRequestsAvailableToAll();
	int addRequest(CreditLineRequest req);
	int rejectRequest(int requestID);
	List<CreditLineRequest> getRequestsByCustomer(Customer customer);
	List<CreditLineRequest> getRequestsAutoRejected();
	int approveRequest(int requestID, Employee loggedInEmployee, CreditLineRequestOption option);
	long getTotalNumberOfRequests();
	List<CreditLineRequest> getAllRejectedRequests();
}
