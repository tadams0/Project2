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
import com.greenbank.beans.AccountTransaction;
import com.greenbank.data.AccountHibernate;
import com.greenbank.data.AccountTransactionHibernate;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/accounttransaction")
public class AccountTransactionController {
	
	@Autowired
	private AccountTransactionHibernate atHibernate;
	@Autowired
	private AccountHibernate accountHibernate;
	
	private Logger log = Logger.getLogger(AccountTransactionController.class);
	
	@GetMapping("{id}")
	public ArrayList<AccountTransaction> getTransactionsByAccountId(@PathVariable("id") int i){
		return atHibernate.getTransactionsByAccountId(i);
	}

}
