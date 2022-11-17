import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-checkout',
  templateUrl: './login-checkout.component.html',
  styleUrls: ['./login-checkout.component.scss']
})
export class LoginCheckoutComponent implements OnInit {
  formLogin: FormGroup = this.formBuilder.group({
    login: ['', [Validators.email, Validators.required, Validators.minLength(10)]],
    senha: ['', [Validators.required, Validators.minLength(5)]]
  });

  backgroundImage: string = '/assets/images/login-checkout.png';
  type: string = 'password';
  eyeActive: boolean = true;
  // eyeButton: boolean = true;

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
  }

  setEye(type: string) {
    this.type = type;
    this.eyeActive = !this.eyeActive;
  }

}
