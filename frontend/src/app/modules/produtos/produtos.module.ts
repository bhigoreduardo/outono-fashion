import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutosRoutingModule } from './produtos-routing.module';
import { ProdutosComponent } from './produtos.component';
import { SharedModule } from '../shared.module';


@NgModule({
  declarations: [
    ProdutosComponent
  ],
  imports: [
    CommonModule,
    ProdutosRoutingModule,
    SharedModule
  ]
})
export class ProdutosModule { }
