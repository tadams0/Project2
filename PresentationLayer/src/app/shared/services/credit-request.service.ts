import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreditLineRequest } from 'src/app/shared/models/CreditLineRequest';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { CreditLineRequestOption } from 'src/app/shared/models/creditlinerequestoption';
import { SimpleMessage } from 'src/app/shared/models/simplemessage';

@Injectable({
  providedIn: 'root'
})
export class CreditRequestService {

  constructor(private http: HttpClient) { 

  }  
  
  getRequestsForAll(): Observable<CreditLineRequest[]> {
    return this.http.get('http://localhost:8080/Project2/creditline',
    {withCredentials: true}).pipe(
       map( resp => resp as CreditLineRequest[] ));
  }

  getRequestsForManager(): Observable<CreditLineRequest[]> {
    return this.http.get('http://localhost:8080/Project2/creditline/managerlines',
    {withCredentials: true}).pipe(
       map( resp => resp as CreditLineRequest[] ));
  }

  getRequestsForCustomer(): Observable<CreditLineRequest[]> {
    return this.http.get('http://localhost:8080/Project2/creditline/0',
    {withCredentials: true}).pipe(
       map( resp => resp as CreditLineRequest[] ));
  }

  getRequestsAutoRejected(): Observable<CreditLineRequest[]> {
    return this.http.get('http://localhost:8080/Project2/creditline/1',
    {withCredentials: true}).pipe(
       map( resp => resp as CreditLineRequest[]));
  }

  addRequest(request : CreditLineRequest): Observable<CreditLineRequest> {
    const body = JSON.stringify(request);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post('http://localhost:8080/Project2/creditline', body, 
    {headers, withCredentials: true}).pipe(map((resp)=>resp as CreditLineRequest));
  }

  sendCreditLineOption(option : CreditLineRequestOption): Observable<SimpleMessage> {
    const body = JSON.stringify(option);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put('http://localhost:8080/Project2/creditline', body, 
    {headers, withCredentials: true}).pipe(map((resp)=>resp as SimpleMessage));
  }

}
