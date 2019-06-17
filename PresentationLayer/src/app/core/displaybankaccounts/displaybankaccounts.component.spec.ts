import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplaybankaccountsComponent } from './displaybankaccounts.component';

describe('DisplaybankaccountsComponent', () => {
  let component: DisplaybankaccountsComponent;
  let fixture: ComponentFixture<DisplaybankaccountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplaybankaccountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplaybankaccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
