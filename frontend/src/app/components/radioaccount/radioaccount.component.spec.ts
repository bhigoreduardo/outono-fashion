import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadioaccountComponent } from './radioaccount.component';

describe('RadioaccountComponent', () => {
  let component: RadioaccountComponent;
  let fixture: ComponentFixture<RadioaccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RadioaccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RadioaccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
