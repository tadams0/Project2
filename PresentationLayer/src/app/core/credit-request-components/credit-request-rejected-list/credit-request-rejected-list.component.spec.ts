import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditRequestRejectedListComponent } from './credit-request-rejected-list.component';

describe('CreditRequestRejectedListComponent', () => {
  let component: CreditRequestRejectedListComponent;
  let fixture: ComponentFixture<CreditRequestRejectedListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreditRequestRejectedListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditRequestRejectedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
