package com.greenbank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenbank.beans.UserInfo;
import com.greenbank.data.UserInfoDAO;


@Controller
@CrossOrigin(origins="*")
@RequestMapping(value="/login")
public class LoginController {
	@Autowired
	private UserInfoDAO ud;
	
	@RequestMapping(method=RequestMethod.GET)
	public String goLogin(HttpSession session) {
		if(session.getAttribute("user")!=null) {
			return "redirect:home";
		}
		return "static/login.html";
	}
	@PostMapping
	public UserInfo login(String username, String password, HttpSession session) {
		System.out.println(username+password);
		UserInfo user = ud.getUser(username, password);
		if(user!=null) {
			session.setAttribute("user", user);
		}
		return user;
	}
}
