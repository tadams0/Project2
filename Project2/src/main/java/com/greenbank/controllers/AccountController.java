package com.greenbank.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.Account;
import com.greenbank.service.AccountService;

@RestController
@CrossOrigin(origins="http://localhost:8080")
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService = new AccountService();
	
	//@RequestMapping("/account")
	public Set<Account> postResponseController (HttpSession session) {
		Set<Account> accountSet = accountService.getAccounts();
		return accountSet;
	}
}
