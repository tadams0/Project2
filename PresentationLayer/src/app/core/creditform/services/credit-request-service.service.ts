import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreditLineRequest } from 'src/app/shared/beans/CreditLineRequest';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CreditRequestService {

  constructor(private http: HttpClient) { 

  }  
  
  getRequest(id:number): Observable<CreditLineRequest> {
    return this.http.get('https://localhost:8080/creditline').pipe(
       map( resp => resp as CreditLineRequest ) );
  }
}
