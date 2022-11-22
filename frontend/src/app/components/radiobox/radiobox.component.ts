import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-radiobox',
  templateUrl: './radiobox.component.html',
  styleUrls: ['./radiobox.component.scss']
})
export class RadioboxComponent {

  @Input() title!: string;
  @Input() placeholder!: string;
  @Input() name!: string;
  @Input() value!: string;

  @Input() check!: string;
  @Input() color: string = 'none';

}
