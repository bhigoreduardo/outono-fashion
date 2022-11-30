import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IProdutoModel } from 'src/app/model/IProduto';
import { SwiperOptions } from 'swiper';
import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-genero',
  templateUrl: './genero.component.html',
  styleUrls: ['./genero.component.scss']
})
export class GeneroComponent implements OnInit {

  // Banner Vars
  bannerSlide!: SwiperOptions;
  bannerImages: any[] = [];

  // Known Vars
  categoriaImages: any[] = [];

  // Ads Vars
  adsImages: any[] = [];

  // New Produto Vars
  newProdutos!: SwiperOptions;
  produtosNew: IProdutoModel[] = [];

  // Offer Vars
  offerImages: any[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private produtoService: ProdutoService
  ) { }

  ngOnInit(): void {
    this.findElementosGenero();
    this.initializeSlides();
  }

  public replaceAll(str: string, find: string, replace: string) {
    str = str.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    return str.replace(new RegExp(find, 'g'), replace);
  }

  prevSlide(target: any): void {
    target.swiperRef.slidePrev();
  }

  nextSlide(target: any): void {
    target.swiperRef.slideNext();
  }

  initializeSlides(): void {
    this.bannerSlide = {
      direction: 'horizontal',
      slidesPerView: 1,
      spaceBetween: 0,
      scrollbar: { draggable: true }
    };

    this.newProdutos = {
      direction: 'horizontal',
      slidesPerView: 1,
      spaceBetween: 30,
      scrollbar: { draggable: true },
      breakpoints: {
        470: {
          slidesPerView: 2,
        },
        730: {
          slidesPerView: 3,
        },
        1100: {
          slidesPerView: 4,
        }
      }
    }
  }

  findProdutosGenero(genero: string[], order: string) {
    return new Promise(resolve => {
      this.produtoService.findProdutos(genero, [''], [''], [''], [''], [''], undefined, undefined, order).subscribe(
        data => {
          resolve(data)
        }
      )
    })
  }

  async findElementosGenero() {
    const params = this.activatedRoute.snapshot.paramMap;
    const genero = params.get('genero')!;

    if (genero == 'masculino') {
      this.bannerImages = [
        { nome: '/assets/images/genero/banner-masculino-1.gif', genero: 'masculino', categoria: 'roupas', tipo: 'bermudas' },
        { nome: '/assets/images/genero/banner-masculino-2.gif', genero: 'masculino', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/banner-masculino-3.gif', genero: 'masculino', categoria: 'calcados', tipo: 'tenis' },
        { nome: '/assets/images/genero/banner-masculino-4.gif', genero: 'masculino', categoria: 'acessorios', tipo: 'bones' }
      ];
      this.categoriaImages = [
        { nome: '/assets/images/genero/categoria-masculino-1.webp', genero: 'masculino', categoria: 'calcados', tipo: 'tenis' },
        { nome: '/assets/images/genero/categoria-masculino-2.webp', genero: 'masculino', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/categoria-masculino-3.webp', genero: 'masculino', categoria: 'acessorios', tipo: 'relogios' },
        { nome: '/assets/images/genero/categoria-masculino-4.webp', genero: 'masculino', categoria: 'roupas', tipo: 'cuecas' },
        { nome: '/assets/images/genero/categoria-masculino-5.webp', genero: 'masculino', categoria: 'acessorios', tipo: 'perfumes' },
        { nome: '/assets/images/genero/categoria-masculino-6.webp', genero: 'masculino', categoria: 'roupas', tipo: 'camisas' }
      ];
      this.adsImages = [
        { nome: '/assets/images/genero/ads-masculino-1.gif', genero: 'masculino', categoria: 'acessorios', tipo: 'relogios' },
        { nome: '/assets/images/genero/ads-masculino-2.gif', genero: 'masculino', categoria: 'calcados', tipo: 'chinelos' }
      ];
      this.produtosNew = <IProdutoModel[]>await this.findProdutosGenero(['masculino'], 'novos');
      this.offerImages = [
        { nome: '/assets/images/genero/offer-masculino-1.webp', genero: 'masculino', categoria: 'acessorios' },
        { nome: '/assets/images/genero/offer-masculino-2.webp', genero: 'masculino', categoria: 'roupas', tipo: 'calcas' },
        { nome: '/assets/images/genero/offer-masculino-3.webp', genero: 'masculino', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/offer-masculino-4.webp', genero: 'masculino', categoria: 'roupas', tipo: 'cuecas' }
      ];
    } else if (genero == 'feminino') {
      this.bannerImages = [
        { nome: '/assets/images/genero/banner-feminino-1.webp', genero: 'feminino', categoria: 'roupas' },
        { nome: '/assets/images/genero/banner-feminino-2.webp', genero: 'feminino', categoria: 'calcados' },
        { nome: '/assets/images/genero/banner-feminino-3.webp', genero: 'feminino', categoria: 'acessorios' },
        { nome: '/assets/images/genero/banner-feminino-4.webp', genero: 'feminino', categoria: 'calcados' }
      ];
      this.categoriaImages = [
        { nome: '/assets/images/genero/categoria-feminino-1.webp', genero: 'feminino', categoria: 'calcados', tipo: 'sandalias' },
        { nome: '/assets/images/genero/categoria-feminino-2.webp', genero: 'feminino', categoria: 'calcados', tipo: 'chinelos' },
        { nome: '/assets/images/genero/categoria-feminino-3.webp', genero: 'feminino', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/categoria-feminino-4.webp', genero: 'feminino', categoria: 'roupas', tipo: 'calcas' },
        { nome: '/assets/images/genero/categoria-feminino-5.webp', genero: 'feminino', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/categoria-feminino-6.webp', genero: 'feminino', categoria: 'calcados', tipo: 'tenis' }
      ];
      this.adsImages = [
        { nome: '/assets/images/genero/ads-feminino-1.gif', genero: 'feminino', categoria: 'acessorios', tipo: 'perfumes' },
        { nome: '/assets/images/genero/ads-feminino-2.gif', genero: 'feminino', categoria: 'calcados', tipo: 'sandalias' }
      ];
      this.produtosNew = <IProdutoModel[]>await this.findProdutosGenero(['feminino'], 'novos');
      this.offerImages = [
        { nome: '/assets/images/genero/offer-masculino-1.webp', genero: 'masculino', categoria: 'acessorios' },
        { nome: '/assets/images/genero/offer-masculino-2.webp', genero: 'masculino', categoria: 'roupas', tipo: 'calcas' },
        { nome: '/assets/images/genero/offer-masculino-3.webp', genero: 'masculino', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/offer-masculino-4.webp', genero: 'masculino', categoria: 'roupas', tipo: 'cuecas' }
      ];
    } else if (genero == 'infantil') {
      this.bannerImages = [
        { nome: '/assets/images/genero/banner-infantil-1.webp', genero: 'infantil', categoria: 'roupas' },
        { nome: '/assets/images/genero/banner-infantil-2.webp', genero: 'infantil', categoria: 'calcados' },
        { nome: '/assets/images/genero/banner-infantil-3.webp', genero: 'infantil', categoria: 'calcados' },
        { nome: '/assets/images/genero/banner-infantil-4.webp', genero: 'infantil', categoria: 'roupas' }
      ];
      this.categoriaImages = [
        { nome: '/assets/images/genero/categoria-infantil-1.webp', genero: 'infantil', categoria: 'calcados', tipo: 'sandalias' },
        { nome: '/assets/images/genero/categoria-infantil-2.webp', genero: 'infantil', categoria: 'calcados', tipo: 'chinelos' },
        { nome: '/assets/images/genero/categoria-infantil-3.webp', genero: 'infantil', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/categoria-infantil-4.webp', genero: 'infantil', categoria: 'calcados', tipo: 'tenis' },
        { nome: '/assets/images/genero/categoria-infantil-5.webp', genero: 'infantil', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/categoria-infantil-6.webp', genero: 'infantil', categoria: 'roupas', tipo: 'vestidos' }
      ];
      this.adsImages = [
        { nome: '/assets/images/genero/ads-infantil-1.gif', genero: 'infantil', categoria: 'roupas' },
        { nome: '/assets/images/genero/ads-infantil-2.gif', genero: 'infantil', categoria: 'calcados' }
      ];
      this.produtosNew = <IProdutoModel[]>await this.findProdutosGenero(['infantil'], 'novos');
      this.offerImages = [
        { nome: '/assets/images/genero/offer-masculino-1.webp', genero: 'masculino', categoria: 'acessorios' },
        { nome: '/assets/images/genero/offer-masculino-2.webp', genero: 'masculino', categoria: 'roupas', tipo: 'calcas' },
        { nome: '/assets/images/genero/offer-masculino-3.webp', genero: 'masculino', categoria: 'roupas', tipo: 'camisas' },
        { nome: '/assets/images/genero/offer-masculino-4.webp', genero: 'masculino', categoria: 'roupas', tipo: 'cuecas' }
      ];
    }
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

}
