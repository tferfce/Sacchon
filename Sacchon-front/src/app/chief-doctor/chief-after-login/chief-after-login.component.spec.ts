import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChiefAfterLoginComponent } from './chief-after-login.component';

describe('ChiefAfterLoginComponent', () => {
  let component: ChiefAfterLoginComponent;
  let fixture: ComponentFixture<ChiefAfterLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChiefAfterLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChiefAfterLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
