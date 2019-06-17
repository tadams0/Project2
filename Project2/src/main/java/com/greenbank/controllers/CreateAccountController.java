package com.greenbank.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.greenbank.beans.OpenAccountRequestPayload;
import com.greenbank.beans.UserInfo;
import com.greenbank.data.AccountDAO;
import com.greenbank.data.CustomerDAO;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/register")
public class CreateAccountController {
	
	@Autowired
	private AccountDAO accountDao;

	@Autowired
	private CustomerDAO customerDAO;

	@GetMapping
	public String getRequestsAvailableToAll(HttpSession session) {
        return new String("Service Available");
	}

	@PostMapping
	public Map<String, Object> createAccount(@RequestBody OpenAccountRequestPayload openAccount)
	{
		System.out.println("POST REQUEST RECIEVED!");
		System.out.println(openAccount);

		//generates and saves password/username for account
		openAccount.generatePassword();
		String password = openAccount.getUserInfo().getPassword(); 
		openAccount.generateUsername();
		String username = openAccount.getUserInfo().getUsername(); 
		//gotta send the email instead of response body at the end with information 
				
		//create a temp customer account
		//should add field indicating temporary, disallowing to login
		Customer newCustomer = new Customer();
		newCustomer.setUserInfo(openAccount.getUserInfo());
		customerDAO.addCustomer(newCustomer);

		//create savings/checking account
		Account newAccount = new Account();
		//mark with Date
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateOpened = new Date();
		newAccount.setDateOpened(dateOpened);
		//set type
		newAccount.setAccountType(openAccount.getType());
		//setHolder as the temp Customer Account
		newAccount.setPrimaryAccountHolder(newCustomer);
		accountDao.addAccount(newAccount);
		
		
		//response with temp username/password instead of email for testing
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("username", username);
		response.put("password", password);
		return response;
	}
}

