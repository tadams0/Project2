package com.greenbank.data;

import java.util.ArrayList;
import java.util.Set;

import com.greenbank.beans.Dispute;

public interface DisputeDao {

	//create
	public int addDispute(Dispute dispute);
	
	//retrieve
	public Dispute getDisputeById(int i);
	public Dispute getDisputeByTransactionId(int i);
	public Set<Dispute> getDisputes();
	public ArrayList<Dispute> getDisputesById(int i);
	public ArrayList<Dispute> getDisputesNonPending();
	public ArrayList<Dispute> getDisputesPending();
	
	//update
	public void updateDispute(Dispute dispute);
	
	//delete
	public void deleteDispute(Dispute dispute);
}
