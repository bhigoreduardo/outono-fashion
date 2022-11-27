import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-checkout',
  templateUrl: './login-checkout.component.html',
  styleUrls: ['./login-checkout.component.scss']
})
export class LoginCheckoutComponent implements OnInit {

  backgroundImage: string = '/assets/images/login-checkout.png';

  formLogin!: FormGroup;
  passwordLoginBoolean: boolean = true;
  passwordLoginType: string = 'password';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.createFormLogin();
  }

  createFormLogin(): void {
    this.formLogin = this.formBuilder.group({
      login: new FormControl(null, [Validators.email, Validators.required, Validators.minLength(10)]),
      senha: new FormControl(null, [Validators.required, Validators.minLength(5)])
    });
  }

  setPasswordLogin(): void {
    this.passwordLoginBoolean = !this.passwordLoginBoolean;
    this.passwordLoginType = (this.passwordLoginBoolean == true) ? 'password' : 'text';
  }

  registerConta(): void {
    const query = {
      "cadastrar": "1"
    }

    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['login'], { queryParams: query });
    });
  }

}
