package com.greenbank.data;

import java.util.ArrayList;
import java.util.Set;

import com.greenbank.beans.Account;
import com.greenbank.beans.Customer;

public interface AccountDao {

	//Create
	public int addAccount(Account account);
	
	//Retrieve
	public Account getAccount(Account account);
	public Account getAccount(int i);
	public Account getAccountByCustomerId(Customer customer);
	public Set<Account> getAccounts();
	public ArrayList<Account> getAccountsByCustomerId(Customer customer);
	ArrayList<Account> getAccountsByCustomerId(int i);
	
	//Update
	public void updateAccount(Account account);
	
	//Delete
	public void deleteAccount(Account account);
	
}
