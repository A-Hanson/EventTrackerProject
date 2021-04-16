import { TestBed } from '@angular/core/testing';

import { TvwatchingsessionService } from './tvwatchingsession.service';

describe('TvwatchingsessionService', () => {
  let service: TvwatchingsessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TvwatchingsessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
