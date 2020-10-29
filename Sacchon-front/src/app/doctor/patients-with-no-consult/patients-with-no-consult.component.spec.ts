import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientsWithNoConsultComponent } from './patients-with-no-consult.component';

describe('PatientsWithNoConsultComponent', () => {
  let component: PatientsWithNoConsultComponent;
  let fixture: ComponentFixture<PatientsWithNoConsultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientsWithNoConsultComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientsWithNoConsultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
