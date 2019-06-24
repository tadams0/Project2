package com.greenbank.data;

import java.util.ArrayList;
import java.util.Set;

import com.greenbank.beans.BankAccount;
import com.greenbank.beans.Customer;

public interface BankAccountDAO {

	//Create
	public int addAccount(BankAccount account);
	
	//Retrieve
	public BankAccount getAccount(BankAccount account);
	public BankAccount getAccount(int i);
	public BankAccount getAccountByCustomerId(Customer customer);
	public Set<BankAccount> getAccounts();
	public ArrayList<BankAccount> getAccountsByCustomerId(Customer customer);
	ArrayList<BankAccount> getAccountsByCustomerId(int i);
	
	//Update
	public void updateAccount(BankAccount account);
	
	//Delete
	public void deleteAccount(BankAccount account);
}
