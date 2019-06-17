import { Component, OnInit, Input } from '@angular/core';
import { TransactionService } from './service/transaction.service';
import { Transaction } from 'src/app/shared/models/transaction';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {
  @Input() public transaction : Transaction;

  constructor(private transactionService : TransactionService) { }

  ngOnInit() {
  }

  disputeTransaction(transactionId : number){
    console.log("Disputing lol");
  }
}
