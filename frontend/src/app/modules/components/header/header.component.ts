import { Component, OnInit, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  logo: string = "/assets/images/logo.svg";
  
  femininoHighlight = "/assets/images/feminino-highlights-drop-menu.webp";

  target: any;
  isNavbar!: boolean;

  constructor(private renderer: Renderer2) {
    this.renderer.listen('window', 'scroll', this.clearTarget.bind(this));
  }

  ngOnInit(): void {
    this.clearTarget();
  }

  clearTarget() {
    this.target = "";
    this.isNavbar = false;
  }

}
