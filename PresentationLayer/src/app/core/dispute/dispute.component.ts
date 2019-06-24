import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Dispute } from 'src/app/shared/models/dispute';
import { DisputeService } from './services/dispute.service';
import { CreditLineRequestOption } from 'src/app/shared/models/creditlinerequestoption';

@Component({
  selector: 'app-dispute',
  templateUrl: './dispute.component.html',
  styleUrls: ['./dispute.component.css']
})
export class DisputeComponent implements OnInit {
  @Input() public dispute : Dispute;
  @Output() removeDisputeRequest = new EventEmitter<Dispute>();

  constructor(private disputeService : DisputeService) { }

  ngOnInit() {
  }

  onReject() {
    this.sendDisputeOption("REJECT")
  }

  onApprove(){
    this.sendDisputeOption("APPROVE")
  }

  sendDisputeOption(msg: string) : void {
    const option = new CreditLineRequestOption(this.dispute.id, msg);
    this.disputeService.sendDisputeOption(option).subscribe((simpleMessage)=>{
      if (simpleMessage.message === "S")
      {
        
          this.removeDisputeRequest.emit(this.dispute);
  
      }
     
    });
  }
}
