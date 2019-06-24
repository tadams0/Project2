import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
  }
  
  viewStatements(){
    console.log("routing to statements");
    this.router.navigate(['statements'])
  }
}
