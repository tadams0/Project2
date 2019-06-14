package com.greenbank.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.Account;
import com.greenbank.beans.Customer;
import com.greenbank.beans.UserInfo;
import com.greenbank.data.AccountDao;
import com.greenbank.data.CustomerDAO;
import com.greenbank.data.UserInfoDAO;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/register")
public class CreateAccountController {
	
	@Autowired
	private AccountDao accountDAO;

	@Autowired
	private CustomerDAO customerDAO;
	
	@GetMapping
	public String getRequestsAvailableToAll(HttpSession session) {
        return new String("Service Available");
	}

	@PostMapping
	public String addRequest(@RequestBody UserInfo request, String  accountType)
	{
		System.out.println("POST REQUEST RECIEVED!");
		System.out.println(request);

		//create Customer with UserInfo
		Customer newCustomer = new Customer();
		newCustomer.setUserInfo(request);
		customerDAO.addCustomer(newCustomer);
		//create Account tied to new Customer
		Account newAccount = new Account();
		newAccount.setAccountType(accountType);
		newAccount.setPrimaryAccountHolder(newCustomer);
		accountDAO.addAccount(newAccount);
		
		return new String("Creating Account");
	}
}

