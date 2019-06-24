import { Component, OnInit, Input } from '@angular/core';
import { Dispute } from 'src/app/shared/models/dispute';
import { DisputeService } from '../dispute/services/dispute.service';
import { Transaction } from 'src/app/shared/models/transaction';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../login/login.service';
import { Customer } from 'src/app/shared/models/customer';

@Component({
  selector: 'app-dispute-submit',
  templateUrl: './dispute-submit.component.html',
  styleUrls: ['./dispute-submit.component.css']
})
export class DisputeSubmitComponent implements OnInit {
  @Input() public dispute : Dispute;

  public transaction : Transaction = new Transaction;
  private sub : any;

  constructor(private disputeService : DisputeService, 
    private router : Router, 
    private activeRoute : ActivatedRoute,
    private userService : UserService) { }

  ngOnInit() {
    this.sub = this.activeRoute.params.subscribe(params => {
      this.transaction.setId(+params['transactionId']);
    });
  }

  disputeTransaction(comment : string){
    let tempDispute : Dispute = new Dispute
    let customer : Customer = this.userService.getCustomer();
    tempDispute.setCustomer(customer);
    tempDispute.setTransaction(this.transaction);
    tempDispute.setComments(comment);
    tempDispute.setStatus("PENDING");
    this.disputeService.addDispute(tempDispute).subscribe((resp)=>{
      console.log(resp);
      this.router.navigate(['transactions']);
    });
  }

}
