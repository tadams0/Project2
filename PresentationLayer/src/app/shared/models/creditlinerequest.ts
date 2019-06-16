import { Customer } from './customer';
import { Employee } from './employee';

export class CreditLineRequest{
   public id : number;
   public customer : Customer;
   public employeeApprover : Employee;
   public creditAPR : number;
   public creditMax : number;
   public status : string;
}