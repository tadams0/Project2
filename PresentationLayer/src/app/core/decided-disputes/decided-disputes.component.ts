import { Component, OnInit } from '@angular/core';
import { Dispute } from 'src/app/shared/models/dispute';
import { DisputeService } from '../dispute/services/dispute.service';
import { UserService } from '../login/login.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-decided-disputes',
  templateUrl: './decided-disputes.component.html',
  styleUrls: ['./decided-disputes.component.css']
})
export class DecidedDisputesComponent implements OnInit {
  public disputes : Dispute[];
  constructor(private disputeService : DisputeService, private userService : UserService) { }

  ngOnInit() {
    this.disputeService.getDisputesForManager().subscribe((disputesIn)=>{
      this.disputes = disputesIn;
    })
    console.log(this.disputes);
  }
}
