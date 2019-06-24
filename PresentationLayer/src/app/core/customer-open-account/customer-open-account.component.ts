import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account/services/account.service';
import { BankAccount } from './model/BankAccount';

@Component({
  selector: 'app-customer-open-account',
  templateUrl: './customer-open-account.component.html',
  styleUrls: ['./customer-open-account.component.css']
})
export class CustomerOpenAccountComponent implements OnInit {

  private bankAccount: BankAccount;
  public Submitted: String;

  constructor(private accountService : AccountService) { }

  ngOnInit() {
    this.bankAccount = new BankAccount;
  }

  openSavingsAccount(){
    this.popualteBankAccount();

    this.bankAccount.type ="Savings";
    this.accountService.addAccountForCustomer(this.bankAccount).subscribe(
      resp=>{
        this.Submitted = this.bankAccount.type + " Account has been created!";
      }
    )
  }

  openCheckingAccount(){
    this.popualteBankAccount();

    this.bankAccount.type ="Savings";
    this.accountService.addAccountForCustomer(this.bankAccount).subscribe(
      resp=>{
        this.Submitted = this.bankAccount.type + " Account has been created!";
      }
    )
  }

  popualteBankAccount(){
    this.bankAccount.type = '';
    this.bankAccount.version=null;
  }
}
