import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgxMaskModule } from 'ngx-mask';

import { SwiperModule } from 'swiper/angular';
import { SharedModule } from '../../shared.module';
import { IndividualComponent } from './components/individual/individual.component';
import { ProdutosRoutingModule } from './produtos-routing.module';
import { ProdutosComponent } from './produtos.component';
import { GeneroComponent } from './components/genero/genero.component';

@NgModule({
  declarations: [
    ProdutosComponent,
    IndividualComponent,
    GeneroComponent
  ],
  imports: [
    CommonModule,
    ProdutosRoutingModule,
    SharedModule,
    SwiperModule,
    NgxMaskModule.forRoot()
  ]
})
export class ProdutosModule { }
