package com.greenbank.data;

import com.greenbank.beans.CreditScore;
import com.greenbank.beans.Customer;

public interface CreditScoreDao {
	CreditScore getCreditScore(Customer customer);
	int addCreditScore(CreditScore score);
}
