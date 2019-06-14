import { Customer } from './customer';

export class Account{
   private id : number;
   private accountType : string;
   private balance : number;
   private dateOpened : Date;
   private dateClosed : Date;
   private primaryHolder : Customer;
}