import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowPatientDataComponent } from './show-patient-data.component';

describe('ShowPatientDataComponent', () => {
  let component: ShowPatientDataComponent;
  let fixture: ComponentFixture<ShowPatientDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowPatientDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowPatientDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
