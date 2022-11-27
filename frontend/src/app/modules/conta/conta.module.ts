import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared.module';
import { SwiperModule } from 'swiper/angular';
import { ContaRoutingModule } from './conta-routing.module';
import { ContaComponent } from './conta.component';
import { NgxMaskModule } from 'ngx-mask';

@NgModule({
  declarations: [
    ContaComponent
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    SwiperModule,
    NgxMaskModule.forRoot()
  ]
})
export class ContaModule { }
