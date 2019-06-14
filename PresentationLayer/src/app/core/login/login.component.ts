import { Component, OnInit } from '@angular/core';
import { CurrentUser } from 'src/app/shared/models/currentuser';
import { UserService } from 'src/app/shared/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loggedUser: CurrentUser;
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
      }
    );
  }

  logout(): void {
    this.userService.logout().subscribe();
    this.loggedUser = null;
    this.username = null;
    this.password = null;
  }

  register() {
    this.router.navigate(['./register']);
  }
}
