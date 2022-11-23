import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputupdateComponent } from './inputupdate.component';

describe('InputupdateComponent', () => {
  let component: InputupdateComponent;
  let fixture: ComponentFixture<InputupdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputupdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InputupdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
