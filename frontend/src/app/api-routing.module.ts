import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ContaGuard } from './modules/conta/service/conta.guard';
import { CheckoutGuard } from './modules/finalizar-compra/service/checkout.guard';
import { ErrorComponent } from './components/error/error.component';

const routes: Routes = [
  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
  { path: 'produtos', loadChildren: () => import('./modules/produtos/produtos.module').then(m => m.ProdutosModule) },
  { path: 'carrinho', loadChildren: () => import('./modules/carrinho/carrinho.module').then(m => m.CarrinhoModule) },
  { path: 'finalizar-compra', loadChildren: () => import('./modules/finalizar-compra/finalizar-compra.module').then(m => m.FinalizarCompraModule), canActivate: [CheckoutGuard] },
  { path: 'login', loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule) },
  { path: 'inicio', loadChildren: () => import('./modules/inicio/inicio.module').then(m => m.InicioModule) },
  { path: 'conta', loadChildren: () => import('./modules/conta/conta.module').then(m => m.ContaModule), canActivate: [ContaGuard] },
  { path: 'contato', loadChildren: () => import('./modules/contato/contato.module').then(m => m.ContatoModule) },
  { path: 'sobre', loadChildren: () => import('./modules/sobre/sobre.module').then(m => m.SobreModule) },
  { path: '**', component: ErrorComponent }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class ApiRoutingModule { }
