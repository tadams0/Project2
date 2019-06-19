import { Component, OnInit, Input } from '@angular/core';
import { AccountService } from './services/account.service';
import { UserService } from '../login/login.service';
import { Account } from 'src/app/shared/models/account';
import { Router } from '@angular/router';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  @Input() public account : Account;

  public acc : Account = new Account();

  constructor(private accountService : AccountService, private userService : UserService, private router : Router) { }

  ngOnInit() {
  }

  viewAccounts(accountId : number){
    this.acc.id = accountId;
    this.userService.setAccount(this.acc);
    this.router.navigate(['transactions'])
  }

  viewStatements(accountId:number){
    this.acc.id = accountId;
    this.userService.setAccount(this.acc);
    this.router.navigate(['statements'])
  }

}
