import { Component, OnInit, Input } from '@angular/core';
import { CreditLineRequest } from 'src/app/shared/models/CreditLineRequest';
import { CreditRequestService } from '../../shared/services/credit-request.service';

@Component({
  selector: 'app-credit-request-customer',
  templateUrl: './credit-request-customer.component.html',
  styleUrls: ['./credit-request-customer.component.css']
})
export class CreditRequestCustomerComponent implements OnInit {
  @Input() public request : CreditLineRequest;

  constructor(private creditService : CreditRequestService) { }

  ngOnInit() {
  }

}
