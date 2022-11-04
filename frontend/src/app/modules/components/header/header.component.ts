import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  logo: string = "/assets/images/logo.svg";
  target: any = "";
  cond: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

}
