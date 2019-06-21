import { Component, OnInit } from '@angular/core';
import { UserService } from '../login/login.service';
import { DisputeService } from '../dispute/services/dispute.service';
import { Dispute } from 'src/app/shared/models/dispute';

@Component({
  selector: 'app-dispute-pending-list',
  templateUrl: './dispute-pending-list.component.html',
  styleUrls: ['./dispute-pending-list.component.css']
})
export class DisputePendingListComponent implements OnInit {
  public disputes : Dispute[];
  constructor(private userService : UserService, private disputeService : DisputeService) { }

  ngOnInit() {
    this.disputeService.getDisputesForRep().subscribe((disputePending)=> {
      this.disputes = disputePending;
    });
  }

}
