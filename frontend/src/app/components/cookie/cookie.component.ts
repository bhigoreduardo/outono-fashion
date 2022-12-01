import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-cookie',
  templateUrl: './cookie.component.html',
  styleUrls: ['./cookie.component.scss']
})
export class CookieComponent implements OnInit {

  cookieBoolean!: Boolean;

  @Output() setPolicy =  new EventEmitter();

  constructor() { }

  ngOnInit(): void {
    this.getCookie();
  }

  setCookie(): void {
    localStorage.setItem('cookie', JSON.stringify(false));
    this.getCookie();
  }

  getCookie(): void {
    this.cookieBoolean = JSON.parse(localStorage.getItem('cookie')!) == null;
  }

}
