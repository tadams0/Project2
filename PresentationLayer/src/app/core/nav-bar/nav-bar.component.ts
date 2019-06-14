import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
<<<<<<< HEAD
import { UserService } from 'C:/Users/andre/Project2/PresentationLayer/src/app/shared/user.service';
=======
import { UserService } from 'src/app/shared/user.service';
>>>>>>> 013065532a90eb885157cb13243db06c5fc5966a

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  title = 'Richard\'s BookStore';

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
