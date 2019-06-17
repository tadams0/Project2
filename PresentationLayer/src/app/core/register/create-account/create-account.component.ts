import { UserInfo } from 'src/app/shared/models/userinfo';
import { RegistrationService } from '../services/registration.service';
import { Component, OnInit } from '@angular/core';
import { CreateAccountPayload } from '../model/createaccountpayload';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  private userInfo = new UserInfo;
  private createAccountPayload = new CreateAccountPayload;
  public username: string;
  public password: string;
  public firstName: string;
  public lastName: string;
  public email: string;
  public phoneNumber : string;
  public address : string;
  public  city : string;
  public state : string;
  public country : string;
  public zipcode : string;

  constructor(private RegistrationService: RegistrationService) { }

  ngOnInit() {
  this.username = null;
  this.password = null;
  this.firstName = null;
  this.lastName  = null;
  this.email = null;
  this.phoneNumber = null;
  this.address = null;
  this.city = null;
  this.state = null;
  this.country = null;
  this.zipcode = null;

  
  // this.username = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);;
  // this.password = "password";
  this.firstName = "lalalallala";
  this.lastName  = "lelellelelelee";
  this.email = "lolll@lolol.lol";
  this.phoneNumber = "12345";
  this.address = "dawdaw";
  this.city = "awdwad";
  this.state = "dwawa";
  this.country = "awdawds";
  this.zipcode = "1234";
  
  }

  // submitChecking(): void {
  //   let accountType = "Checking";
  //   this.makeUser();
  //   this.makePayload(accountType);

  //   console.log(this.createAccountPayload);
  //   this.RegistrationService.register(this.createAccountPayload).subscribe(
  //     resp => {
        
  //     }
  //   );
  // }

  submitChecking(): void {
    let accountType = "Checking";
    this.makeUser();
    this.makePayload(accountType);

    console.log(this.createAccountPayload);
    this.RegistrationService.register(this.createAccountPayload).subscribe(
      resp=> console.log(resp)
    );
  }

  submitSavings(): void {
    let accountType = "Savings";
    this.makeUser();
    this.makePayload(accountType);

    console.log(this.createAccountPayload);
    this.RegistrationService.register(this.createAccountPayload).subscribe(
      resp => {
        
      }
    );
  }

  makeUser(){
    this.userInfo.username = this.username;
    this.userInfo.password = this.password;
    this.userInfo.firstName = this.firstName;
    this.userInfo.lastName = this.lastName;
    this.userInfo.email = this.email;
    this.userInfo.phoneNumber = this.phoneNumber;
    this.userInfo.address = this.address;
    this.userInfo.city = this.city;
    this.userInfo.state = this.state;
    this.userInfo.country = this.country;
    this.userInfo.zipcode = this.zipcode;
  }

  makePayload(accountType:String){
    this.createAccountPayload.userInfo = this.userInfo;
    this.createAccountPayload.type = accountType;
  }
}
