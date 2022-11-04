import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { CheckboxComponent } from './components/checkbox/checkbox.component';

@NgModule({
  declarations: [
    HeaderComponent,
    CheckboxComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HeaderComponent,
    CheckboxComponent
  ]
})
export class SharedModule { }
