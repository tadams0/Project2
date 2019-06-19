package com.greenbank.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.BankAccount;
import com.greenbank.beans.Customer;
import com.greenbank.beans.LoginResponsePayload;
import com.greenbank.beans.OpenBankAccountRequestPayload;
import com.greenbank.beans.SimpleMessage;
import com.greenbank.data.BankAccountImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/account")
public class BankAccountController {
	
	@Autowired
	private BankAccountImpl bankAccountDAO;	
	
	private Logger log = Logger.getLogger(BankAccountController.class);
	
	
	@GetMapping("{id}")
	public ArrayList<BankAccount> getAccountsByCustomerId(@PathVariable("id") int i){
		System.out.println("getAccountsByCustomerId method called");
		return bankAccountDAO.getAccountsByCustomerId(i);
	}
	
	@PutMapping
	private SimpleMessage updateAccount(@RequestBody OpenBankAccountRequestPayload req, HttpSession session) 
	{
		System.out.println("PUT REQUEST RECIEVED!");
		System.out.println("Opening an Account...");
		System.out.println(req);
		
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		if (payload == null || payload.getCustomer() == null)
		{
			return SimpleMessage.failureMessage;
		}
		
		String type = req.getType();
		
		if("Savings".equals(type) || "Checking".equals(type))
		{
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
			bankAccountDAO.addAccount(newAccount);
		}
			
		return SimpleMessage.successMessage;
	}
}
