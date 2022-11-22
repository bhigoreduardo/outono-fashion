import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-inputsmall',
  templateUrl: './inputsmall.component.html',
  styleUrls: ['./inputsmall.component.scss']
})
export class InputsmallComponent implements OnInit {
  @Input() type!: string;
  @Input() name!: string;
  @Input() id!: string;
  @Input() placeholder!: string;
  @Input() required!: boolean;
  @Input() fieldLabel!: string;

  @Input() eyeButton!: string;
  @Input() formControlName!: string;

  @Input() eyeActive: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  setEye(type: string) {
    this.type = type;
    this.eyeActive = !this.eyeActive;
  }

}
