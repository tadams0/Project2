import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest';
import { CreditRequestService } from '../../../shared/services/credit-request.service'; 
import { CreditLineRequestOption } from 'src/app/shared/models/creditlinerequestoption';
 
@Component({
  selector: 'app-credit-request',
  templateUrl: './credit-request.component.html',
  styleUrls: ['./credit-request.component.css']
})
export class CreditRequestComponent implements OnInit {
  @Input() public request : CreditLineRequest;
  @Output() removeCreditLine = new EventEmitter<CreditLineRequest>();

  constructor(private creditService : CreditRequestService) { }

  ngOnInit() {
  }

  onApprove() {
    this.sendCreditLineOption("APPROVE");
  }

  onReject() {
    this.sendCreditLineOption("REJECT");
  }

  private sendCreditLineOption(msg : string) : void
  {
    const option = new CreditLineRequestOption(this.request.id, msg);
    this.creditService.sendCreditLineOption(option).subscribe((simpleMessage)=>{
      if (simpleMessage.message === "S")
      {
        //Succeeded
          this.removeCreditLine.emit(this.request);
      }
      else if (simpleMessage.message === "F")
      {
        //Failure
      }
    });
  }

}
