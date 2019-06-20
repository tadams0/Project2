package com.greenbank.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.beans.BankTransaction;
import com.greenbank.beans.Dispute;
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
	public Set<Dispute> getDisputesForManager(HttpSession session){
		return disputeImpl.getDisputes();
	}
	
	@PostMapping
	public int addDispute(@RequestBody Dispute dispute) {
		System.out.println("add Dispute started");
		BankTransaction transaction = btImpl.getTransaction(dispute.getTransaction().getId());
		System.out.println(transaction);
		dispute.setTransaction(transaction);
		int id = disputeImpl.addDispute(dispute);
		System.out.println("--------------------------id\t"+id);
		return id;
	}
}
