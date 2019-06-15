import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserInfo } from 'src/app/shared/models/userinfo';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<UserInfo>
  {

    if ( username && password ) {
      // actually log in
      const body = `username=${username}&password=${password}`;
      const headers = new HttpHeaders({'Content-Type': 'application/json'});

      return this.http.post('http://localhost:8080/Project2/register', body,
      {headers, withCredentials: true}).pipe(map((resp)=>resp as UserInfo));
    }
    /* else {
      // just checking if we are logged in already.
      return this.http.get('http://localhost:8080/Project2/register', {withCredentials: true})
        .pipe( map( resp => {
          const user: CurrentUser = resp as CurrentUser;
          if (user) {
            this.employee = user.employee;
            this.customer = user.customer;
          }
          return user;
        }));
      }
      */
  }
}
