import { Component, OnInit, Input } from '@angular/core';
import { CreditRequestService } from '../../shared/services/credit-request.service';
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest';
import { Customer } from 'src/app/shared/models/customer';

@Component({
  selector: 'app-creditform',
  templateUrl: './creditform.component.html',
  styleUrls: ['./creditform.component.css']
})
export class CreditFormComponent implements OnInit {
  public resultText : string;
  constructor(private creditService : CreditRequestService) { }

  ngOnInit() {
    this.resultText = '';
  }

  onSubmit() {
    this.resultText = 'Submitting a new credit line request, please wait...';
    console.log('Submit pressed!');
    const req = new CreditLineRequest();
    req.customer = new Customer();
    this.creditService.addRequest(req).subscribe((request)=>{
      this.resultText = 'A new request has been submitted!';
    });
  }
}
