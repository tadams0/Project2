import { UserInfo } from './userinfo';

export class Customer{
   public id : number;
   public userInfo : UserInfo;

   Customer(){
      this.id = 0;
      this.userInfo = null;
   }

   getId() : number {
      return this.id;
   }

   setId(id : number){
      this.id = id;
   }
}