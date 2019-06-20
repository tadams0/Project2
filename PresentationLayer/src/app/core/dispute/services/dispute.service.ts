import { Injectable } from '@angular/core';
import { Dispute } from 'src/app/shared/models/dispute';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DisputeService {

  constructor(private http: HttpClient) { }

  addDispute(dispute : Dispute) : Observable<Object>{
    const body = JSON.stringify(dispute);
    console.log('body: ' + body);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post('http://localhost:8080/Project2/dispute', body,
    {headers, withCredentials: true});
  }
}
