package com.greenbank.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenbank.beans.Account;
import com.greenbank.beans.Customer;
import com.greenbank.data.hibernate.AccountHibernate;

@Controller
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private AccountHibernate accountHibernate;	
	
	private Logger log = Logger.getLogger(AccountController.class);
	
	@GetMapping
	public Set<Account> getAccounts(HttpSession session){
		log.trace("It was called");
		Set<Account> accounts = accountHibernate.getAccounts();
		return accounts;
	}
	
	
	/*@GetMapping
	public ArrayList<Account> getAccountsByCustomerId(@PathVariable("customer_id") int i){
		System.out.println("getAccountsByCustomerId method called");
		return accountHibernate.getAccountsByCustomerId(i);
	}*/
	
	/*@PostMapping
	public Account addAccount(@RequestBody Account account) {
		Integer i = accountHibernate.addAccount(account);
		return accountHibernate.getAccount(i);
	}*/
}
