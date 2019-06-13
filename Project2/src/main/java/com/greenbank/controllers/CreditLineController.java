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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Customer;
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

	@PostMapping
	public CreditLineRequest addRequest(@RequestBody CreditLineRequest request)
	{
		System.out.println("");
		System.out.println("POST REQUEST RECIEVED!");
		System.out.println(request);
		System.out.println("");
		request.setCreditAPR(5);
		request.setCreditMax(15);
		Customer c = customerDao.getCustomerById(1);
		System.out.println("Found customer: " + c);
		request.setCustomer(c); //Get session user here.
		request.setEmployeeApprover(null);
		if (creditLineDao.addRequest(request) > 0)
			return request;
		else
			return null;
	}
}
