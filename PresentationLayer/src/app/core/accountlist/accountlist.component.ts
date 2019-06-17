import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account/services/account.service';
import { Customer } from 'src/app/shared/models/customer';
import { UserInfo } from 'src/app/shared/models/userinfo';
import { Account } from 'src/app/shared/models/account';
import { UserService } from 'src/app/core/login/login.service';

@Component({
  selector: 'app-accountlist',
  templateUrl: './accountlist.component.html',
  styleUrls: ['./accountlist.component.css']
})
export class AccountlistComponent implements OnInit {
  public accountList : Account[];
  public customer : Customer;

  constructor(private accountService : AccountService, private userService : UserService) { }

  ngOnInit() {
    this.customer = this.userService.getCustomer();

    this.accountService.getAccountsForCustomer(this.customer).subscribe((acc)=>{
      this.accountList = acc;
    });
  }

}
