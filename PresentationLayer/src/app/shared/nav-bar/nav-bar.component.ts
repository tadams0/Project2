import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/core/login/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  title = 'Green Bank Online';

  constructor(private route: Router, private userService: UserService) { }

  ngOnInit() {
    if (!this.userService.isLoggedIn())
    {
      this.route.navigate(['/login'])
    }
  }

  isEmployee(): boolean {
    return this.userService.isEmployee();
  }
  isCustomer(): boolean {
    return this.userService.isCustomer();
  }

  onLogoutPressed(): void {
    
    this.userService.logout().subscribe((resp)=> {

      this.route.navigate(['/login']);
    });
  }
}
