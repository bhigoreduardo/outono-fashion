import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfirmacaoComponent } from './components/confirmacao/confirmacao.component';
import { LoginCheckoutComponent } from './components/login-checkout/login-checkout.component';
import { FinalizarCompraComponent } from './finalizar-compra.component';

const routes: Routes = [
  { path: '', component: FinalizarCompraComponent },
  { path: 'login', component: LoginCheckoutComponent },
  { path: 'confirmacao', component: ConfirmacaoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FinalizarCompraRoutingModule { }
