import { Customer } from './customer';
import { Transaction } from './transaction';
import { Employee } from './employee';

export class Dispute{
   private id : number;
   private customer : Customer;
   private transaction : Transaction;
   private employee : Employee; // Approver
   private comments : string;
}