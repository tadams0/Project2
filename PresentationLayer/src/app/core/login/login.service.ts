import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {UserInfo} from 'c:/Users/andre/Project2/PresentationLayer/src/app/shared/beans/userinfo';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<UserInfo>
  {
    this.http.get("Spring EndPoint");
    return null;
  }

}
