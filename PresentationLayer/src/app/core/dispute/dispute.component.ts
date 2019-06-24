import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Dispute } from 'src/app/shared/models/dispute';
import { DisputeService } from './services/dispute.service';
import { CreditLineRequestOption } from 'src/app/shared/models/creditlinerequestoption';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dispute',
  templateUrl: './dispute.component.html',
  styleUrls: ['./dispute.component.css']
})
export class DisputeComponent implements OnInit {
  @Input() public dispute : Dispute;
  @Output() removeDisputeRequest = new EventEmitter<Dispute>();

  constructor(private disputeService : DisputeService, private router: Router) { }

  ngOnInit() {
  }

  onManagerApprove(){
    this.sendDisputeOption("MANAGER APPROVED");
    this.router.navigate(['disputed']);
  }

  onManagerReject(){
    this.sendDisputeOption("MANAGER REJECTED");
    this.router.navigate(['disputed']);
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
