import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IUsuarioLoginInput, IUsuarioModel, IUsuarioSenhaInput } from 'src/app/model/IUsuario';
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
  message: string | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.createForms();
    this.findAllGeneroAsync();
    this.findQueryParams();
  }

  createForms(): void {
    this.formLogin = this.formBuilder.group({
      email: new FormControl(null, [Validators.email, Validators.required, Validators.minLength(10), Validators.maxLength(120)]),
      senha: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)])
    });

    this.formInsertPF = this.formBuilder.group({
      nome: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(60)]),
      sobrenome: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(60)]),
      email: new FormControl(null, [Validators.email, Validators.required, Validators.minLength(10), Validators.maxLength(120)]),
      cpfCnpj: new FormControl(null, [Validators.required, Validators.minLength(11), Validators.maxLength(14)]),
      rgIe: new FormControl(null, [Validators.required, Validators.minLength(7), Validators.maxLength(7)]),
      dataNascimento: new FormControl(null, Validators.required),
      genero: new FormControl('Informe seu Gênero', Validators.required),
      senha: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)]),
      repetirSenha: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)])
    });

    this.formInsertPJ = this.formBuilder.group({
      nome: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(120)]),
      email: new FormControl(null, [Validators.email, Validators.required, Validators.minLength(10), Validators.maxLength(120)]),
      cpfCnpj: new FormControl(null, [Validators.required, Validators.minLength(14), Validators.maxLength(14)]),
      rgIe: new FormControl(null, [Validators.required, Validators.maxLength(14)]),
      senha: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)]),
      repetirSenha: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(20)])
    });
  }

  login(usuarioLoginInput: IUsuarioLoginInput) {
    return new Promise(resolve => {
      this.loginService.login(usuarioLoginInput).subscribe(
        res => {
          localStorage.setItem('usuarioModel', JSON.stringify(res));
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/conta']);
          });
        }, error => {
          this.message = error.error.userMessage;
        }
      );
    });
  }

  async loginAsync(values: any) {
    const usuarioLoginInput: IUsuarioLoginInput = {
      email: values.email,
      senha: values.senha
    }

    await this.login(usuarioLoginInput);
  }

  insert(usuarioSenhaInput: IUsuarioSenhaInput) {
    return new Promise(resolve => {
      this.loginService.insert(usuarioSenhaInput).subscribe(
        res => {
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['login/confirmacao']);
          });
        }, error => {
          this.message = error.error.userMessage;
        });
    })
  }

  async insertPFAsync(values: any) {
    if (values.senha === values.repetirSenha) {
      const usuarioSenhaInput: IUsuarioSenhaInput = {
        nome: values.nome,
        sobrenome: values.sobrenome,
        email: values.email,
        cpfCnpj: values.cpfCnpj,
        rgIe: values.rgIe,
        dataNascimento: values.dataNascimento,
        genero: {
          id: values.genero
        },
        senha: values.senha
      }

      await this.insert(usuarioSenhaInput);
      return;
    }

    this.message = 'Senhas diferentes digitadas nos campo.';
  }

  async insertPJAsync(values: any) {
    if (values.senha === values.repetirSenha) {
      const usuarioSenhaInput: IUsuarioSenhaInput = {
        nome: values.nome,
        email: values.email,
        cpfCnpj: values.cpfCnpj,
        rgIe: values.rgIe,
        genero: {
          id: 5
        },
        senha: values.senha
      }

      await this.insert(usuarioSenhaInput);
      return;
    }

    this.message = 'Senhas diferentes digitadas nos campo.';
  }

  findAllGenero() {
    return new Promise(resolve => {
      this.loginService.findAllGenero().subscribe(
        data => {
          resolve(data);
        }
      )
    })
  }

  async findAllGeneroAsync() {
    this.generos = <IGenero[]>await this.findAllGenero();
  }

  findQueryParams() {
    const queryParams = this.activatedRoute.snapshot.queryParamMap;

    if (queryParams.get('cadastrar') == '1') {
      this.setRegister();
    }
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

  clearMessage(value: any): void {
    this.message = undefined;
  }

}
