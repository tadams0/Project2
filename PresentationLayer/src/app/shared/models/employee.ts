import { UserInfo } from './userinfo';

export class Employee{
   private id : number;
   private userInfo : UserInfo;
   private manager : Employee;
   private employeeType : string;
}