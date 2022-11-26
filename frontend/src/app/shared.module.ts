import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CheckboxComponent } from './components/checkbox/checkbox.component';
import { FooterComponent } from './components/footer/footer.component';
import { ReplacePipe } from './pipes/replace.pipe';
import { ComparePipe } from './pipes/compare.pipe';
import { PriceprodutoPipe } from './pipes/priceproduto.pipe';
import { RadioboxComponent } from './components/radiobox/radiobox.component';
import { CommentaryComponent } from './components/commentary/commentary.component';
import { RadiolocationComponent } from './components/radiolocation/radiolocation.component';
import { InputsmallComponent } from './components/inputsmall/inputsmall.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RangeboxComponent } from './components/rangebox/rangebox.component';
import { MessageComponent } from './components/message/message.component';
import { CardComponent } from './components/card/card.component';
import { HeaderComponent } from './components/header/header.component';
import { RadioaccountComponent } from './components/radioaccount/radioaccount.component';
import { InputupdateComponent } from './components/inputupdate/inputupdate.component';
import { EmptymessageComponent } from './components/emptymessage/emptymessage.component';

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
    InputsmallComponent,
    RangeboxComponent,
    MessageComponent,
    RadioaccountComponent,
    InputupdateComponent,
    EmptymessageComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
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
    InputsmallComponent,
    RangeboxComponent,
    MessageComponent,
    RadioaccountComponent,
    InputupdateComponent,
    EmptymessageComponent
  ]
})
export class SharedModule { }
