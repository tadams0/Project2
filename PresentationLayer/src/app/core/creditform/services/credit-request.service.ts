import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreditLineRequest } from 'src/app/shared/models/CreditLineRequest';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CreditRequestService {

  constructor(private http: HttpClient) { 

  }  
  
  getRequestsForAll(): Observable<CreditLineRequest[]> {
    return this.http.get('http://localhost:8080/Project2/creditline').pipe(
       map( resp => resp as CreditLineRequest[] ) );
  }

  addRequest(request : CreditLineRequest): Observable<CreditLineRequest> {
    const body = JSON.stringify(request);
    console.log('body: ' + body);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post('http://localhost:8080/Project2/creditline', body, 
    {headers, withCredentials: true}).pipe(map((resp)=>resp as CreditLineRequest));
  } 

}
