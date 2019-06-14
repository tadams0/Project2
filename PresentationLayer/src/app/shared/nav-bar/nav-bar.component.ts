import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
<<<<<<< HEAD:PresentationLayer/src/app/core/nav-bar/nav-bar.component.ts
import { UserService } from 'C:/Users/andre/Project2/PresentationLayer/src/app/shared/user.service';
=======
import { UserService } from 'src/app/shared/user.service';
>>>>>>> f5fcfa7e819649e7062b1a589acfe0c35ec4feec:PresentationLayer/src/app/shared/nav-bar/nav-bar.component.ts

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
    return this.userService.isEmployee();
  }
  isCustomer(): boolean {
    return this.userService.isCustomer();
  }

}
