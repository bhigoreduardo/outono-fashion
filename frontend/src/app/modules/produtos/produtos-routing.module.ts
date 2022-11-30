import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GeneroComponent } from './components/genero/genero.component';
import { IndividualComponent } from './components/individual/individual.component';
import { ProdutosComponent } from './produtos.component';

const routes: Routes = [
  { path: '', component: ProdutosComponent },
  { path: ':nome/:id', component: IndividualComponent },
  { path: ':genero', component: GeneroComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProdutosRoutingModule { }
