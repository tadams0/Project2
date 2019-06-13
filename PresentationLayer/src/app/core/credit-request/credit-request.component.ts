import { Component, OnInit, Input } from '@angular/core';
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest';
import { CreditRequestService } from '../creditform/services/credit-request.service';

@Component({
  selector: 'app-credit-request',
  templateUrl: './credit-request.component.html',
  styleUrls: ['./credit-request.component.css']
})
export class CreditRequestComponent implements OnInit {
  @Input() public request : CreditLineRequest;

  constructor(private creditService : CreditRequestService) { }

  ngOnInit() {
  }

  onApprove() {

  }

  onReject() {
    
  }

}
