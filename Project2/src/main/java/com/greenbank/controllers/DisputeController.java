package com.greenbank.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.BankTransaction;
import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Dispute;
import com.greenbank.beans.LoginResponsePayload;
import com.greenbank.data.BankTransactionImpl;
import com.greenbank.data.DisputeImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/dispute")
public class DisputeController {

	@Autowired
	private DisputeImpl disputeImpl;
	@Autowired
	private BankTransactionImpl btImpl;
	
	@GetMapping
	public ArrayList<Dispute> getDisputesForManager(HttpSession session){
		List<Dispute> requests = null;
		LoginResponsePayload payload = (LoginResponsePayload)session.getAttribute("user");
		System.out.println(payload.getEmployee().getEmployeeType());
//		boolean isEmployee = payload.getEmployee() != null;
		boolean isCustRep = "CUST REP".equals(payload.getEmployee().getEmployeeType());
		System.out.println(isCustRep);
		if(isCustRep) {
			System.out.println("This is Customer Representative");
			return disputeImpl.getDisputesPending();
		}else {
			System.out.println("This is manager.");
			return disputeImpl.getDisputesNonPending();
		}
		
	}
	
//	@GetMapping
//	public ArrayList<Dispute> getDisputeForRep(HttpSession session){
//		return disputeImpl.getDisputesPending();
//	}
	
	@PostMapping
	public int addDispute(@RequestBody Dispute dispute) {
		BankTransaction transaction = btImpl.getTransaction(dispute.getTransaction().getId());
		dispute.setTransaction(transaction);
		Dispute tempDis = disputeImpl.getDisputeByTransactionId(dispute.getTransaction().getId());
		if (tempDis == null)
			return disputeImpl.addDispute(dispute);
		else
			return 0;
	}
	
}
