import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-policy',
  templateUrl: './policy.component.html',
  styleUrls: ['./policy.component.scss']
})
export class PolicyComponent implements OnInit {

  logo: string = '/assets/images/logo.svg';
  targetInformation!: string;
  @Input() policyBoolean!: Boolean;

  @Output() setPolicy =  new EventEmitter();

  constructor() { }

  ngOnInit(): void {
    this.targetInformation = 'cookie';
  }

}
