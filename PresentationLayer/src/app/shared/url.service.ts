import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UrlService {
  private static readonly URL = 'http://localhost:8080/GreenBank';
  constructor() { }
  public getURL(): string {
    return UrlService.URL;
  }
}


