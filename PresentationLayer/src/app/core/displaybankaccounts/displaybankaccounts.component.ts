import { Component, OnInit } from '@angular/core';
import { GetbankaccountsService } from './services/getbankaccounts.service';
import { Account } from 'src/app/shared/models/account';
import { Customer } from 'src/app/shared/models/customer';
import { UserInfo } from 'src/app/shared/models/userinfo';

@Component({
  selector: 'app-displaybankaccounts',
  templateUrl: './displaybankaccounts.component.html',
  styleUrls: ['./displaybankaccounts.component.css']
})
export class DisplaybankaccountsComponent implements OnInit {
  public userinfo : UserInfo;
  public cust : Customer; 
  public accounts : Account[];
  constructor(private accountService : GetbankaccountsService) { }

  ngOnInit() {
    this.cust.setId(1);
    this.cust.userInfo=this.userinfo;
  }

  loadAccounts() {
    this.accountService.getAccountsForCustomer(this.cust).subscribe(resultArray => this.accounts = resultArray,
    error => console.log("Error :: " + error));
    this.accounts.forEach(function (account){
      this.displayAccount(account);
    });
  }

  displayAccount(account: Account){
    let table = document.getElementById("bankaccounts");
    let tr = document.createElement("tr");
    this.addTableDef(account.getAccountType(), tr);
    this.addTableDef(account.getBalance(), tr);

    //view button
    let td = document.createElement("td");
    let btn = document.createElement("button");
    tr.appendChild(td);
    tr.appendChild(btn);
    btn.innerHTML="View"
    btn.className="btn btn-secondary cust-btn";
    btn.id="c_b_"+account.getId();
    btn.addEventListener("click", this.viewAccount);

    table.appendChild(tr);

  }

  viewAccount(){

  }

  addTableDef(value, tr) {
    let td = document.createElement("td");
    td.innerHTML = value;
    tr.appendChild(td);
}

  addListToTable(tr, list, parser){
    let td = document.createElement("td");
    let ul = document.createElement("ul");
    for (let i = 0; i< list.length; i++){
        let li = document.createElement("li");
        li.innerHTML=parser(list[i]);
        ul.appendChild(li);
    }
    td.appendChild(ul);
    tr.appendChild(td);
  }


}
