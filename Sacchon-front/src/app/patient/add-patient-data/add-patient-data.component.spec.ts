import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPatientDataComponent } from './add-patient-data.component';

describe('AddPatientDataComponent', () => {
  let component: AddPatientDataComponent;
  let fixture: ComponentFixture<AddPatientDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPatientDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPatientDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
