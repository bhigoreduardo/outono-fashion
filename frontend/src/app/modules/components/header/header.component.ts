import { Component, OnInit, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { ITipo } from '../../produtos/model/ITipo';
import { HeaderService } from './service/header.service';

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

  // Feminino Calçados
  femininoCalcados: ITipo[] = [];

  constructor(
    private renderer: Renderer2,
    private headerService: HeaderService,
    private router: Router
  ) {
    // onScroll Event
    this.renderer.listen('window', 'scroll', this.clearTarget.bind(this));
  }

  ngOnInit(): void {
    this.clearTarget();

    this.initializeTiposVars();
  }

  clearTarget() {
    this.target = "";
    this.isNavbar = false;
  }

  initializeTiposVars() {
    // Feminino Calçados
    this.headerService.findByGeneroAndCategoria('feminino', 'calcados').subscribe(
      data => { this.femininoCalcados = data },
      error => { console.log(error.error.message) }
    );
  }

  goToProdutos(genero: string, categoria: string, tipo?: string) {
    let query = {};

    query = { ...query, genero: genero, categoria: categoria };

    if (tipo != undefined) {
      tipo = this.replaceAll(tipo, ' ', '-');
      query = { ...query, tipo: tipo };
    }

    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['produtos'], { queryParams: query });
    });
  }

  public replaceAll(str: string, find: string, replace: string) {
    str = str.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    return str.replace(new RegExp(find, 'g'), replace);
  }

}
