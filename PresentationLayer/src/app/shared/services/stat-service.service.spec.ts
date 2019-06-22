import { TestBed } from '@angular/core/testing';

import { StatServiceService } from './stat-service.service';

describe('StatServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StatServiceService = TestBed.get(StatServiceService);
    expect(service).toBeTruthy();
  });
});
