import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientWaitingConsultsComponent } from './patient-waiting-consults.component';

describe('PatientWaitingConsultsComponent', () => {
  let component: PatientWaitingConsultsComponent;
  let fixture: ComponentFixture<PatientWaitingConsultsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientWaitingConsultsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientWaitingConsultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
