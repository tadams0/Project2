import { Component, OnInit } from '@angular/core';
import { CreditRequestService } from '../../../shared/services/credit-request.service';  
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest';
import { UserService } from '../../login/login.service';

@Component({
  selector: 'app-credit-request-list',
  templateUrl: './credit-request-list.component.html',
  styleUrls: ['./credit-request-list.component.css']
})
export class CreditRequestListComponent implements OnInit {
  public requests : CreditLineRequest[];

  constructor(private creditService : CreditRequestService, private userService : UserService) { }

  ngOnInit() {
    const employee = this.userService.getEmployee();
    if (employee)
    {
      if (employee.employeeType === "MANAGER")
      {
        this.creditService.getRequestsForManager().subscribe((requestsIn)=>{
          this.requests = requestsIn;
        })
      }
      else
      {
        this.creditService.getRequestsForAll().subscribe((requestsIn)=>{
          this.requests = requestsIn;
        })
      }
    }
  }
 
  removeRequest(request: CreditLineRequest) {
    this.requests = this.requests.filter(function( obj) 
    {
      return obj.id !== request.id;
    });
  } 

}
