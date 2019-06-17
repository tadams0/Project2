import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlService } from 'src/app/shared/url.service';
import { Customer } from 'src/app/shared/models/customer';
import { Observable } from 'rxjs';
import { LoginResponsePayload } from 'src/app/shared/models/loginresponsepayload';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SetUpService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  private loggedUser: LoginResponsePayload;

  constructor (private urlSource: UrlService, private http: HttpClient){}
  
  
    login(username: string, password: string): Observable<LoginResponsePayload> {
      if ( username && password ) {
        const appUrl = this.urlSource.getURL() + '/update';
        const body ={"username": username, "password": password};

        console.log(appUrl +" "+body+" "+
        {headers: this.headers, withCredentials: true});

        return this.http.post(appUrl, body,
          {headers: this.headers, withCredentials: true})
          .pipe( map( resp => {
            const user: LoginResponsePayload = resp as LoginResponsePayload;
            if (user) {
              console.log(user);
              this.loggedUser = user;
            }
            return user;
          }));
    }
  }

  update(username: string, password: string): Observable<LoginResponsePayload> {
    if ( username && password ) {
      const appUrl = this.urlSource.getURL() + '/update';
      
      this.loggedUser.customer.userInfo.username = username;
      this.loggedUser.customer.userInfo.password = password;
      const body = this.loggedUser.customer;

      console.log(appUrl +" "+body+" "+
        {headers: this.headers, withCredentials: true});

        return this.http.put(appUrl, body,
          {headers: this.headers, withCredentials: true})
          .pipe( map( resp => {
            const user: LoginResponsePayload = resp as LoginResponsePayload;
            if (user) {
              this.loggedUser= user;
            }
            return user;
          }));
    }
  }

}
