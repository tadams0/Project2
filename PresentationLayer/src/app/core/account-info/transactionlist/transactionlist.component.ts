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
    });
    
  }

  filterCredit() {
    // let creditTransaction: Array<Transaction>;
    // if(this.transactionList.length>0){
    //   for(let i=0; i<this.transactionList.length; i++ ) {
    //     if (this.transactionList[i].getBalance() > 0){
    //       creditTransaction.push(this.transactionList[i]);
    //     }
    //   }
    console.log(this.transactionList);
    this.filteredList = this.transactionList.filter(function(obj) 
    {
      return obj.balance > 0;
    });
    console.log(this.filteredList);
    }
    // return creditTransaction;
  

  filterDebit() {
    this.filteredList = this.transactionList.filter(function(obj) 
    {
      return obj.balance < 0;
    });
    }
  
  filterAll() {
    this.filteredList = this.transactionList.filter(function(obj) 
    {
      return true;
    });
    

  }

  // filterDebit(): Array<Transaction> {
  //   let debitTransaction: Array<Transaction>;
  //   if(this.transactionList.length>0){
  //     for(let i=0; i<this.transactionList.length; i++ ) {
  //       if (this.transactionList[i].getBalance() > 0){
  //         debitTransaction.push(this.transactionList[i]);
  //       }
  //     }
  //   }
  //   return debitTransaction;
  // }

  
}