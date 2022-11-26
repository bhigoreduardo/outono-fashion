import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmptymessageComponent } from './emptymessage.component';

describe('EmptymessageComponent', () => {
  let component: EmptymessageComponent;
  let fixture: ComponentFixture<EmptymessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmptymessageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmptymessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
