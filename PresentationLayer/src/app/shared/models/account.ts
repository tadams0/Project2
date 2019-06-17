import { Customer } from './customer';

export class Account{
   public id : number;
   public accountType : string;
   public balance : number;
   public dateOpened : Date;
   public dateClosed : Date;
   public primaryHolder : Customer;

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