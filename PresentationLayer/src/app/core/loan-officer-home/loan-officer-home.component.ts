import { Component, OnInit } from '@angular/core';
import { UserService } from '../login/login.service';
import { Employee } from 'src/app/shared/models/employee';

@Component({
  selector: 'app-loan-officer-home',
  templateUrl: './loan-officer-home.component.html',
  styleUrls: ['./loan-officer-home.component.css']
})
export class LoanOfficerHomeComponent implements OnInit {
  public employee :Employee;

  constructor(private userService : UserService) 
  { 
    
  }

  ngOnInit() {
    this.employee = this.userService.getEmployee();
  }

}
