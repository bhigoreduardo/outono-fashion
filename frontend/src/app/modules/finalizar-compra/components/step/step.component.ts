import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.scss']
})
export class StepComponent {

  @Input() identity!: boolean;
  @Input() payment!: boolean;
  @Input() confirmation!: boolean;

}
