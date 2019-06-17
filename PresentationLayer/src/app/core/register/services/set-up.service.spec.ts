import { TestBed } from '@angular/core/testing';

import { SetUpService } from './set-up.service';

describe('SetUpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SetUpService = TestBed.get(SetUpService);
    expect(service).toBeTruthy();
  });
});
