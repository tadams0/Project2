package com.greenbank.data;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.CreditLineRequest;
import com.greenbank.beans.StatPayload;
import com.greenbank.utils.HibernateUtil;

@Component
public class StatImpl implements StatDAO {

	@Autowired
	private HibernateUtil hu;
	
	@Override
	public StatPayload getGenericStats() {
		ArrayList<CreditLineRequest> requests = null;
		Session session = hu.getSession();
		String hqlString = "from com.greenbank.beans.CreditLineRequest req";
		Query<CreditLineRequest> query = session.createQuery(hqlString, CreditLineRequest.class);
		requests = new ArrayList<CreditLineRequest>(query.getResultList());
		session.close();
		
		StatPayload payload = new StatPayload();
		
		long rejectedCreditLineRequests = 0;
		long approvedCreditLineRequests = 0;
		long rejectedCreditLineRequestsAuto = 0;
		long pendingCreditLineRequests = 0;
		
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
		
		payload.setApprovedCreditLineRequests(approvedCreditLineRequests);
		payload.setRejectedCreditLineRequests(rejectedCreditLineRequests);
		payload.setRejectedCreditLineRequestsAuto(rejectedCreditLineRequestsAuto);
		return payload;
	}
	

}
