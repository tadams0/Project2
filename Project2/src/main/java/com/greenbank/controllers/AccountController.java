package com.greenbank.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.Account;
import com.greenbank.data.AccountHibernate;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private AccountHibernate accountHibernate;	
	
	private Logger log = Logger.getLogger(AccountController.class);
	
	
	@GetMapping("{id}")
	public ArrayList<Account> getAccountsByCustomerId(@PathVariable("id") int i){
		System.out.println("getAccountsByCustomerId method called");
		return accountHibernate.getAccountsByCustomerId(i);
	}
}
