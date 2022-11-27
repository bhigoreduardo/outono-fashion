import { Component, OnInit, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { ITipoModel } from 'src/app/model/ITipo';
import { IUsuarioModel } from 'src/app/model/IUsuario';
import { CarrinhoService } from 'src/app/modules/carrinho/service/carrinho.service';
import { LoginService } from 'src/app/modules/login/service/login.service';
import { HeaderService } from './service/header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  logo: string = "/assets/images/logo.svg";

  // Usuário Vars
  login!: Boolean;
  userInfoDropBoolean!: Boolean;
  usuarioModel!: IUsuarioModel;

  // Navbar Vars
  navbarToggleBoolean!: boolean;
  target: any;

  // Drop Vars
  femininoHighlight = "/assets/images/feminino-highlights-drop-menu.webp";
  calcadosFeminino: ITipoModel[] = [];

  constructor(
    private loginService: LoginService,
    private carrinhoService: CarrinhoService,
    private renderer: Renderer2,
    private headerService: HeaderService,
    private router: Router
  ) {
    // onScroll Event
    this.renderer.listen('window', 'scroll', this.clearTarget.bind(this));
  }

  ngOnInit(): void {
    this.initializeVars();
    this.clearTarget();
    this.initializeDropVars();
  }

  public replaceAll(str: string, find: string, replace: string) {
    str = str.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    return str.replace(new RegExp(find, 'g'), replace);
  }

  initializeVars(): void {
    this.login = this.loginService.islogin();
    this.usuarioModel = JSON.parse(localStorage.getItem('usuarioModel')!);
  }

  loggout(): void {
    this.loginService.loggout();
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([]);
    });
  }

  redirectTo(link: string): void {
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([link]);
    });
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

  getQuantidadeProdutos(): number {
    return this.carrinhoService.getQuantidadeProdutos();
  }

  clearTarget(): void {
    this.target = "";
    this.navbarToggleBoolean = false;
  }

  findByGeneroAndCategoria(genero: string, categoria: string) {
    return new Promise(resolve => {
      this.headerService.findByGeneroAndCategoria(genero, categoria).subscribe(
        data => {
          resolve(data);
        }
      )
    })
  }

  async initializeDropVars() {
    this.calcadosFeminino = <ITipoModel[]>await this.findByGeneroAndCategoria('feminino', 'calcados');
  }

}
