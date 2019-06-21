package com.greenbank.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.BankAccount;
import com.greenbank.beans.BankTransaction;
import com.greenbank.data.BankAccountImpl;
import com.greenbank.data.BankTransactionImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/accounttransaction")
public class BankTransactionController {
	
	@Autowired
	private BankTransactionImpl atHibernate;
	@Autowired
	private BankAccountImpl accountHibernate;
	
	private Logger log = Logger.getLogger(BankTransactionController.class);
	
	@GetMapping("{id}")
	public ArrayList<BankTransaction> getTransactionsByAccountId(@PathVariable("id") int i){
		return atHibernate.getTransactionsByAccountId(i);
	}

}
