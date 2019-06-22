import { Component, OnInit } from '@angular/core';
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest';
import { CreditRequestService } from 'src/app/shared/services/credit-request.service';
import { UserService } from '../../login/login.service'; 

@Component({
  selector: 'app-credit-request-rejected-list',
  templateUrl: './credit-request-rejected-list.component.html',
  styleUrls: ['./credit-request-rejected-list.component.css']
})
export class CreditRequestRejectedListComponent implements OnInit {
  public requests : CreditLineRequest[];

  constructor(private creditService : CreditRequestService, private userService: UserService) { }

  ngOnInit() {
    this.creditService.getRequestsRejected().subscribe((requestsIn)=>{
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
