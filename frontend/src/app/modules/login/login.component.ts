import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

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

  formRegisterPF: FormGroup = this.formBuilder.group({
    nome: ['', Validators.required, Validators.minLength(15), Validators.maxLength(60)],
    sobrenome: ['', Validators.required, Validators.minLength(15), Validators.maxLength(60)],
    login: ['', [Validators.email, Validators.required, Validators.minLength(10)]],
    cpf: ['', [Validators.required, Validators.maxLength(14)]],
    rg: ['', [Validators.required, Validators.maxLength(14)]],
    nascimento: ['', Validators.required],
    genero: ['', Validators.required],
    senha: ['', [Validators.required, Validators.minLength(5)]],
    repetirSenha: ['', [Validators.required, Validators.minLength(5)]]
  });

  formRegisterPJ: FormGroup = this.formBuilder.group({
    razaoSocial: ['', Validators.required, Validators.minLength(15), Validators.maxLength(120)],
    login: ['', [Validators.email, Validators.required, Validators.minLength(10)]],
    cnpj: ['', [Validators.required, Validators.maxLength(14)]],
    ie: ['', [Validators.required, Validators.maxLength(14)]],
    genero: ['', Validators.required],
    senha: ['', [Validators.required, Validators.minLength(5)]],
    repetirSenha: ['', [Validators.required, Validators.minLength(5)]]
  });

  type: string = 'password';
  eyeActive: boolean = true;

  registerType: string = 'pf';
  register: Boolean = false;

  logo: string = "/assets/images/logo.svg";
  banner: string = "/assets/images/login.png";

  constructor(
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const queryParams = this.activatedRoute.snapshot.queryParamMap;

    if (queryParams.get('cadastrar') == '1') {
      this.setRegister();
    }

  }

  setEye(type: string) {
    this.type = type;
    this.eyeActive = !this.eyeActive;
  }

  setRegister() {
    this.register = !this.register;
  }

  setRegisterType(type: string) {
    this.registerType = type;
  }

}
