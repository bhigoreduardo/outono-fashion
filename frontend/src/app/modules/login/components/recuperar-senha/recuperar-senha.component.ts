import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-recuperar-senha',
  templateUrl: './recuperar-senha.component.html',
  styleUrls: ['./recuperar-senha.component.scss']
})
export class RecuperarSenhaComponent implements OnInit {

  logo: string = '/assets/images/logo.svg';
  backgroundImage: string = '/assets/images/login.png';
  formRecuperarSenha!: FormGroup;

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.createFormRecuperarSenha();
  }

  createFormRecuperarSenha(): void {
    this.formRecuperarSenha = this.formBuilder.group({
      email: new FormControl(null, [Validators.email, Validators.required, Validators.minLength(10), Validators.maxLength(120)])
    });
  }

}
