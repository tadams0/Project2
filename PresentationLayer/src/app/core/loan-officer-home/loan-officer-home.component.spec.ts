import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanOfficerHomeComponent } from './loan-officer-home.component';

describe('LoanOfficerHomeComponent', () => {
  let component: LoanOfficerHomeComponent;
  let fixture: ComponentFixture<LoanOfficerHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoanOfficerHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanOfficerHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
