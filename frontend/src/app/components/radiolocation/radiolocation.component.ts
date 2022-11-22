import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-radiolocation',
  templateUrl: './radiolocation.component.html',
  styleUrls: ['./radiolocation.component.scss']
})
export class RadiolocationComponent {

  @Input() name!: string;
  @Input() nickname!: string;
  @Input() street!: string;
  @Input() streetNumber!: string;
  @Input() neighborhood!: string;
  @Input() complement!: string;
  @Input() reference!: string;
  @Input() state!: string;
  @Input() city!: string;
  @Input() cep!: string;

}
