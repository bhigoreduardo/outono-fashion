import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContaRoutingModule } from './conta-routing.module';
import { ContaComponent } from './conta.component';
import { SharedModule } from 'src/app/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { SwiperModule } from 'swiper/angular';


@NgModule({
  declarations: [
    ContaComponent
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    SwiperModule
  ]
})
export class ContaModule { }
