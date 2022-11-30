import { Component, OnInit, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { IMarcaModel } from 'src/app/model/IMarca';
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
  masculinoHighlight = "/assets/images/masculino-highlights-drop-menu.webp";
  femininoHighlight = "/assets/images/feminino-highlights-drop-menu.webp";
  
  meninoHighlight = "/assets/images/menino-highlights-drop-menu.webp";
  meninaHighlight = "/assets/images/menina-highlights-drop-menu.webp";
  
  calcadosMasculino: ITipoModel[] = [];
  roupasMasculino: ITipoModel[] = [];
  acessoriosMasculino: ITipoModel[] = [];

  calcadosFeminino: ITipoModel[] = [];
  roupasFeminino: ITipoModel[] = [];
  acessoriosFeminino: ITipoModel[] = [];

  calcadosMenino: ITipoModel[] = [];
  roupasMenino: ITipoModel[] = [];
  acessoriosMenino: ITipoModel[] = [];

  calcadosMenina: ITipoModel[] = [];
  roupasMenina: ITipoModel[] = [];
  acessoriosMenina: ITipoModel[] = [];

  marcasMasculino: IMarcaModel[] = [];
  marcasFeminino: IMarcaModel[] = [];
  marcasMenino: IMarcaModel[] = [];
  marcasMenina: IMarcaModel[] = [];

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

  goToProdutos(genero: string, categoria?: string, tipo?: string, marca?: string) {
    let query = {};

    query = { ...query, genero: genero };

    if (categoria != undefined) {
      categoria = this.replaceAll(categoria, ' ', '-');
      query = { ...query, categoria: categoria };
    }

    if (tipo != undefined) {
      tipo = this.replaceAll(tipo, ' ', '-');
      query = { ...query, tipo: tipo };
    }

    if (marca != undefined) {
      marca = this.replaceAll(marca, ' ', '-');
      query = { ...query, marca: marca };
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

  findByGenero(genero: string) {
    return new Promise(resolve => {
      this.headerService.findByGenero(genero).subscribe(
        data => {
          resolve(data);
        }
      )
    })
  }

  async initializeDropVars() {
    this.calcadosMasculino = <ITipoModel[]>await this.findByGeneroAndCategoria('masculino', 'calcados');
    this.roupasMasculino = <ITipoModel[]>await this.findByGeneroAndCategoria('masculino', 'roupas');
    this.acessoriosMasculino = <ITipoModel[]>await this.findByGeneroAndCategoria('masculino', 'acessorios');

    this.calcadosFeminino = <ITipoModel[]>await this.findByGeneroAndCategoria('feminino', 'calcados');
    this.roupasFeminino = <ITipoModel[]>await this.findByGeneroAndCategoria('feminino', 'roupas');
    this.acessoriosFeminino = <ITipoModel[]>await this.findByGeneroAndCategoria('feminino', 'acessorios');

    this.calcadosMenino = <ITipoModel[]>await this.findByGeneroAndCategoria('menino', 'calcados');
    this.roupasMenino = <ITipoModel[]>await this.findByGeneroAndCategoria('menino', 'roupas');
    this.acessoriosMenino = <ITipoModel[]>await this.findByGeneroAndCategoria('menino', 'acessorios');

    this.calcadosMenina = <ITipoModel[]>await this.findByGeneroAndCategoria('menina', 'calcados');
    this.roupasMenina = <ITipoModel[]>await this.findByGeneroAndCategoria('menina', 'roupas');
    this.acessoriosMenina = <ITipoModel[]>await this.findByGeneroAndCategoria('menina', 'acessorios');

    this.marcasMasculino = <IMarcaModel[]>await this.findByGenero('masculino');
    this.marcasFeminino = <IMarcaModel[]>await this.findByGenero('feminino');
    this.marcasMenino = <IMarcaModel[]>await this.findByGenero('menino');
    this.marcasMenina = <IMarcaModel[]>await this.findByGenero('menina');
  }

}
