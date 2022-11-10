import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { CheckboxComponent } from './components/checkbox/checkbox.component';
import { CardComponent } from './components/card/card.component';
import { FooterComponent } from './components/footer/footer.component';
import { ReplacePipe } from './pipes/replace.pipe';
import { ComparePipe } from './pipes/compare.pipe';
import { PriceprodutoPipe } from './pipes/priceproduto.pipe';
import { RadioboxComponent } from './components/radiobox/radiobox.component';
import { CommentaryComponent } from './components/commentary/commentary.component';

@NgModule({
  declarations: [
    HeaderComponent,
    CheckboxComponent,
    CardComponent,
    FooterComponent,
    ReplacePipe,
    ComparePipe,
    PriceprodutoPipe,
    RadioboxComponent,
    CommentaryComponent
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
    PriceprodutoPipe,
    RadioboxComponent,
    CommentaryComponent
  ]
})
export class SharedModule { }
