import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.scss']
})
export class CheckboxComponent {

  @Input() titleLabel?: string;
  @Input() placeholder?: string;
  @Input() valueCheckbox?: string;
  @Input() inputName?: string;
  @Input() check?: string;

}
