import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-checkout',
  templateUrl: './header-checkout.component.html',
  styleUrls: ['./header-checkout.component.scss']
})
export class HeaderCheckoutComponent implements OnInit {

  logo: string = '/assets/images/logo.svg';

  constructor() { }

  ngOnInit(): void {
  }

}
