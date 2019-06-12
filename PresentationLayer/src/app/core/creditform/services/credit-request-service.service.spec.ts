import { TestBed } from '@angular/core/testing';

import { CreditRequestServiceService } from './credit-request-service.service';

describe('CreditRequestServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreditRequestServiceService = TestBed.get(CreditRequestServiceService);
    expect(service).toBeTruthy();
  });
});
