import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreditLineRequest } from 'src/app/shared/models/CreditLineRequest';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { CreditLineRequestOption } from 'src/app/shared/models/creditlinerequestoption';

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
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post('http://localhost:8080/Project2/creditline', body, 
    {headers, withCredentials: true}).pipe(map((resp)=>resp as CreditLineRequest));
  } 

  sendCreditLineOption(option : CreditLineRequestOption): Observable<String> {
    const body = JSON.stringify(option);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put('http://localhost:8080/Project2/creditline', body, 
    {headers, withCredentials: true}).pipe(map((resp)=>resp as string));
  }

}
