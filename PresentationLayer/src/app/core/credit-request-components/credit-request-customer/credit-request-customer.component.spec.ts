import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditRequestCustomerComponent } from './credit-request-customer.component';

describe('CreditRequestCustomerComponent', () => {
  let component: CreditRequestCustomerComponent;
  let fixture: ComponentFixture<CreditRequestCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreditRequestCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditRequestCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
