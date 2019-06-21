import { Injectable } from '@angular/core';
import { Dispute } from 'src/app/shared/models/dispute';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UrlService } from 'src/app/shared/url.service';

@Injectable({
  providedIn: 'root'
})
export class DisputeService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  private appUrl = this.urlSource.getURL() + '/dispute';

  constructor(private urlSource : UrlService, private http: HttpClient) { }

  addDispute(dispute : Dispute) : Observable<Object>{
    const body = JSON.stringify(dispute);
    console.log('body: ' + body);
    return this.http.post(this.appUrl, body,
    {headers: this.headers, withCredentials: true});
  }

  getDisputesForManager() : Observable<Dispute[]>{
    return this.http.get(this.appUrl,
    {withCredentials: true}).pipe(
      map(resp => resp as Dispute[]));
    }
}
