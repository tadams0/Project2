import { Component, OnInit } from '@angular/core';
import { CurrentUser } from 'src/app/shared/models/currentuser';
import { UserService } from 'src/app/shared/user.service';
import { Router } from '@angular/router';
import { LoginPayload } from 'src/app/shared/models/loginpayload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loggedUser: LoginPayload;
  public username: string;
  public password: string;
  constructor( private userService: UserService, private router: Router ) { }

  ngOnInit() {
    
  }

  login(username: string, password: string): void {
    this.username = username;
    this.password = password;
    console.log("logging in "+this.username+" "+this.password)
    this.userService.login(this.username, this.password).subscribe(
      resp => {
        this.loggedUser = resp;
        this.userService.setPayload(resp);
        this.router.navigate(['/home'])
      }
    );
  }

  logout(): void {
    this.userService.logout().subscribe();
    this.loggedUser = null;
    this.username = null;
    this.password = null;
  }

  openAccount() {
    this.router.navigate(['./register']);
  }
  setUpAccount(){
    this.router.navigate(['./register']);
  }
}
