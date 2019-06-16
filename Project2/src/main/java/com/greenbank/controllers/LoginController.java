package com.greenbank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.Customer;
import com.greenbank.beans.Employee;
import com.greenbank.beans.Login;
import com.greenbank.beans.LoginPayload;
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
	
	@PostMapping
	public LoginPayload login(@RequestBody Login login, HttpSession session) {
		String username = login.getUsername();
		String password = login.getPassword();
		System.out.println(login);
		System.out.println(username+" "+password);
		
		LoginPayload payload = new LoginPayload();
		
		UserInfo user = ud.getUser(username, password);
		Customer customer = cd.getCustomerByInfoId(user.getId());
		Employee employee = null;
		if (customer == null)
		{
			employee = ed.getEmployeeByInfoId(user.getId());
		}
		payload.setCustomer(customer);
		payload.setEmployee(employee);
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
