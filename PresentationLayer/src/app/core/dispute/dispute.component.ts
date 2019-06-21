import { Component, OnInit, Input } from '@angular/core';
import { Dispute } from 'src/app/shared/models/dispute';
import { DisputeService } from './services/dispute.service';
import { UserService } from '../login/login.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Transaction } from 'src/app/shared/models/transaction';
import { Customer } from 'src/app/shared/models/customer';

@Component({
  selector: 'app-dispute',
  templateUrl: './dispute.component.html',
  styleUrls: ['./dispute.component.css']
})
export class DisputeComponent implements OnInit {
  @Input() public dispute : Dispute;

  public transaction : Transaction = new Transaction;
  private sub : any;

  constructor(
    private disputeService : DisputeService, 
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
    this.disputeService.addDispute(tempDispute).subscribe((resp)=>{
      console.log(resp);
    });
  }
}
