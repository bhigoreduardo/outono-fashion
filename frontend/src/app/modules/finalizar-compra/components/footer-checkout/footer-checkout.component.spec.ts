import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterCheckoutComponent } from './footer-checkout.component';

describe('FooterCheckoutComponent', () => {
  let component: FooterCheckoutComponent;
  let fixture: ComponentFixture<FooterCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FooterCheckoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FooterCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
