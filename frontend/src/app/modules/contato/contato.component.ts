import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.scss']
})
export class ContatoComponent implements OnInit {
  formContato!: FormGroup;

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.createFormContato();
  }

  createFormContato(): void {
    this.formContato = this.formBuilder.group({
      nome: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(120)]),
      cpfCnpj: new FormControl(null, [Validators.required, Validators.minLength(11), Validators.maxLength(14)]),
      email: new FormControl(null, [Validators.email, Validators.required, Validators.minLength(10), Validators.maxLength(120)]),
      telefone: new FormControl(null, [Validators.required, Validators.minLength(11), Validators.maxLength(11)]),
      mensagem: new FormControl(null, [Validators.required, Validators.minLength(10), Validators.maxLength(255)])
    });
  }

  resetForm(): void {
    this.formContato.reset();
  }

}
