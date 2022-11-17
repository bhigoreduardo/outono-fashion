import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginCheckoutComponent } from './login-checkout.component';

describe('LoginCheckoutComponent', () => {
  let component: LoginCheckoutComponent;
  let fixture: ComponentFixture<LoginCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginCheckoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
