import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDataViewComponent } from './add-data-view.component';

describe('AddDataViewComponent', () => {
  let component: AddDataViewComponent;
  let fixture: ComponentFixture<AddDataViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDataViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDataViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
