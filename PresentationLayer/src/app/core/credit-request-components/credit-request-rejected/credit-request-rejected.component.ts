import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { CreditLineRequestOption } from 'src/app/shared/models/creditlinerequestoption';
import { CreditRequestService } from 'src/app/shared/services/credit-request.service';
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest'; 

@Component({
  selector: 'app-credit-request-rejected',
  templateUrl: './credit-request-rejected.component.html',
  styleUrls: ['./credit-request-rejected.component.css']
})
export class CreditRequestRejectedComponent implements OnInit {
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
      console.log("Simple Message: " + simpleMessage.message);
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
