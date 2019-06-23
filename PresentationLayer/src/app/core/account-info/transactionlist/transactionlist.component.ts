import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../account/services/account.service';
import { Customer } from 'src/app/shared/models/customer';
import { UserInfo } from 'src/app/shared/models/userinfo';
import { Account } from 'src/app/shared/models/account';
import { UserService } from 'src/app/core/login/login.service';
import { TransactionService } from 'src/app/core/account-info/transaction/service/transaction.service';
import { Transaction } from 'src/app/shared/models/transaction';

@Component({
  selector: 'app-transactionlist',
  templateUrl: './transactionlist.component.html',
  styleUrls: ['./transactionlist.component.css']
})
export class TransactionlistComponent implements OnInit {
  public transactionList : Transaction[];
  public filteredList : Transaction[];
  public customer : Customer;
  public account : Account;

  constructor(private transactionService : TransactionService, private userService : UserService) { }

  ngOnInit() {
    this.customer = this.userService.getCustomer();
    this.account = this.userService.getAccount();

    this.transactionService.getTransactionsForAccount(this.account).subscribe((tran)=>{
      this.transactionList = tran;
      this.filterAll();
      console.log(this.transactionList);
    });
  }

  filterAll() {
    this.filteredList = this.transactionList;
  }

  filterCredit() {
    this.filteredList = this.transactionList.filter( function (obj ) {
      return obj.balance > 0;
    });
  }

  filterDebit() {
    this.filteredList = this.transactionList.filter(function (obj){
      return obj.balance < 0;
    });
  }

}
