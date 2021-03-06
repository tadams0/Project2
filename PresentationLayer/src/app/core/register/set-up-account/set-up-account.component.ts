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
  
  public validated: boolean;
  public userInformationBody: String;
  // public show1 = true;
  public loggedUser: LoginResponsePayload;
  public username: string;
  public password: string;
  public newUsername: string;
  public newPassword: string;

  constructor( private setUpService: SetUpService, private router: Router ) { }

  ngOnInit() {
    this.validated = false;
    this.userInformationBody = null;
    this.username = null;
    this.password = null;
    this.newUsername = null;
    this.newPassword = null;
  }

  validate(): void {
    console.log("validating "+this.username+" "+this.password)
    this.setUpService.login(this.username, this.password).subscribe(
      resp => {
        if(resp){
          this.validated = true;
          this.loggedUser = resp;
          this.userInformationBody = JSON.stringify(this.loggedUser.customer.userInfo);
        }
        
        // this.newPassword = this.loggedUser.customer.userInfo.password;
        // this.newUsername = this.loggedUser.customer.userInfo.username;
       
      }
    );
  }

  updateUserPassword(): void{
    console.log("changing "+this.username+" "+this.password+" to "+this.newUsername+" "+this.newPassword);
    this.setUpService.update(this.newUsername, this.newPassword).subscribe(
      resp => {
        if(resp){
          this.loggedUser = resp;
          console.log(this.loggedUser);
          this.userInformationBody = JSON.stringify(this.loggedUser.customer.userInfo);
          this.router.navigate(['/home']);
        }
      }
    );


  }


}
