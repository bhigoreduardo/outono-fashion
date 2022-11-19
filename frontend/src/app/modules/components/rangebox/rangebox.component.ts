import { Component, ElementRef, Input, OnInit, Renderer2, ViewChild } from '@angular/core';

@Component({
  selector: 'app-rangebox',
  templateUrl: './rangebox.component.html',
  styleUrls: ['./rangebox.component.scss']
})
export class RangeboxComponent implements OnInit {
  @Input() min!: number;
  @Input() max!: number;
  @Input() minGap!: number;
  @Input() show!: Boolean;

  @ViewChild('minScale') minScale!: ElementRef<HTMLInputElement>;
  @ViewChild('maxScale') maxScale!: ElementRef<HTMLInputElement>;
  @ViewChild('sliderTrack') sliderTrack!: ElementRef<HTMLDivElement>;

  avg: number = (this.min + this.max) / 2;
  minValue: number = this.avg * 3 / 4;
  maxValue: number = this.avg * 5 / 4;

  constructor() { }

  ngOnInit(): void {
    this.setMinScale();
    this.setMaxScale();
  }

  setMinScale() {
    if ((Number(this.maxScale.nativeElement.value) - Number(this.minScale.nativeElement.value)) <= this.minGap) {
      this.minScale.nativeElement.value = String(Number(this.maxScale.nativeElement.value) - this.minGap);
    }

    this.minValue = Number(this.minScale.nativeElement.value);
    this.fillColor();
  }

  setMaxScale() {
    if ((Number(this.maxScale.nativeElement.value) - Number(this.minScale.nativeElement.value)) <= this.minGap) {
      this.maxScale.nativeElement.value = String(Number(this.minScale.nativeElement.value) + this.minGap);
    }

    this.maxValue = Number(this.maxScale.nativeElement.value);
    this.fillColor();
  }

  fillColor() {
    let firstPercent = (this.minValue / Number(this.minScale.nativeElement.max)) * 100;
    let secondPercent = (this.maxValue / Number(this.minScale.nativeElement.max)) * 100;

    this.sliderTrack.nativeElement.
      style.background = `linear-gradient(to right, #dadae5 ${firstPercent}%, #F29D36 ${firstPercent}%, #F29D36 ${secondPercent}%, #dadae5 ${secondPercent}%)`;
  }

}
