import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfirmacaoComponent } from './components/confirmacao/confirmacao.component';
import { LoginCheckoutComponent } from './components/login-checkout/login-checkout.component';
import { LoginComponent } from './login.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'confirmacao', component: ConfirmacaoComponent },
  { path: 'finalizar-compra', component: LoginCheckoutComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
