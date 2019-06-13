import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
// import { UserInfo } from 'src/app/shared/beans/userinfo';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  value1 = '';
  value2 = '';
 // public dude: UserInfo;


  constructor(private loginService: LoginService ) {}
  ngOnInit() {
  }

  onEnter1(value1: string) {
     this.value1 = value1; }

  onEnter2(value2: string) {
     this.value2 = value2; }


     
  // login(){
  //   this.loginService.login(this.value1, this.value2).subscribe(
  //     resp => {
  //       this.dude =  resp;
  //       console.log(this.dude);
  //     });

  // }

  register(){

  }
}
