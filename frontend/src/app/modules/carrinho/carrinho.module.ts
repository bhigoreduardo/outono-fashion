import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarrinhoRoutingModule } from './carrinho-routing.module';
import { CarrinhoComponent } from './carrinho.component';
import { SharedModule } from '../../shared.module';
import { SwiperModule } from 'swiper/angular';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CarrinhoComponent
  ],
  imports: [
    CommonModule,
    CarrinhoRoutingModule,
    SharedModule,
    SwiperModule,
    FormsModule
  ]
})
export class CarrinhoModule { }
