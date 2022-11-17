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
import { RadiolocationComponent } from './components/radiolocation/radiolocation.component';
import { InputsmallComponent } from './components/inputsmall/inputsmall.component';
import { ReactiveFormsModule } from '@angular/forms';

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
    CommentaryComponent,
    RadiolocationComponent,
    InputsmallComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
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
    CommentaryComponent,
    RadiolocationComponent,
    InputsmallComponent
  ]
})
export class SharedModule { }
