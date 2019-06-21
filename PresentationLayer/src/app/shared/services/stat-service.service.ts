import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { StatPayload } from '../models/statpayload';

@Injectable({
  providedIn: 'root'
})
export class StatServiceService {
  private payload : StatPayload = null;
  constructor(private http: HttpClient) { }
  
  getStatPayload(): Observable<StatPayload> {
    return this.http.get('http://localhost:8080/Project2/stats',
    {withCredentials: true}).pipe(
       map( resp => resp as StatPayload ));
  }

  getStatPayloadCache() : StatPayload {
    return this.payload;
  }

  cacheStatPayload(payload : StatPayload) {
    this.payload = payload;
  }

  clearCache() {
    this.payload = null;
  }
}
