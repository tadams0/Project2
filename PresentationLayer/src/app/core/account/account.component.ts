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

  viewAccount(accountid : number){
    this.acc.id = accountid;
    this.userService.setAccount(this.acc);
    this.router.navigate(['transactions'])
  }


}
