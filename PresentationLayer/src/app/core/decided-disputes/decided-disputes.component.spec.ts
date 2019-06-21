import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DecidedDisputesComponent } from './decided-disputes.component';

describe('DecidedDisputesComponent', () => {
  let component: DecidedDisputesComponent;
  let fixture: ComponentFixture<DecidedDisputesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecidedDisputesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecidedDisputesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
