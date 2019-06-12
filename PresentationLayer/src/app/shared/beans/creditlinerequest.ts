import { Customer } from './customer';
import { Employee } from './employee';

export class CreditLineRequest{
   private id : number;
   private customer : Customer;
   private employee : Employee;
   private creditAPR : number;
   private creditMax : number;
}