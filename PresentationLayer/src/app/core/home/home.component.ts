import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/login/login.service';
import { Router } from '@angular/router';
import { Employee } from 'src/app/shared/models/employee';

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

  isLoanOfficer() : boolean {
    if (this.userService.isEmployee())
    {
      const employee : Employee  = this.userService.getEmployee();
      return employee !== null && employee.employeeType === 'LOAN OFFICER';
    }
  }

  isManager() : boolean {
    if (this.userService.getEmployee())
    {
      const employee : Employee  = this.userService.getEmployee();
      return employee !== null && employee.employeeType === 'MANAGER';
    }    
  }

  isRep() : boolean {
    if(this.userService.getEmployee()){
      const employee : Employee = this.userService.getEmployee();
      return employee != null && employee.employeeType === 'CUST REP';
    }
  }
}
