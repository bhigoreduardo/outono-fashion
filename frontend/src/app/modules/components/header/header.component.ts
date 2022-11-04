import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  logo: string = "/assets/images/logo.svg";
  
  femininoHighlight = "/assets/images/feminino-highlights-drop-menu.webp";

  target: any;
  isNavbar: boolean;

  constructor() {
    this.target = "";
    this.isNavbar = false;
  }

  ngOnInit(): void {
  }

}
