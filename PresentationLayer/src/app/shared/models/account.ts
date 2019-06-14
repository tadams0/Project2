import { Customer } from './customer';

export class Account{
   private id : number;
   private accountType : string;
   private balance : number;
   private dateOpened : Date;
   private dateClosed : Date;
   private primaryHolder : Customer;

   getId() : number{
      return this.id;
   }

   getAccountType() : string{
      return this.accountType;
   }

   getBalance() : number{
      return this.balance;
   }

   getDateOpened() : Date{
      return this.dateOpened;
   }

   getDateClosed() : Date{
      return this.dateClosed;
   }

   getPrimaryHolder() : Customer{
      return this.primaryHolder;
   }
}