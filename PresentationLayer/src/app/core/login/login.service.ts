import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LoginResponsePayload } from 'src/app/shared/models/loginresponsepayload';
import { Router } from '@angular/router';
import { Employee } from 'src/app/shared/models/employee';
import { Customer } from 'src/app/shared/models/customer';
import { UrlService } from 'src/app/shared/url.service';
import { Account } from 'src/app/shared/models/account';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private appUrl = this.urlSource.getURL() + '/login';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  private employee: Employee;
  private customer: Customer;
  private account : Account;

  constructor( private urlSource: UrlService, private http: HttpClient) { }

  login(username: string, password: string): Observable<LoginResponsePayload> {
    
    if ( username && password ) {
      // actually log in
      const body ={"username": username, "password": password}; // "user=rorr&pass=pswd"

      console.log(this.appUrl +" "+body+" "+
      {headers: this.headers, withCredentials: true});

      return this.http.post(this.appUrl, body,
        {headers: this.headers, withCredentials: true})
        .pipe( map( resp => {
          const user: LoginResponsePayload = resp as LoginResponsePayload;
          if (user) {
            this.employee = user.employee;
            this.customer = user.customer;
          }
          return user;
        }));
    } else {
      // just checking if we are logged in already.
      return this.http.get(this.appUrl, {withCredentials: true})
        .pipe( map( resp => {
          const user: LoginResponsePayload = resp as LoginResponsePayload;
          if (user) {
            this.employee = user.employee;
            this.customer = user.customer;
          }
          return user;
        }));
    }
  }
  
  logout(): Observable<object> {
    return this.http.delete('http://localhost:8080/Project2/login', {withCredentials: true}).pipe(
      map(success => {
        this.employee = null;
        this.customer = null;
        return success;
      })
    );
  }
  getCustomer(): Customer {
    return this.customer;
  }
  getEmployee(): Employee {
    return this.employee;
  }
  getAccount() : Account {
    return this.account;
  }
  setAccount(account : Account){
    this.account = account;
  }
  isEmployee(): boolean {
    return this.employee !== null && this.employee !== undefined && this.employee.employeeType !== 'CUST REP';
  }
  isCustomer(): boolean {
    return this.customer !== null && this.customer !== undefined;
  }
  isManager() : boolean {
    return this.employee && this.employee.employeeType==="MANAGER";
  }
  isCustRep(): boolean {
    // if(this.isEmployee()){
    //   if(this.employee.employeeType === 'CUST REP'){
    //     console.log("This is the customer rep");
    //     return true;
    //   }
    // }
    // return false;
    // console.log("This is the customer rep");
    return this.employee !== null && this.employee !== undefined && this.employee.employeeType === 'CUST REP';
  }

  isLoggedIn() : boolean {
    return (this.customer !== null && this.customer !== undefined) || (this.employee !== null && this.employee !== undefined);
  }

  setPayload(payload : LoginResponsePayload)
  {
    this.employee = payload.employee;
    this.customer = payload.customer;
  }
}
