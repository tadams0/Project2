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
import com.greenbank.beans.StatPayload;
import com.greenbank.data.CreditLineRequestDAO;
import com.greenbank.data.CreditLineRequestImpl;
import com.greenbank.data.CreditScoreDAO;
import com.greenbank.data.CustomerDAO;
import com.greenbank.data.StatDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/stats")
public class StatsController {
	
	@Autowired
	private StatDAO statDao;

	@GetMapping
	public StatPayload getRequestsAvailableToAll(HttpSession session) 
	{
		StatPayload stats = null;
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		
		if (payload == null)
			return null;

		stats = statDao.getGenericStats();

    	return stats;
	}
	
}
