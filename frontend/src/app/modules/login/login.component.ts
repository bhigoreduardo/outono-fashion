import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IUsuarioLoginInput, IUsuarioSenhaInput } from 'src/app/model/IUsuario';
import { IGenero } from '../../model/IGenero';
import { LoginService } from './service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  // Forms Vars
  formLogin!: FormGroup;
  formInsertPF!: FormGroup;
  formInsertPJ!: FormGroup;

  generos!: IGenero[];

  // Password Vars
  passwordLoginBoolean: boolean = true;
  passwordLoginType: string = 'password';

  passwordInsertPFBoolean: boolean = true;
  passwordRepeatInsertPFBoolean: boolean = true;
  passwordInsertPFType: string = 'password';
  passwordRepeatInsertPFType: string = 'password';

  passwordInsertPJBoolean: boolean = true;
  passwordRepeatInsertPJBoolean: boolean = true;
  passwordInsertPJType: string = (this.passwordInsertPJBoolean == true) ? 'password' : 'text';
  passwordRepeatInsertPJType: string = (this.passwordRepeatInsertPJBoolean == true) ? 'password' : 'text';

  // Type Forms Vars
  registerType: string = 'pf';
  register: Boolean = false;

  // Images Vars
  logo: string = "/assets/images/logo.svg";
  banner: string = "/assets/images/login.png";

  // Message
  message!: string;

  constructor(
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.findQueryParams();
    this.findAllGenero();
    this.createForms();
  }

  findQueryParams() {
    const queryParams = this.activatedRoute.snapshot.queryParamMap;

    if (queryParams.get('cadastrar') == '1') {
      this.setRegister();
    }
  }

  findAllGeneroPromise() {
    return new Promise(resolve => {
      this.loginService.findAllGenero().subscribe(
        data => {
          resolve(data);
          console.log(data)
        }
      )
    })
  }

  async findAllGenero() {
    this.generos = <IGenero[]>await this.findAllGeneroPromise();
  }

  createForms(): void {
    this.formLogin = this.formBuilder.group({
      email: [null, [Validators.email, Validators.required, Validators.minLength(10), Validators.maxLength(120)]],
      senha: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)]]
    });

    this.formInsertPF = this.formBuilder.group({
      nome: [null, Validators.required, Validators.minLength(5), Validators.maxLength(60)],
      sobrenome: [null, Validators.required, Validators.minLength(5), Validators.maxLength(60)],
      email: [null, [Validators.email, Validators.required, Validators.minLength(10), Validators.maxLength(120)]],
      cpfCnpj: [null, [Validators.required, Validators.minLength(14), Validators.maxLength(14)]],
      rgIe: [null, [Validators.required, Validators.minLength(7), Validators.maxLength(14)]],
      dataNascimento: [null, Validators.required],
      genero: ['Informe seu Gênero', Validators.required],
      senha: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      repetirSenha: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)]]
    });

    this.formInsertPJ = this.formBuilder.group({
      nome: [null, Validators.required, Validators.minLength(5), Validators.maxLength(120)],
      email: [null, [Validators.email, Validators.required, Validators.minLength(10), Validators.maxLength(120)]],
      cpfCnpj: [null, [Validators.required, Validators.maxLength(14)]],
      rgIe: [null, [Validators.required, Validators.maxLength(14)]],
      senha: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      repetirSenha: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)]]
    });
  }

  sendLogin(values: any): void {
    const usuarioLoginInput: IUsuarioLoginInput = {
      email: values.email,
      senha: values.senha
    }

    this.loginService.authLogin(usuarioLoginInput).subscribe(
      res => {
        localStorage.setItem('usuarioLoginModel', JSON.stringify(res));
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/conta']);
        })
      }, error => {
        this.message = error.error.userMessage;
      }
    )

    console.log(values);
  }

  sendInsertPF(values: any): void {
    const usuarioSenhaInput: IUsuarioSenhaInput = {
      nome: values.nome,
      sobrenome: values.sobrenome,
      email: values.email,
      cpfCnpj: values.cpfCnpj,
      rgIe: values.rgIe,
      dataNascimento: new Date(),
      genero: {
        id: values.genero
      },
      senha: values.senha
    }

    this.loginService.insert(usuarioSenhaInput).subscribe(
      res => {
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['login/confirmacao']);
        });
      }, error => {
        this.message = error.error.userMessage;
      });
  }

  sendInsertPJ(values: any): void {
    const usuarioSenhaInput: IUsuarioSenhaInput = {
      nome: values.nome,
      sobrenome: null,
      email: values.email,
      cpfCnpj: values.cpfCnpj,
      rgIe: values.rgIe,
      dataNascimento: null,
      genero: {
        id: 5
      },
      senha: values.senha
    }

    this.loginService.insert(usuarioSenhaInput).subscribe(
      res => {
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['login/confirmacao']);
        });
      }, error => {
        this.message = error.error.userMessage;
      });
  }

  setPasswordLogin(): void {
    this.passwordLoginBoolean = !this.passwordLoginBoolean;
    this.passwordLoginType = (this.passwordLoginBoolean == true) ? 'password' : 'text';
  }

  setPasswordInsertPF(): void {
    this.passwordInsertPFBoolean = !this.passwordInsertPFBoolean;
    this.passwordInsertPFType = (this.passwordInsertPFBoolean == true) ? 'password' : 'text';
  }

  setPasswordRepeatInsertPF(): void {
    this.passwordRepeatInsertPFBoolean = !this.passwordRepeatInsertPFBoolean;
    this.passwordRepeatInsertPFType = (this.passwordRepeatInsertPFBoolean == true) ? 'password' : 'text';
  }

  setPasswordInsertPJ(): void {
    this.passwordInsertPJBoolean = !this.passwordInsertPJBoolean;
    this.passwordInsertPJType = (this.passwordInsertPJBoolean == true) ? 'password' : 'text';
  }

  setPasswordRepeatInsertPJ(): void {
    this.passwordRepeatInsertPJBoolean = !this.passwordRepeatInsertPJBoolean;
    this.passwordRepeatInsertPJType = (this.passwordRepeatInsertPJBoolean == true) ? 'password' : 'text';
  }

  setRegister() {
    this.register = !this.register;
  }

  setRegisterType(type: string) {
    this.registerType = type;
  }

}
