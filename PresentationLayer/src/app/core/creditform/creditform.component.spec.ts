import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditformComponent } from './creditform.component';

describe('CreditformComponent', () => {
  let component: CreditformComponent;
  let fixture: ComponentFixture<CreditformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreditformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
