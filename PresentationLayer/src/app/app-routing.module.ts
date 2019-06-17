import { LoginComponent } from './core/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreditFormComponent } from './core/creditform/creditform.component';
import { CreateAccountComponent } from './core/register/create-account/create-account.component';
import { CreditRequestListComponent } from './core/credit-request-list/credit-request-list.component';
import { DisplaybankaccountsComponent } from './core/displaybankaccounts/displaybankaccounts.component';
import { HomeComponent } from './core/home/home.component';
import { AccountlistComponent } from './core/accountlist/accountlist.component';
import { SetUpAccountComponent } from './core/register/set-up-account/set-up-account.component';
import { TransactionlistComponent } from './core/transactionlist/transactionlist.component';
import { AccounttransactionComponent } from './core/accounttransaction/accounttransaction.component';
import { CreditRequestListCustomerComponent } from './core/credit-request-list-customer/credit-request-list-customer.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'form',
    component: CreditFormComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: CreateAccountComponent
  },
  {
    path: 'setup',
    component: SetUpAccountComponent
  },
  {
    path: 'myrequests',
    component: CreditRequestListCustomerComponent
  },
  {
    path: 'pendingrequests',
    component: CreditRequestListComponent
  },
  {
    path: 'transactions',
    component : AccounttransactionComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
