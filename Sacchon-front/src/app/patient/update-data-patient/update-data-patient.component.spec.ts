import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDataPatientComponent } from './update-data-patient.component';

describe('UpdateDataPatientComponent', () => {
  let component: UpdateDataPatientComponent;
  let fixture: ComponentFixture<UpdateDataPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateDataPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateDataPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
