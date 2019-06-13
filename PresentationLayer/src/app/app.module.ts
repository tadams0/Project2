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



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreditFormComponent
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
