import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowDoctorConsultationsComponent } from './show-doctor-consultations.component';

describe('ShowDoctorConsultationsComponent', () => {
  let component: ShowDoctorConsultationsComponent;
  let fixture: ComponentFixture<ShowDoctorConsultationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowDoctorConsultationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowDoctorConsultationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
