package com.greenbank.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.Account;
import com.greenbank.service.AccountService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService = new AccountService();
	
	@GetMapping(value="/account/{customer_id}")
	public ArrayList<Account> getAccountsByCustomerId(@PathVariable("customer_id") int i){
		return accountService.getAccountsByCustomerId(i);
	}
	
	@PostMapping
	public Account addAccount(@RequestBody Account account) {
		Integer i = accountService.addAcount(account);
		return accountService.getAccount(i);
	}
}
