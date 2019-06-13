import { Component, OnInit } from '@angular/core';
import { CurrentUser } from 'src/app/shared/beans/currentuser';
import { UserService } from 'src/app/shared/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loggedUser: CurrentUser;
  public username: string;
  public password: string;
  constructor( private userService: UserService ) { }

  ngOnInit() {
    
  }

  login(username: string, password: string): void {
    this.username = username;
    this.password = password;
    console.log("logging in "+this.username+" "+this.password)
    this.userService.login(this.username, this.password).subscribe(
      resp => {
        this.loggedUser = resp;
      }
    );
  }

  logout(): void {
    this.userService.logout().subscribe();
    this.loggedUser = null;
    this.username = null;
    this.password = null;
  }

  // onEnter1(value1: string) {
  //    this.value1 = value1; }

  // onEnter2(value2: string) {
  //    this.value2 = value2; }


     
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
