import { Component, OnInit, Input, EventEmitter, Output, ViewChild } from '@angular/core';
import { CreditRequestService } from '../../shared/services/credit-request.service';
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest';
import { Customer } from 'src/app/shared/models/customer';
import { CreditRequestListCustomerComponent } from '../credit-request-components/credit-request-list-customer/credit-request-list-customer.component';

@Component({
  selector: 'app-creditform',
  templateUrl: './credit-form.component.html',
  styleUrls: ['./credit-form.component.css']
})
export class CreditFormComponent implements OnInit {
  public resultText : string;

  @ViewChild(CreditRequestListCustomerComponent, {static: false}) requestListComponent: CreditRequestListCustomerComponent;
  @Output() addCreditLine = new EventEmitter<CreditLineRequest>();
  
  constructor(private creditService : CreditRequestService) { }

  ngOnInit() {
    this.resultText = '';
  }

  onSubmit() {
    this.resultText = 'Submitting a new credit line request, please wait...';
    const req = new CreditLineRequest();
    req.customer = new Customer();
    this.creditService.addRequest(req).subscribe((request)=>{
      if (request.status !== "AUTOREJECT")
      {
        this.resultText = 'A new request has been submitted!';
        this.requestListComponent.addRequest(request);
      }
      else
      {
        this.resultText = 'Sorry, you do not qualify for a credit line at this moment.';
      }
    });
  }
}
