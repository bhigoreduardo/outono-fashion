import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup = this.formBuilder.group({
    login: ['', [Validators.email, Validators.required, Validators.minLength(10)]],
    senha: ['', [Validators.required, Validators.minLength(5)]]
  });

  type: string = 'password';
  eyeActive: boolean = true;

  logo: string = "/assets/images/logo.svg";
  banner: string = "/assets/images/login.png";

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
