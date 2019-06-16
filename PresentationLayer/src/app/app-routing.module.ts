import { LoginComponent } from './core/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreditFormComponent } from './core/creditform/creditform.component';
import { CreateAccountComponent } from './core/register/create-account.component';
import { CreditRequestListComponent } from './core/credit-request-list/credit-request-list.component';
import { DisplaybankaccountsComponent } from './core/displaybankaccounts/displaybankaccounts.component';
import { HomeComponent } from './core/home/home.component';
import { AccountlistComponent } from './core/accountlist/accountlist.component';


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
    path: 'pendingrequests',
    component: CreditRequestListComponent
  },
  {
    path: 'account',
    component : AccountlistComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
