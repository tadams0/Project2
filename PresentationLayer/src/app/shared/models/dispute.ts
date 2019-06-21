import { Customer } from './customer';
import { Transaction } from './transaction';
import { Employee } from './employee';

export class Dispute{
   private id : number;
   private customer : Customer;
   private transaction : Transaction;
   private employee : Employee; // Approver
   private comments : string;
   private status : string;

   public setId(id : number){
      this.id = id;
   }

   public setCustomer(customer : Customer){
      this.customer = customer;
   }

   public setTransaction(transaction : Transaction){
      this.transaction = transaction;
   }

   public setEmployee(employee : Employee){
      this.employee = employee;
   }

   public setComments(comments : string){
      this.comments = comments;
   }

   public setStatus(status : string){
      this.status = status;
   }
}