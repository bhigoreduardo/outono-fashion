import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadiolocationComponent } from './radiolocation.component';

describe('RadiolocationComponent', () => {
  let component: RadiolocationComponent;
  let fixture: ComponentFixture<RadiolocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RadiolocationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RadiolocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
