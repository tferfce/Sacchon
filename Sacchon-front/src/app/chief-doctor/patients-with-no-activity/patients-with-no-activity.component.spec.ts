import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientsWithNoActivityComponent } from './patients-with-no-activity.component';

describe('PatientsWithNoActivityComponent', () => {
  let component: PatientsWithNoActivityComponent;
  let fixture: ComponentFixture<PatientsWithNoActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientsWithNoActivityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientsWithNoActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
