import { Component, OnInit } from '@angular/core';
import { LoginResponsePayload } from 'src/app/shared/models/loginresponsepayload';
import { Router } from '@angular/router';
import { SetUpService } from '../services/set-up.service';
import { UserInfo } from 'src/app/shared/models/userinfo';

@Component({
  selector: 'app-set-up-account',
  templateUrl: './set-up-account.component.html',
  styleUrls: ['./set-up-account.component.css']
})
export class SetUpAccountComponent implements OnInit {
  
  public userInformation: boolean;
  public userInformationBody: String;
  // public show1 = true;
  public loggedUser: LoginResponsePayload;
  public username: string;
  public password: string;

  constructor( private setUpService: SetUpService, private router: Router ) { }

  ngOnInit() {
    this.userInformation = null;
    this.userInformationBody = null;
    this.username = null;
    this.password = null;
  }

  validate(): void {
    console.log("logging in "+this.username+" "+this.password)
    this.setUpService.login(this.username, this.password).subscribe(
      resp => {
        this.loggedUser = resp;
        this.setUpService.setPayload(resp);
        this.userInformation = true;
        this.userInformationBody = JSON.stringify(this.loggedUser.customer.userInfo);
      }
    );
  }

}
