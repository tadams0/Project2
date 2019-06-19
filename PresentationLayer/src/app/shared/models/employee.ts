import { UserInfo } from './userinfo';

export class Employee{
   private id : number;
   public userInfo : UserInfo;
   public manager : Employee;
   public employeeType : string;
}