import { TestBed } from '@angular/core/testing';

import { ContaGuard } from './conta.guard';

describe('ContaGuard', () => {
  let guard: ContaGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ContaGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
