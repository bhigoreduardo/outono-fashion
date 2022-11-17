import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FinalizarCompraRoutingModule } from './finalizar-compra-routing.module';
import { FinalizarCompraComponent } from './finalizar-compra.component';
import { StepComponent } from './components/step/step.component';
import { SharedModule } from '../shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderCheckoutComponent } from './components/header-checkout/header-checkout.component';
import { FooterCheckoutComponent } from './components/footer-checkout/footer-checkout.component';
import { ConfirmacaoComponent } from './components/confirmacao/confirmacao.component';
import { LoginCheckoutComponent } from './components/login-checkout/login-checkout.component';


@NgModule({
  declarations: [
    FinalizarCompraComponent,
    StepComponent,
    HeaderCheckoutComponent,
    FooterCheckoutComponent,
    ConfirmacaoComponent,
    LoginCheckoutComponent
  ],
  imports: [
    CommonModule,
    FinalizarCompraRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class FinalizarCompraModule { }
