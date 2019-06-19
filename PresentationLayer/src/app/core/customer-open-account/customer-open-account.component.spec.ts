import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerOpenAccountComponent } from './customer-open-account.component';

describe('CustomerOpenAccountComponent', () => {
  let component: CustomerOpenAccountComponent;
  let fixture: ComponentFixture<CustomerOpenAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerOpenAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerOpenAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
