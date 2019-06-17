package com.greenbank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.Customer;
import com.greenbank.beans.Employee;
import com.greenbank.beans.LoginRequestPayload;
import com.greenbank.beans.LoginResponsePayload;
import com.greenbank.beans.SimpleMessage;
import com.greenbank.beans.UserInfo;
import com.greenbank.data.CustomerDAO;
import com.greenbank.data.EmployeeDAO;
import com.greenbank.data.UserInfoDAO;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/login")
public class LoginController {
	@Autowired
	private UserInfoDAO ud;
	
	@Autowired
	private CustomerDAO cd;
	
	@Autowired
	private EmployeeDAO ed;
	
	@RequestMapping(method=RequestMethod.GET)
	public String goLogin(HttpSession session) {
		if(session.getAttribute("user")!=null) {
			return "redirect:account";
		}
		return "static/login.html";
	}
	
	@DeleteMapping
	public SimpleMessage onLogout(HttpSession session)
	{
		System.out.println("Logout processing...");
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		if (payload != null && payload.hasUser())
		{
			session.removeAttribute("user");
			return SimpleMessage.successMessage;
		}
		
		return SimpleMessage.failureMessage;
	}
	
	@PostMapping
	public LoginResponsePayload login(@RequestBody LoginRequestPayload login, HttpSession session) {
		String username = login.getUsername();
		String password = login.getPassword();
		
		LoginResponsePayload payload = new LoginResponsePayload();
		
		UserInfo user = ud.getUser(username, password);
		Customer customer = null;
		Employee employee = null;
		if (user != null)
		{
			customer = cd.getCustomerByInfoId(user.getId());
			if (customer == null)
			{
				employee = ed.getEmployeeByInfoId(user.getId());
			} 
		}
		else
		{
			//No username with that combination!
		}
		if (!"TEMP".equals(customer.getAccountType())) {
			payload.setCustomer(customer);
		}
		payload.setEmployee(employee);
		
		if (payload.hasUser())
			session.setAttribute("user", payload);
		
		return payload;
	}
	
	/*
	 * @PostMapping public String login(@RequestBody Login login, HttpSession
	 * session) { String username = login.getUsername(); String password =
	 * login.getPassword(); System.out.println(login);
	 * System.out.println(username+" "+password); UserInfo user =
	 * ud.getUser(username, password); if(user!=null) { session.setAttribute("user",
	 * user); return "redirect:home"; } return "redirect:login"; }
	 */
}
