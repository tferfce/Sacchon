import { TestBed } from '@angular/core/testing';

import { ChiefDoctorServiceService } from './chief-doctor-service.service';

describe('ChiefDoctorServiceService', () => {
  let service: ChiefDoctorServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChiefDoctorServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
