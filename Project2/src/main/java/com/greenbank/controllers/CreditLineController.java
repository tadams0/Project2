package com.greenbank.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.CreditLineRequestOption;
import com.greenbank.beans.CreditScore;
import com.greenbank.beans.Customer;
import com.greenbank.beans.Employee;
import com.greenbank.beans.LoginResponsePayload;
import com.greenbank.beans.SimpleMessage;
import com.greenbank.data.CreditLineRequestDAO;
import com.greenbank.data.CreditLineRequestImpl;
import com.greenbank.data.CreditScoreDAO;
import com.greenbank.data.CustomerDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/creditline")
public class CreditLineController {
	
	@Autowired
	private CreditLineRequestDAO creditLineDao;
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private CreditScoreDAO creditScoreDao;

	@GetMapping("{id}")
	public ArrayList<CreditLineRequest> getRequestsSpecific(@PathVariable("id") String id, HttpSession session) 
	{
		List<CreditLineRequest> requests = null;
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		
		if (payload == null)
			return null;
		
		boolean isCustomer = payload.getCustomer() != null;
		boolean isEmployee = payload.getEmployee() != null;
		
		if (id != null)
		{ 
			if (isCustomer)
			{
				if (id.equals("0"))
				{
					Customer loggedInCustomer = payload.getCustomer();
			    	requests = creditLineDao.getRequestsByCustomer(loggedInCustomer);
				}
			}
			else if (isEmployee)
			{
				if (id.equals("1"))
				{
			    	requests = creditLineDao.getRequestsAutoRejected();
				}
				else if (id.equals("managerlines"))
				{
					requests = creditLineDao.getRequestsByManager(payload.getEmployee());
				}
			}
		}
		
		if (requests != null)
			return new ArrayList<CreditLineRequest>(requests);
		else
			return null;
	}
	@GetMapping
	public ArrayList<CreditLineRequest> getRequestsAvailableToAll(HttpSession session) 
	{
		List<CreditLineRequest> requests = null;
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		
		if (payload == null)
			return null;
		//Could do a check if this is an employee requesting..
    	requests = creditLineDao.getRequestsAvailableToAll();
    	
		if (requests != null)
			return new ArrayList<CreditLineRequest>(requests);
		else
			return null;
	}
	@PutMapping
	public SimpleMessage optionRequest(@RequestBody CreditLineRequestOption option, HttpSession session)
	{
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		
		if (payload == null || payload.getEmployee() == null)
		{
			return SimpleMessage.failureMessage;
		}
		
		int val = 0;
		if ("REJECT".equals(option.getOption()))
		{
			val = creditLineDao.rejectRequest(option.getId());
		}
		else if ("APPROVE".equals(option.getOption()))
		{
			val = creditLineDao.approveRequest(option.getId(), payload.getEmployee(), option);
		}
		
		return val == 1 ? SimpleMessage.successMessage : SimpleMessage.failureMessage;  //S = success, F = failure.
	}

	@PostMapping
	public CreditLineRequest addRequest(@RequestBody CreditLineRequest request, HttpSession session)
	{
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");

		if (payload.hasUser())
		{
			CreditScore score = creditScoreDao.getCreditScore(payload.getCustomer());
			
			int apr = getAprByCreditScore(score);
			
			request.setCreditAPR(apr);
			request.setCreditMax(apr);
			Customer c = payload.getCustomer();
			request.setCustomer(c); //Get session user here.
			
			if (apr == -1)
			{
				request.setStatus("AUTOREJECT");
			}
			else
			{
				request.setStatus("PENDING");
			}
			
			request.setEmployeeApprover(null);
		}
		else
		{
			//Cannot add a request if no user is logged in.
		}
		if (creditLineDao.addRequest(request) > 0)
			return request;
		else
			return null;
	}
	
	private int getAprByCreditScore(CreditScore score)
	{
		if (score == null)
			return -1;
		
		int scoreVal = score.getCreditScore();
		
		if (scoreVal < 550)
			return -1;
		else if (scoreVal < 600)
			return 27;
		else if (scoreVal < 650)
			return 25;
		else if (scoreVal < 700)
			return 20;
		else if (scoreVal < 750)
			return 15;
		else if (scoreVal < 800)
			return 10;
		else
			return 5;
	}
}
