package com.greenbank.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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



@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/register")
public class CreateAccountController {
	
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CustomerDAO customerDAO;

	@GetMapping
	public String getRequestsAvailableToAll(HttpSession session) {
        return new String("Service Available");
	}

	@PostMapping
	public String addUser(@RequestBody UserInfo user)
	{
		System.out.println("POST REQUEST RECIEVED!");
		System.out.println(user);

		//create Customer with UserInfo
		Customer newCustomer = new Customer();
		newCustomer.setUserInfo(user);
		customerDAO.addCustomer(newCustomer);
		//create Account tied to new Customer
		Account newAccount = new Account();
		//mark with Date
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateOpened = new Date();
		System.out.println(df.format(dateOpened));
		//newAccount.setAccountType(accountType);
		newAccount.setAccountType("Checking");
		newAccount.setDateOpened(dateOpened);
		newAccount.setPrimaryAccountHolder(newCustomer);
		accountDao.addAccount(newAccount);
		
		return new String("Creating Account");
	}
}

