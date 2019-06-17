package com.greenbank.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.CreditLineRequestOption;
import com.greenbank.beans.Customer;
import com.greenbank.beans.LoginResponsePayload;
import com.greenbank.beans.SimpleMessage;
import com.greenbank.data.CreditLineRequestDao;
import com.greenbank.data.CreditLineRequestImpl;
import com.greenbank.data.CustomerDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/creditline")
public class CreditLineController {
	
	@Autowired
	private CreditLineRequestDao creditLineDao;
	
	@Autowired
	private CustomerDAO customerDao;

	@GetMapping
	public ArrayList<CreditLineRequest> getRequestsAvailableToAll(HttpSession session) {
    	List<CreditLineRequest> requests = creditLineDao.getRequestsAvailableToAll();
        return new ArrayList<CreditLineRequest>(requests);
	}
	
	@PutMapping
	public SimpleMessage optionRequest(@RequestBody CreditLineRequestOption option, HttpSession session)
	{
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		
		if (payload == null || payload.getEmployee() == null)
		{
			System.out.println("NO EMPLOYEE LOGGED IN!");
			return SimpleMessage.failureMessage;
		}
		
		int val = 0;
		if ("REJECT".equals(option.getOption()))
		{
			val = creditLineDao.rejectRequest(option.getId());
		}
		else if ("APPROVE".equals(option.getOption()))
		{
			val = creditLineDao.approveRequest(option.getId(), payload.getEmployee());
		}
		
		return val == 1 ? SimpleMessage.successMessage : SimpleMessage.failureMessage;  //S = success, F = failure.
	}

	@PostMapping
	public CreditLineRequest addRequest(@RequestBody CreditLineRequest request, HttpSession session)
	{
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		if (payload.hasUser())
		{
			request.setCreditAPR(5);
			request.setCreditMax(15);
			Customer c = payload.getCustomer();
			request.setCustomer(c); //Get session user here.
			request.setStatus("PENDING");
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
}
