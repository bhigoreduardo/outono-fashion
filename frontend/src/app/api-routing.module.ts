import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
  { path: 'produtos', loadChildren: () => import('./modules/produtos/produtos.module').then(m => m.ProdutosModule) },
  { path: 'carrinho', loadChildren: () => import('./modules/carrinho/carrinho.module').then(m => m.CarrinhoModule) },
  { path: 'finalizar-compra', loadChildren: () => import('./modules/finalizar-compra/finalizar-compra.module').then(m => m.FinalizarCompraModule) },
  { path: 'login', loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule) },
  { path: 'inicio', loadChildren: () => import('./modules/inicio/inicio.module').then(m => m.InicioModule) }
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
