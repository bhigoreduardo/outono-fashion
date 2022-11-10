import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadioboxComponent } from './radiobox.component';

describe('RadioboxComponent', () => {
  let component: RadioboxComponent;
  let fixture: ComponentFixture<RadioboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RadioboxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RadioboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
