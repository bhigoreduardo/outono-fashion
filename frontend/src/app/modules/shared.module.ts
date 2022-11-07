import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { CheckboxComponent } from './components/checkbox/checkbox.component';
import { CardComponent } from './components/card/card.component';
import { FooterComponent } from './components/footer/footer.component';
import { ReplacePipe } from '../replace.pipe';
import { ComparePipe } from '../compare.pipe';
import { PriceprodutoPipe } from '../pipes/priceproduto.pipe';

@NgModule({
  declarations: [
    HeaderComponent,
    CheckboxComponent,
    CardComponent,
    FooterComponent,
    ReplacePipe,
    ComparePipe,
    PriceprodutoPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HeaderComponent,
    CheckboxComponent,
    CardComponent,
    FooterComponent,
    ReplacePipe,
    ComparePipe,
    PriceprodutoPipe
  ]
})
export class SharedModule { }
