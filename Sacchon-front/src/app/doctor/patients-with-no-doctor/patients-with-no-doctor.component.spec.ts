import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientsWithNoDoctorComponent } from './patients-with-no-doctor.component';

describe('PatientsWithNoDoctorComponent', () => {
  let component: PatientsWithNoDoctorComponent;
  let fixture: ComponentFixture<PatientsWithNoDoctorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientsWithNoDoctorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientsWithNoDoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
