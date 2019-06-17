package com.greenbank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.Customer;
import com.greenbank.beans.LoginRequestPayload;
import com.greenbank.beans.LoginResponsePayload;
import com.greenbank.beans.UserInfo;
import com.greenbank.data.CustomerDAO;
import com.greenbank.data.UserInfoDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/update")
public class UpdateAccountController {
	
	@Autowired
	private UserInfoDAO userDAO;
	
	@Autowired
	private CustomerDAO customerDAO;

	@PostMapping
	public LoginResponsePayload login(@RequestBody LoginRequestPayload login) 
	{
		String username = login.getUsername();
		String password = login.getPassword();
		
		LoginResponsePayload payload = new LoginResponsePayload();
		
		UserInfo user = userDAO.getUser(username, password);
		Customer customer = null;
		if (user != null)
		{
			customer = customerDAO.getCustomerByInfoId(user.getId());
			if("TEMP".equals(customer.getAccountType()))
			{
				payload.setCustomer(customer);
				return payload;
			}
		}
		return null;
	}
	
	
	@PutMapping
	private void updateAccount(@RequestBody Customer tempAccount) 
	{
		System.out.println("PUT REQUEST RECIEVED!");
		System.out.println(tempAccount);
		
//		Customer customer = tempAccount.getCustomer();
		tempAccount.setAccountType("PERM");
		customerDAO.updateCustomer(tempAccount);
		
	}
}
