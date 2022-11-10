import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-radiobox',
  templateUrl: './radiobox.component.html',
  styleUrls: ['./radiobox.component.scss']
})
export class RadioboxComponent implements OnInit {

  @Input() titleLabel?: string;
  @Input() placeholder?: string;
  @Input() valueRadioBox?: string;
  @Input() inputName?: string;
  @Input() check?: string;
  @Input() color: string = 'none';

  constructor() { }

  ngOnInit(): void {
  }

}
