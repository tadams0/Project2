package com.greenbank.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.BankAccount;
import com.greenbank.beans.Customer;
import com.greenbank.beans.CreateAccountRequestPayload;
import com.greenbank.beans.UserInfo;
import com.greenbank.data.BankAccountDAO;
import com.greenbank.data.CustomerDAO;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/register")
public class CreateCustomerAccountController {
	
	@Autowired
	private BankAccountDAO accountDao;

	@Autowired
	private CustomerDAO customerDAO;

	@GetMapping
	public String getRequestsAvailableToAll(HttpSession session) {
        return new String("Service Available");
	}

	@PostMapping
	public Map<String, Object> createCustomerAccount(@RequestBody CreateAccountRequestPayload req)
	{
		System.out.println("POST REQUEST RECIEVED!");
		System.out.println(req);

		//generates and saves password/username for account
		req.generatePassword();
		String password = req.getUserInfo().getPassword(); 
		req.generateUsername();
		String username = req.getUserInfo().getUsername(); 
		//gotta send the email instead of response body at the end with information 
				
		//create a temp customer account
		//should add field indicating temporary, disallowing to login
		Customer newCustomer = new Customer();
		newCustomer.setUserInfo(req.getUserInfo());
		newCustomer.setAccountType("TEMP");
		customerDAO.addCustomer(newCustomer);

		//create savings/checking account
		BankAccount newAccount = new BankAccount();
		//mark with Date
		//perhaps move this into BankAccount class
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateOpened = new Date();
		newAccount.setDateOpened(dateOpened);
		//set account type
		newAccount.setAccountType(req.getType());
		//setHolder as the temp Customer Account
		newAccount.setPrimaryAccountHolder(newCustomer);
		accountDao.addAccount(newAccount);
		
		//comments for testing
		System.out.println("----------------------------------");
		System.out.println(username);
		System.out.println(password);
		

		try {
			sendEmail(newCustomer.getUserInfo().getEmail(), username, password);
		} catch (Exception e) {
			System.out.println("Email Sending Failure");
			e.printStackTrace();
		}
		
		//response with temp username/password for testing
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("username", username);
		response.put("password", password);
		return response;
	}
	
	private void sendEmail(String email, String username, String password) throws MessagingException {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		mailSender.setJavaMailProperties(properties);
		
		mailSender.setUsername("greenbanktheonlybank@gmail.com");
		mailSender.setPassword("GreenBankPassword!");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		String text = "<html><body>"
				+ "<p>Your Username: "+username+"</p>"
				+ "<p>Your Password: "+password+"</p>"
				+ "<p>Thank you for using GreenBank!</p>"
				+ "</body></html>";
		
		helper.setFrom("GreenBank");
		helper.setSubject("GreenBank Account Registration");
		helper.setText(text, true); // true to activate multipart
		helper.addTo(email);
		
//		ByteArrayDataSource dataSource = new ByteArrayDataSource(inputStream, "text/plain");
//		helper.addAttachment("file.txt", dataSource);
		
		mailSender.send(message);

	}
}

