import { UserInfo } from './userinfo';
import { identifierModuleUrl } from '@angular/compiler';

export class Customer{
   private id : number;
   public userInfo : UserInfo;

   getId() : number {
      return this.id;
   }

   setId(id : number){
      this.id = id;
   }
}