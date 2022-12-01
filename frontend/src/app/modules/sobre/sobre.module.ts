import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SobreRoutingModule } from './sobre-routing.module';
import { SobreComponent } from './sobre.component';
import { SharedModule } from 'src/app/shared.module';


@NgModule({
  declarations: [
    SobreComponent
  ],
  imports: [
    CommonModule,
    SobreRoutingModule,
    SharedModule
  ]
})
export class SobreModule { }
