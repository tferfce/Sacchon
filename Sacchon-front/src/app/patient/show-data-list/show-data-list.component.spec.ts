import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowDataListComponent } from './show-data-list.component';

describe('ShowDataListComponent', () => {
  let component: ShowDataListComponent;
  let fixture: ComponentFixture<ShowDataListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowDataListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowDataListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
