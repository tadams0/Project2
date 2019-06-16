import { Component, OnInit, Input } from '@angular/core';
import { CreditLineRequest } from 'src/app/shared/models/creditlinerequest';
import { CreditRequestService } from '../creditform/services/credit-request.service';
import { CreditLineRequestOption } from 'src/app/shared/models/creditlinerequestoption';

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
      }
      else if (simpleMessage.message === "F")
      {
        //Failure
      }
    });
  }

}
