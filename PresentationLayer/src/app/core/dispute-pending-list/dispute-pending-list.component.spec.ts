import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisputePendingListComponent } from './dispute-pending-list.component';

describe('DisputePendingListComponent', () => {
  let component: DisputePendingListComponent;
  let fixture: ComponentFixture<DisputePendingListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisputePendingListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisputePendingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
