package com.greenbank.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.greenbank.beans.Account;
import com.greenbank.beans.Customer;
import com.greenbank.data.AccountDao;
import com.greenbank.data.hibernate.AccountHibernate;

@Service
public class AccountService {
	
	AccountDao ad = new AccountHibernate();

	public Account getAccount(Account account) {
		return ad.getAccount(account);
	}
	
	public Account getAccountByCustomerID(Customer customer) {
		return ad.getAccountByCustomerId(customer);
	}
	
	public Set<Account> getAccounts(){
		return ad.getAccounts();
	}
	
	public ArrayList<Account> getAccountsByCustomerId(Customer customer){
		return ad.getAccountsByCustomerId(customer);
	}
	
	public void updateAccount(Account account) {
		ad.updateAccount(account);
	}
	
	public void deleteAccount(Account account) {
		ad.deleteAccount(account);
	}
}
