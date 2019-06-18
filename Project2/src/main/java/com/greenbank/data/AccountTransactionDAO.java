package com.greenbank.data;

import java.util.ArrayList;
import java.util.Set;

import org.hibernate.Transaction;

import com.greenbank.beans.Account;
import com.greenbank.beans.AccountTransaction;

public interface AccountTransactionDAO {

	//create
	public int addTransaction(AccountTransaction at);
	
	//retrieve
	public AccountTransaction getTransaction(AccountTransaction at);
	public AccountTransaction getTransaction(int i);
	public AccountTransaction getTransactionByAccountId(Account account);
	public ArrayList<AccountTransaction> getTransactionsByAccountId(Account account);
	public ArrayList<AccountTransaction> getTransactionsByAccountId(int i);
	public Set<AccountTransaction> getTransactions();
	
	//update
	public void updateTransaction(AccountTransaction at);
	
	//delete
	public void deleteTransaction(AccountTransaction at);
}
