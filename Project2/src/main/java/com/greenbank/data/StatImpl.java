package com.greenbank.data;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.Dispute;
import com.greenbank.beans.StatPayload;
import com.greenbank.utils.HibernateUtil;

@Component
public class StatImpl implements StatDAO {

	@Autowired
	private HibernateUtil hu;
	
	@Override
	public StatPayload getGenericStats() {
		ArrayList<CreditLineRequest> requests = null;
		ArrayList<Dispute> disputes = null;
		Session session = hu.getSession();
		String hqlString = "from com.greenbank.beans.CreditLineRequest req";
		Query<CreditLineRequest> query = session.createQuery(hqlString, CreditLineRequest.class);
		requests = new ArrayList<CreditLineRequest>(query.getResultList());

		String hqlStringDisputes = "from com.greenbank.beans.Dispute dis";
		Query<Dispute> queryDisputes = session.createQuery(hqlStringDisputes, Dispute.class);
		disputes = new ArrayList<Dispute>(queryDisputes.getResultList());
		session.close();
		
		StatPayload payload = new StatPayload();
		
		long rejectedCreditLineRequests = 0;
		long approvedCreditLineRequests = 0;
		long rejectedCreditLineRequestsAuto = 0;
		long pendingCreditLineRequests = 0;
		long pendingDisputes = 0;
		long rejectedDisputes = 0;
		long approvedDisputes = 0;

		System.out.println("Before Requests");
		for (int i = 0 ; i < requests.size(); i++)
		{
			switch (requests.get(i).getStatus())
			{
			case "APPROVED":
				approvedCreditLineRequests++;
				break;
			case "REJECTED":
				rejectedCreditLineRequests++;
				break;
			case "AUTOREJECT":
				rejectedCreditLineRequestsAuto++;
				break;
			default:
				pendingCreditLineRequests++;
				break;
			}
		}
		System.out.println("Disputes List: ");
		System.out.println(disputes);
		
		for (int i = 0 ; i < disputes.size(); i++)
		{
			switch (disputes.get(i).getStatus())
			{
			case "REJECT":
				rejectedDisputes++;
				break;
			case "MANAGER APPROVED":
				approvedDisputes++;
				break;
			default:
				pendingDisputes++;
				break;
			}
		}
		
		payload.setApprovedCreditLineRequests(approvedCreditLineRequests);
		payload.setRejectedCreditLineRequests(rejectedCreditLineRequests);
		payload.setRejectedCreditLineRequestsAuto(rejectedCreditLineRequestsAuto);
		payload.setApprovedDisputes(approvedDisputes);
		payload.setRejectedDisputes(rejectedDisputes);
		
		System.out.println("Payload: " + payload);
		return payload;
	}
	

}