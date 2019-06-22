import { Component, OnInit } from '@angular/core';
import { UserService } from '../login/login.service';
import { DisputeService } from '../dispute/services/dispute.service';
import { Dispute } from 'src/app/shared/models/dispute';
import { CreditLineRequestOption } from 'src/app/shared/models/creditlinerequestoption';

@Component({
  selector: 'app-dispute-pending-list',
  templateUrl: './dispute-pending-list.component.html',
  styleUrls: ['./dispute-pending-list.component.css']
})
export class DisputePendingListComponent implements OnInit {
  public disputes : Dispute[];
  
  constructor(private userService : UserService, private disputeService : DisputeService) { }

  ngOnInit() {
    const employee = this.userService.getEmployee();
    if (employee)
    {
      if (employee.employeeType === "MANAGER")
      {
        this.disputeService.getDisputesForManager().subscribe((disputePending)=>{
          this.disputes = disputePending;
        });
      }else{
        this.disputeService.getDisputesForRep().subscribe((disputePending)=> {
          this.disputes = disputePending;
        });
      }
    
  }
}


  removeDispute(disputed: Dispute) {
    this.disputes = this.disputes.filter(function( obj) 
    {
      return obj.id !== disputed.id;
    });
  } 
}
