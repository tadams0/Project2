import { UserInfo } from 'src/app/shared/models/userinfo';
import { RegistrationService } from './services/registration.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  private user = new UserInfo;
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

  
  this.username = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);;
  this.password = "password";
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

  submitInfo(): void {
    this.makeUser();
    let accountType = "Checking";
    console.log(this.user);
    this.RegistrationService.register(this.user).subscribe(
      resp => {
        
      }
    );
  }

  makeUser(){
    this.user.username = this.username;
    this.user.password = this.password;
    this.user.firstName = this.firstName;
    this.user.lastName = this.lastName;
    this.user.email = this.email;
    this.user.phoneNumber = this.phoneNumber;
    this.user.address = this.address;
    this.user.city = this.city;
    this.user.state = this.state;
    this.user.country = this.country;
    this.user.zipcode = this.zipcode;
  }

}
