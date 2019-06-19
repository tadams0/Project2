
export class CreditLineRequestOption{
   public id : number;
   public option : string;
   public data : any;

   constructor(id : number, option : string)
   {
      this.id = id;
      this.option = option;
      this.data = null;
   }
}