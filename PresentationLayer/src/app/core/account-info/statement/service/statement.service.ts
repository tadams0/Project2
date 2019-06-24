import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Account } from 'src/app/shared/models/account';
import { UrlService } from 'src/app/shared/url.service';


@Injectable({
  providedIn: 'root'
})
export class StatementService {

  private appUrl = this.urlSource.getURL() + '/accountstatement';
  private headers = new HttpHeaders();


  constructor( private urlSource: UrlService, private http : HttpClient) { }

  // getSatementsForAccount(account : Account) : Observable<Statement>{

  //   return this.http.get('http://localhost:8080/Project2/statement/'+account.id, {withCredentials: true}).pipe(
  //     map(resp => resp as Statement));
  // }
  getSatementsForAccount(account : Account, date: Date){

    const year = date.getFullYear();
    const month = date.getMonth()+1;
    const body ={};
    //logging
    console.log(this.appUrl + '/' + account.id + '/' + year + "/" + month, 
    {responseType: 'arraybuffer', withCredentials: true});

    //request
    return this.http.get(this.appUrl + '/' + account.id + '/' + year + "/" + month, 
    {responseType: 'arraybuffer', withCredentials: true}).pipe(
      map(resp =>{

        var file = new Blob([resp], {type: 'application/pdf'});
        var fileURL = URL.createObjectURL(file);
        window.open(fileURL); 
      }));
  }
}
