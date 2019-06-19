import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreditFormComponent } from './core/creditform/creditform.component';
import { CreditRequestService } from './shared/services/credit-request.service';
import { UserService } from './core/login/login.service';
import { UrlService } from './shared/url.service';
import { CreditRequestComponent } from './core/credit-request-components/credit-request/credit-request.component';
import { CreateAccountComponent } from './core/register/create-account/create-account.component';
import { NavBarComponent } from './shared/nav-bar/nav-bar.component';
import { DisplaybankaccountsComponent } from './core/displaybankaccounts/displaybankaccounts.component';
import { HomeComponent } from './core/home/home.component';
import { LoanofficerhomeComponent } from './core/loanofficerhome/loanofficerhome.component';
import { AccountComponent } from './core/account/account.component';
import { AccountlistComponent } from './core/accountlist/accountlist.component';
import { AccountService } from './core/account/services/account.service';
import { CustomerhomeComponent } from './core/customerhome/customerhome.component';
import { SetUpAccountComponent } from './core/register/set-up-account/set-up-account.component';
import { LoginComponent } from './core/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { TransactionComponent } from './core/transaction/transaction.component';
import { TransactionlistComponent } from './core/transactionlist/transactionlist.component';
import { TransactionService } from './core/transaction/service/transaction.service';
import { AccounttransactionComponent } from './core/accounttransaction/accounttransaction.component';
import { CreditRequestCustomerComponent } from './core/credit-request-components/credit-request-customer/credit-request-customer.component';
import { CreditRequestListCustomerComponent } from './core/credit-request-components/credit-request-list-customer/credit-request-list-customer.component';
import { CreditRequestListComponent } from './core/credit-request-components/credit-request-list/credit-request-list.component';
import { CreditRequestRejectedComponent } from './core/credit-request-components/credit-request-rejected/credit-request-rejected.component';
import { CreditRequestRejectedListComponent } from './core/credit-request-components/credit-request-rejected-list/credit-request-rejected-list.component';
import { CustomerOpenAccountComponent } from './core/customer-open-account/customer-open-account.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreditFormComponent,
    CreditRequestComponent,
    CreditRequestListComponent,
    CreateAccountComponent,
    NavBarComponent,
    DisplaybankaccountsComponent,
    HomeComponent,
    LoanofficerhomeComponent,
    AccountComponent,
    AccountlistComponent,
    CustomerhomeComponent,
    SetUpAccountComponent,
    TransactionComponent,
    TransactionlistComponent,
    AccounttransactionComponent,
    CreditRequestCustomerComponent,
    CreditRequestListCustomerComponent,
    CustomerOpenAccountComponent,
    CreditRequestRejectedComponent,
    CreditRequestRejectedListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    CreditRequestService,
    AccountService,
    TransactionService,
    UserService,
    UrlService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
