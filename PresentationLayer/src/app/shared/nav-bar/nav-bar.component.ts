import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  title = 'Green Bank Online';

  constructor( public route: Router, private userService: UserService) { }

  ngOnInit() {
  }

  isEmployee(): boolean {
    console.log('e? ' + this.userService.isEmployee());
    return this.userService.isEmployee();
  }
  isCustomer(): boolean {
    console.log('c? ' + this.userService.isCustomer());
    return this.userService.isCustomer();
  }

}