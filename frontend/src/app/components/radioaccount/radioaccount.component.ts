import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-radioaccount',
  templateUrl: './radioaccount.component.html',
  styleUrls: ['./radioaccount.component.scss']
})
export class RadioaccountComponent implements OnInit {

  @Input() title!: string;
  @Input() name!: string;
  @Input() icon!: string;
  @Input() placeholder!: string;
  @Input() check!: string;
  @Input() value!: string;

  constructor() { }

  ngOnInit(): void {
  }

}
