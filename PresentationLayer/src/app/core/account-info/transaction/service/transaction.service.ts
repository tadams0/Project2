import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable } from 'rxjs';
import { Transaction } from 'src/app/shared/models/transaction';
import { map } from 'rxjs/operators';
import { Account } from 'src/app/shared/models/account';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http : HttpClient) { }

  getTransactionsForAccount(account : Account) : Observable<Transaction[]>{
    return this.http.get('http://localhost:8080/Project2/account/'+account.id).pipe(
      map(resp => resp as Transaction[]));
  }

  // getTransactionsWithFilter(account: Account, transactionType: String): Observable<Transaction[]>{
  //   return this.http.get('http://localhost:8080/Project2/accounttransaction/'+account.id+transactionType).pipe(
  //     map(resp => resp as Transaction[]));
  // }
}
