package com.greenbank.data;

import java.util.ArrayList;
import java.util.Set;

import org.hibernate.Transaction;

import com.greenbank.beans.BankAccount;
import com.greenbank.beans.BankTransaction;

public interface AccountTransactionDAO {

	//create
	public int addTransaction(BankTransaction at);
	
	//retrieve
	public BankTransaction getTransaction(BankTransaction at);
	public BankTransaction getTransaction(int i);
	public BankTransaction getTransactionByAccountId(BankAccount account);
	public ArrayList<BankTransaction> getTransactionsByAccountId(BankAccount account);
	public ArrayList<BankTransaction> getTransactionsByAccountId(int i);
	public Set<BankTransaction> getTransactions();
	
	
	//update
	public void updateTransaction(BankTransaction at);
	
	//delete
	public void deleteTransaction(BankTransaction at);
}
