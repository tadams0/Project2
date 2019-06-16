import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private userService : UserService, private router: Router) { }

  ngOnInit() {
    if (!this.userService.isLoggedIn())
    {
      this.router.navigate(['/login'])
    }
  }

  isCustomer() : boolean{
    return this.userService.isCustomer();
  }

  isEmployee() : boolean {
    return this.userService.isEmployee();
  }

}
