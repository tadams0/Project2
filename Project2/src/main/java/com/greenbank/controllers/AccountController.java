package com.greenbank.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenbank.beans.Account;
import com.greenbank.data.AccountDao;

public class AccountController {
	@Autowired
	private AccountDao accountDao;
	
	@RequestMapping("/account")
	public Set<Account> postResponseController (HttpSession session) {
		Set<Account> accountSet = accountDao.getAccounts();
		return accountSet;
	}
}
