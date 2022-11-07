import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {

  @Input() image: string = '';
  @Input() name: string = '';
  @Input() priceMin: string = '';
  @Input() priceMax: string = '';
  @Input() offer: string = '';

}
