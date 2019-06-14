import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './core/login/login.component';
import { CreditFormComponent } from './core/creditform/creditform.component';
import { CreditRequestService } from './core/creditform/services/credit-request.service';
import { UserService } from './shared/user.service';
import { UrlService } from './shared/url.service';
import { CreditRequestComponent } from './core/credit-request/credit-request.component';
import { CreditRequestListComponent } from './core/credit-request-list/credit-request-list.component';
import { CreateAccountComponent } from './core/register/create-account.component';
import { NavBarComponent } from './shared/nav-bar/nav-bar.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreditFormComponent,
    CreditRequestComponent,
    CreditRequestListComponent,
    CreateAccountComponent,
    NavBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    CreditRequestService,
    UserService,
    UrlService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
