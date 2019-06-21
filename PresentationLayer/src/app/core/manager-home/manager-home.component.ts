import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/shared/models/employee';
import { UserService } from '../login/login.service';

@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit {
  public employee :Employee;

  constructor(private userService : UserService) { }

  ngOnInit() {
    this.employee = this.userService.getEmployee();
  }

}
