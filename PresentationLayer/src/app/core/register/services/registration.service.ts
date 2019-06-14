import { UserInfo } from 'src/app/shared/models/userinfo';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }

  register(user:UserInfo):  Observable<UserInfo> {

    const body = JSON.stringify(user);
    console.log('body: ' + body);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post('http://localhost:8080/Project2/register', body, 
    {headers, withCredentials: true}).pipe(map((resp)=>resp as UserInfo));
  }
}
