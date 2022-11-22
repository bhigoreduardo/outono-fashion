import { Component, ElementRef, Input, OnInit, Renderer2, ViewChild } from '@angular/core';

@Component({
  selector: 'app-rangebox',
  templateUrl: './rangebox.component.html',
  styleUrls: ['./rangebox.component.scss']
})
export class RangeboxComponent implements OnInit {
  // Input Vars
  @Input() min!: number;
  @Input() max!: number;
  @Input() minGap!: number;
  @Input() show!: Boolean;

  // Element Component
  @ViewChild('minScale') minScale: ElementRef<HTMLInputElement> | undefined;
  @ViewChild('maxScale') maxScale: ElementRef<HTMLInputElement> | undefined;
  @ViewChild('sliderTrack') sliderTrack: ElementRef<HTMLDivElement> | undefined;

  // Component Values
  avg!: number;
  @Input() minValue: number | undefined;
  @Input() maxValue: number | undefined;

  constructor() { }

  ngOnInit(): void {
    this.setMinScale();
    this.setMaxScale();
    this.initializeVars();
  }

  setMinScale() {
    if ((this.maxValue! - this.minValue!) <= this.minGap) {
      this.minValue = this.maxValue! - this.minGap;
    }

    if (this.minScale != undefined) {
      this.minValue = Number(this.minScale.nativeElement.value);
      this.fillColor();
    }
  }

  setMaxScale() {
    if ((this.maxValue! - this.minValue!) <= this.minGap) {
      this.maxValue = this.minValue! + this.minGap;
    }

    if (this.maxScale != undefined) {
      this.maxValue = Number(this.maxScale.nativeElement.value);
      this.fillColor();
    }
  }

  fillColor() {
    let firstPercent = (this.minValue! / Number(this.minScale!.nativeElement.max)) * 100;
    let secondPercent = (this.maxValue! / Number(this.minScale!.nativeElement.max)) * 100;

    this.sliderTrack!.nativeElement.
      style.background = `linear-gradient(to right, #dadae5 ${firstPercent}%, #F29D36 ${firstPercent}%, #F29D36 ${secondPercent}%, #dadae5 ${secondPercent}%)`;
  }

  initializeVars() {
    if (this.minValue == undefined) {
      this.minValue = this.min;
    }

    if (this.maxValue == undefined) {
      this.maxValue = this.max;
    }
  }

}
