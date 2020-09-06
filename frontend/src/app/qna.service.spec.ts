import { TestBed } from '@angular/core/testing';

import { QNAService } from './qna.service';

describe('QNAService', () => {
  let service: QNAService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QNAService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
