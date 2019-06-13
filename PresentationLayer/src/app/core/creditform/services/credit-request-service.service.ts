import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreditLineRequest } from 'src/app/shared/beans/CreditLineRequest';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CreditRequestService {

  constructor(private http: HttpClient) { 

  }  
  
  getRequest(id:number): Observable<CreditLineRequest> {
    return this.http.get('https://localhost:8080/Project2/creditline').pipe(
       map( resp => resp as CreditLineRequest ) );
  }

  addRequest(request : CreditLineRequest): Observable<CreditLineRequest> {
    const body = JSON.stringify(request);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post('http://localhost:8080/Project2/creditline', body, {headers, withCredentials: true}).pipe(map((resp)=>resp as CreditLineRequest));
  } 

}
