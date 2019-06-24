import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../../login/login.service';
import { StatementService } from './service/statement.service';
import { Account } from 'src/app/shared/models/account';

@Component({
  selector: 'app-statement',
  templateUrl: './statement.component.html',
  styleUrls: ['./statement.component.css']
})
export class StatementComponent implements OnInit {

  @Input() index: number;
  public account : Account;
  public displayDates = [];
  public date: string;
  private dates = [];
  
  constructor(private statementService: StatementService, private userService : UserService) { }

  

  ngOnInit() {
    let n =12;
    let index =n;
    let months = [];

    months[0] = "January";
    months[1] = "February";
    months[2] = "March";
    months[3] = "April";
    months[4] = "May";
    months[5] = "June";
    months[6] = "July";
    months[7] = "August";
    months[8] = "September";
    months[9] = "October";
    months[10] = "November";
    months[11] = "December";

    let date = new Date();
    while(index --> 0){

      let month = months[date.getMonth()];
      let  y =date.getFullYear();
      this.displayDates.push(y+" "+month);      
      date.setMonth(date.getMonth() - 1);
      this.dates.push(new Date(date));
    }
    console.log(this.dates);
  }

  getStatement(index:number){
    this.account = this.userService.getAccount();
    this.statementService.getSatementsForAccount(this.account, this.dates[index]).subscribe((statement)=>{
      console.log(statement);
    });
  }
}
