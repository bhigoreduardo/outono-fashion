import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutosRoutingModule } from './produtos-routing.module';
import { ProdutosComponent } from './produtos.component';
import { SharedModule } from '../shared.module';
import { FormsModule } from '@angular/forms';
import { SwiperModule } from 'swiper/angular';
import { IndividualComponent } from './components/individual/individual.component';

@NgModule({
  declarations: [
    ProdutosComponent,
    IndividualComponent
  ],
  imports: [
    CommonModule,
    ProdutosRoutingModule,
    SharedModule,
    SwiperModule
  ]
})
export class ProdutosModule { }
