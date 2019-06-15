import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable } from 'rxjs';
import { Customer } from 'src/app/shared/models/customer';
import { map } from 'rxjs/operators';
import { Account } from 'src/app/shared/models/account';


@Injectable({
  providedIn: 'root'
})
export class GetbankaccountsService {

  constructor(private http: HttpClient) { }

  getAccountsForCustomer(customer : Customer): Observable<Account[]> {
    return this.http.get('http://localhost:8080/Project2/account').pipe(
       map( resp => resp as Account[] ) );
  }
}
