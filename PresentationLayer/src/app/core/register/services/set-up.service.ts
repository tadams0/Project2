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

  private appUrl = this.urlSource.getURL() + '/update';
  private customer: Customer;

  constructor (private urlSource: UrlService, private http: HttpClient){}
  
  
    login(username: string, password: string): Observable<LoginResponsePayload> {
      if ( username && password ) {
        let headers = new HttpHeaders({'Content-Type': 'application/json'});
        const body ={"username": username, "password": password};

        console.log(this.appUrl +" "+body+" "+
        {headers: headers, withCredentials: true});

        return this.http.post(this.appUrl, body,
          {headers: headers, withCredentials: true})
          .pipe( map( resp => {
            const user: LoginResponsePayload = resp as LoginResponsePayload;
            if (user) {
              this.customer = user.customer;
            }
            return user;
          }));
    }
  }

  update(customer: Customer): Observable<LoginResponsePayload> {
    if (customer) {
      const body = customer; 
      let headers = new HttpHeaders({'Content-Type': 'application/json'});

      console.log(this.appUrl +" "+body+" "+
      {headers: headers, withCredentials: true});

      return this.http.post(this.appUrl, body,
        {headers: headers, withCredentials: true})
        .pipe( map( resp => {
          const user: LoginResponsePayload = resp as LoginResponsePayload;
          if (user) {
            this.customer = user.customer;
          }
          return user;
        }));
  }
  }
  
  setPayload(payload : LoginResponsePayload)
  {
    this.customer = payload.customer;
  }



}
