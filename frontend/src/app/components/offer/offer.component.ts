import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.scss']
})
export class OfferComponent implements OnInit {

  logo: string = '/assets/images/logo.svg';
  offerBoolean!: Boolean;

  constructor() { }

  ngOnInit(): void {
    this.getOffer();
  }

  setOffer(): void {
    localStorage.setItem('offer', JSON.stringify(false));
    this.getOffer();
  }

  getOffer(): void {
    this.offerBoolean = JSON.parse(localStorage.getItem('offer')!) == null;
  }

}
