import { Component, OnInit, Input } from '@angular/core';
import { AccountService } from './services/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  @Input() public account : Account;

  constructor(private accountService : AccountService) { }

  ngOnInit() {
  }

  veiwAccount(){
    console.log("Viewing lol");
  }


}
