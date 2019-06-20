import { LoginComponent } from './core/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreditFormComponent } from './core/creditform/creditform.component';
import { CreateAccountComponent } from './core/register/create-account/create-account.component';
import { CreditRequestListComponent } from './core/credit-request-components/credit-request-list/credit-request-list.component';
import { DisplaybankaccountsComponent } from './core/displaybankaccounts/displaybankaccounts.component';
import { HomeComponent } from './core/home/home.component';
import { AccountlistComponent } from './core/accountlist/accountlist.component';
import { SetUpAccountComponent } from './core/register/set-up-account/set-up-account.component';
import { TransactionlistComponent } from './core/account-info/transactionlist/transactionlist.component';
import { AccountInfoComponent } from './core/account-info/account-info.component';
import { CreditRequestListCustomerComponent } from './core/credit-request-components/credit-request-list-customer/credit-request-list-customer.component';
import { CreditRequestRejectedListComponent } from './core/credit-request-components/credit-request-rejected-list/credit-request-rejected-list.component';
import { DisputeComponent } from './core/dispute/dispute.component';


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
    path: 'pendingrequests',
    component: CreditRequestListComponent
  },
  {
    path: 'autorejects',
    component: CreditRequestRejectedListComponent
  },
  {
    path: 'transactions',
    component : AccountInfoComponent
  },
  {
    path: 'disputing/:transactionId',
    component : DisputeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
