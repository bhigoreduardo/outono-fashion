import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent implements OnInit, OnChanges {

  @Input() message: string | undefined;

  scale!: string;
  opacity!: string;
  transition!: string;

  constructor() { }

  ngOnInit(): void {
    this.scale = 'scale(0)';
    this.opacity = '0';
    this.transition = 'all 500ms ease-in-out';
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.message != undefined) {
      this.sendMessage();
    }
  }

  sendMessage() {
    this.scale = 'scale(1.0) translateX(-50%)';
    this.opacity = '1';

    setTimeout(() => {
      this.closeMessage();
    }, 5000);
  }

  closeMessage() {
    this.scale = 'scale(0)';
    this.opacity = '0';
    this.message = undefined;
  }

}
