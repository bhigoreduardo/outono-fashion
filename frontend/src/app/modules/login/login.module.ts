import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { SharedModule } from '../../shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ConfirmacaoComponent } from './components/confirmacao/confirmacao.component';
import { NgxMaskModule } from 'ngx-mask';
import { RecuperarSenhaComponent } from './components/recuperar-senha/recuperar-senha.component';

@NgModule({
  declarations: [
    LoginComponent,
    ConfirmacaoComponent,
    RecuperarSenhaComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    NgxMaskModule.forRoot()
  ]
})
export class LoginModule { }
