import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.scss']
})
export class CheckboxComponent {

  @Input() title!: string;
  @Input() name!: string;
  @Input() value!: string;

  @Input() placeholder!: string;
  @Input() check!: string;

}
