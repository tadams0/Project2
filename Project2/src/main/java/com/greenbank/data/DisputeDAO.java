package com.greenbank.data;

import java.util.ArrayList;
import java.util.Set;

import com.greenbank.beans.Dispute;

public interface DisputeDAO {

	//create
	public int addDispute(Dispute dispute);
	
	//retrieve
	public Dispute getDispute(Dispute dispute);
	public Dispute getDisputeById(int i);
	public Set<Dispute> getDisputes(Dispute dispute);
	public ArrayList<Dispute> getDisputesById(int i);
	
	//update
	public void updateDispute(Dispute dispute);
	
	//delete
	public void deleteDispute(Dispute dispute);
}
