import { Component, OnInit } from '@angular/core';
import { CreditRequestService } from '../../../shared/services/credit-request.service'; 
import { CreditLineRequest } from 'src/app/shared/models/CreditLineRequest';
import { UserService } from '../../login/login.service';

@Component({
  selector: 'app-credit-request-list-customer',
  templateUrl: './credit-request-list-customer.component.html',
  styleUrls: ['./credit-request-list-customer.component.css']
})
export class CreditRequestListCustomerComponent implements OnInit {
  public requests : CreditLineRequest[];

  constructor(private creditService : CreditRequestService, private userService: UserService) { }

  ngOnInit() {
    this.creditService.getRequestsForCustomer().subscribe((requestsIn)=>{
      this.requests = requestsIn;
    })
  }

  addRequest(request:CreditLineRequest) {
    this.requests.push(request);
  }

}
