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
import com.greenbank.data.CreditLineRequestDao;
import com.greenbank.data.CreditLineRequestImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@RequestMapping(value="/creditline")
public class CreditLineController {
	@Autowired
	private CreditLineRequestDao creditLineDao;
	   //@Autowired
	    //ExampleService exampleService;

		//@RequestMapping(method=RequestMethod.GET)
	@RequestMapping("/creditline")
	public ArrayList<CreditLineRequest> postResponseController(HttpSession session) {
	    	List<CreditLineRequest> requests = creditLineDao.getRequestsAvailableToAll();
	        return new ArrayList<CreditLineRequest>(requests); //requests.get(0);
	     }
	
	public CreditLineRequest addRequest(@RequestBody CreditLineRequest request)
	{
		if (creditLineDao.addRequest(request) > 0)
			return request;
		else
			return null;
	}
}
