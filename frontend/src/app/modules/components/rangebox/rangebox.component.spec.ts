import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RangeboxComponent } from './rangebox.component';

describe('RangeboxComponent', () => {
  let component: RangeboxComponent;
  let fixture: ComponentFixture<RangeboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RangeboxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RangeboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
