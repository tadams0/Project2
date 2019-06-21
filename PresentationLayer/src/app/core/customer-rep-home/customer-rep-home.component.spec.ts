import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerRepHomeComponent } from './customer-rep-home.component';

describe('CustomerRepHomeComponent', () => {
  let component: CustomerRepHomeComponent;
  let fixture: ComponentFixture<CustomerRepHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerRepHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerRepHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
