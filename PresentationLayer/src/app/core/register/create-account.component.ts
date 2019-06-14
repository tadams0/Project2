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
  public zip : string;

  constructor(private RegistrationService: RegistrationService) { }

  ngOnInit() {
  this.username = null;
  this.password =null;
  this.firstName = null;
  this.lastName  =null;
  this.email=null;
  this.phoneNumber=null;
  this.address=null;
  this.city=null;
  this.state=null;
  this.country=null;
  this.zip=null;
  }

  submitInfo(): void {
    console.log(this.username);

    this.makeUser();
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
    this.user.password = this.email;
    this.user.phoneNumber = this.phoneNumber;
    this.user.address = this.address;
    this.user.city = this.city;
    this.user.state = this.state;
    this.user.country = this.country;
    this.user.zip = this.zip;
  }

}
