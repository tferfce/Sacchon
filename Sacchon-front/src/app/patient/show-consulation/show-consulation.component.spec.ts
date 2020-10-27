import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowConsulationComponent } from './show-consulation.component';

describe('ShowConsulationComponent', () => {
  let component: ShowConsulationComponent;
  let fixture: ComponentFixture<ShowConsulationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowConsulationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowConsulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
