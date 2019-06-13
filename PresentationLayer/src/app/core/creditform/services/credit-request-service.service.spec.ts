import { TestBed } from '@angular/core/testing';

import { CreditRequestService } from './credit-request-service.service';

describe('CreditRequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreditRequestService = TestBed.get(CreditRequestService);
    expect(service).toBeTruthy();
  });
});
