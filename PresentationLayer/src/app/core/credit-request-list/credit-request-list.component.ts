import { Component, OnInit } from '@angular/core';
import { CreditRequestService } from '../creditform/services/credit-request.service';
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest';

@Component({
  selector: 'app-credit-request-list',
  templateUrl: './credit-request-list.component.html',
  styleUrls: ['./credit-request-list.component.css']
})
export class CreditRequestListComponent implements OnInit {
  public requests : CreditLineRequest[];

  constructor(private creditService : CreditRequestService) { }

  ngOnInit() {
    this.creditService.getRequestsForAll().subscribe((requestsIn)=>{
      this.requests = requestsIn;
    })
  }

  removeRequest(request: CreditLineRequest) {
    this.requests = this.requests.filter(function( obj) 
    {
      return obj.id !== request.id;
    });
  } 

}
