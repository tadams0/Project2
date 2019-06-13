import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreditFormComponent } from './core/creditform/creditform.component';
import { LoginComponent } from './core/login/login.component';

const routes: Routes = [
  {
    path: 'form',
    component: CreditFormComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
