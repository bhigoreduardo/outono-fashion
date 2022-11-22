import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputsmallComponent } from './inputsmall.component';

describe('InputsmallComponent', () => {
  let component: InputsmallComponent;
  let fixture: ComponentFixture<InputsmallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputsmallComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InputsmallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
