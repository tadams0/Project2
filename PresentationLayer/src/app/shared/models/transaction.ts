import { DatePipe } from '@angular/common';

export class Transaction{
   public id : number;
   public account : Account;
   public date : Date;
   public balance : number;
   public name : string;

   public setId(id : number){
      this.id = id;
   }
}