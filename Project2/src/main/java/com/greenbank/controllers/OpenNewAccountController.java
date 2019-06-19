package com.greenbank.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.BankAccount;
import com.greenbank.beans.Customer;
import com.greenbank.beans.LoginResponsePayload;
import com.greenbank.beans.OpenBankAccountRequestPayload;
import com.greenbank.beans.SimpleMessage;
import com.greenbank.data.BankAccountDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/openaccount")
public class OpenNewAccountController {
	
	@Autowired
	private BankAccountDAO accountDao;
	

	@PutMapping
	private SimpleMessage updateAccount(@RequestBody OpenBankAccountRequestPayload req, HttpSession session) 
	{
		System.out.println("PUT REQUEST RECIEVED!");
		System.out.println("Opening an Account...");
		
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		if (payload == null || payload.getCustomer() == null)
		{
			return SimpleMessage.failureMessage;
		}
		//Open BankAccount
		//mark with Date
		BankAccount newAccount = new BankAccount();
		Customer customer = payload.getCustomer();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateOpened = new Date();
		newAccount.setDateOpened(dateOpened);
		//set account type
		newAccount.setAccountType(req.getType());
		//setHolder as the temp Customer Account
		newAccount.setPrimaryAccountHolder(customer);
		accountDao.addAccount(newAccount);
		
		return SimpleMessage.successMessage;
	}

}
