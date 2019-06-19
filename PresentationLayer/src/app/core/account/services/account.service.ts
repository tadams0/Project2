import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable } from 'rxjs';
import { Customer } from 'src/app/shared/models/customer';
import { map } from 'rxjs/operators';
import { Account } from 'src/app/shared/models/account';
import { UrlService } from 'src/app/shared/url.service';
import { BankAccount } from '../../customer-open-account/model/BankAccount';


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  private appUrl = this.urlSource.getURL() + '/account';

  constructor(private urlSource: UrlService, private http: HttpClient) { }

  getAccountsForCustomer(customer : Customer): Observable<Account[]> {
    return this.http.get(this.appUrl+'/'+customer.id).pipe(
       map( resp => resp as Account[]));
  }

  addAccountForCustomer(bankAccount: BankAccount){
    const body = bankAccount; 

    return this.http.put(this.appUrl, body,
      {headers: this.headers, withCredentials: true})
      .pipe( map( resp => {
          console.log(resp);
          return resp;
        }
      ));
  }


}
