package com.greenbank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenbank.beans.Login;
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
			return "redirect:account";
		}
		return "static/login.html";
	}
	@PostMapping
	public String login(@RequestBody Login login, HttpSession session) {
		String username = login.getUsername();
		String password = login.getPassword();
		System.out.println(login);
		System.out.println(username+" "+password);
		UserInfo user = ud.getUser(username, password);
		if(user!=null) {
			session.setAttribute("user", user);
			return "redirect:home";
		}
		return "redirect:login";
	}
}
