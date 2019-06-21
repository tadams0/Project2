import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/shared/models/employee';
import { UserService } from '../login/login.service';

@Component({
  selector: 'app-customer-rep-home',
  templateUrl: './customer-rep-home.component.html',
  styleUrls: ['./customer-rep-home.component.css']
})
export class CustomerRepHomeComponent implements OnInit {
  public employee : Employee;
  
  constructor(private userService : UserService) { }

  ngOnInit() {
    this.employee = this.userService.getEmployee();
  }

}
