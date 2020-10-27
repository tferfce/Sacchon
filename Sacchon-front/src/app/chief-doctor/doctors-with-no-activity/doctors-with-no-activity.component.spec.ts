import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorsWithNoActivityComponent } from './doctors-with-no-activity.component';

describe('DoctorsWithNoActivityComponent', () => {
  let component: DoctorsWithNoActivityComponent;
  let fixture: ComponentFixture<DoctorsWithNoActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorsWithNoActivityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorsWithNoActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
