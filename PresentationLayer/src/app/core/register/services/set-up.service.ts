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

  private appUrl = this.urlSource.getURL() + '/login';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  private customer: Customer;

  constructor (private urlSource: UrlService, private http: HttpClient){}
  
  
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
