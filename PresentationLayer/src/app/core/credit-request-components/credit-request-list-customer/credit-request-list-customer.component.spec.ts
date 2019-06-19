import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditRequestListCustomerComponent } from './credit-request-list-customer.component';

describe('CreditRequestListCustomerComponent', () => {
  let component: CreditRequestListCustomerComponent;
  let fixture: ComponentFixture<CreditRequestListCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreditRequestListCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditRequestListCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
