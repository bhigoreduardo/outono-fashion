import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { SharedModule } from '../../shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ConfirmacaoComponent } from './components/confirmacao/confirmacao.component';


@NgModule({
  declarations: [
    LoginComponent,
    ConfirmacaoComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class LoginModule { }
