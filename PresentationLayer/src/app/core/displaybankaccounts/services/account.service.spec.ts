import { TestBed } from '@angular/core/testing';

import { GetbankaccountsService } from '../../account/services/account.service';

describe('GetbankaccountsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetbankaccountsService = TestBed.get(GetbankaccountsService);
    expect(service).toBeTruthy();
  });
});
