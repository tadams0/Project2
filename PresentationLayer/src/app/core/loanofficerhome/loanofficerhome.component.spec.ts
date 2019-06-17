import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanofficerhomeComponent } from './loanofficerhome.component';

describe('LoanofficerhomeComponent', () => {
  let component: LoanofficerhomeComponent;
  let fixture: ComponentFixture<LoanofficerhomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoanofficerhomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanofficerhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
