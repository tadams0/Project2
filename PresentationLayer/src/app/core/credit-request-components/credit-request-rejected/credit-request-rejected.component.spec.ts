import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditRequestRejectedComponent } from './credit-request-rejected.component';

describe('CreditRequestRejectedComponent', () => {
  let component: CreditRequestRejectedComponent;
  let fixture: ComponentFixture<CreditRequestRejectedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreditRequestRejectedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditRequestRejectedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
