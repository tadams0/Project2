import { Injectable } from '@angular/core';
import { UrlService } from './url.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Employee } from './beans/employee';
import { Customer } from './beans/customer';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CurrentUser } from './beans/currentuser';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private appUrl = this.urlSource.getURL() + '/login';
  private headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
  private employee: Employee;
  private customer: Customer;

  constructor( private urlSource: UrlService, private http: HttpClient ) { }

  login(username: string, password: string): Observable<CurrentUser> {
    
    if ( username && password ) {
      // actually log in
      const body = `user=${username}&pass=${password}`; // "user=rorr&pass=pswd"

      console.log(this.appUrl +" "+body+" "+
      {headers: this.headers, withCredentials: true});

      return this.http.post(this.appUrl, body,
        {headers: this.headers, withCredentials: true})
        .pipe( map( resp => {
          const user: CurrentUser = resp as CurrentUser;
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
          const user: CurrentUser = resp as CurrentUser;
          if (user) {
            this.employee = user.employee;
            this.customer = user.customer;
          }
          return user;
        }));
    }
  }

  
  logout(): Observable<object> {
    return this.http.delete(this.appUrl, {withCredentials: true}).pipe(
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
  isEmployee(): boolean {
    return (this.employee !== undefined && this.employee !== null);
  }
  isCustomer(): boolean {
    return (this.customer !== undefined && this.customer !== null);
  }
}
