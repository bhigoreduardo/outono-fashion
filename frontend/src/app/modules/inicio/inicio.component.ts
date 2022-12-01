import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IProdutoModel } from 'src/app/model/IProduto';
import { SwiperOptions } from 'swiper';
import { ProdutoService } from '../produtos/service/produto.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent implements OnInit {

  // Banner Vars
  bannerSlide!: SwiperOptions;
  bannerImages: any[] = [];

  offerData: any[] = [];

  // New Produto Vars
  newProdutos!: SwiperOptions;
  produtosNewFeminino: IProdutoModel[] = [];
  produtosNewMasculino: IProdutoModel[] = [];

  bannerPromotion: string = '/assets/images/banner-promotion.png';

  constructor(
    private router: Router,
    private produtoService: ProdutoService
  ) { }

  ngOnInit(): void {
    this.initializeVars();
    this.initializeSlides();
    this.findProdutosGeneroAsync();
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

  initializeVars(): void {
    this.bannerImages = [
      { nome: '/assets/images/banner-1.gif', genero: 'feminino', categoria: 'roupas' },
      // { nome: '/assets/images/banner-2.gif', genero: 'masculino', categoria: 'roupas', tipo: 'camisas' },
      // { nome: '/assets/images/banner-3.gif', genero: 'masculino', categoria: 'calcados', tipo: 'tenis' },
      // { nome: '/assets/images/banner-4.gif', genero: 'masculino', categoria: 'acessorios', tipo: 'bones' }
    ];
    this.offerData = [
      { h5: 'Blusas Top Marcas', span: 'Até 70% Off', image: '/assets/images/offer-1.webp', item: 'item-1', genero: 'feminino', categoria: 'roupas' },
      { h5: 'Seleção das melhores calças', span: 'Até 60% Off', image: '/assets/images/offer-2.webp', item: 'item-2', genero: 'masculino', categoria: 'roupas', tipo: 'calcas' },
      { h5: 'Moletons e malhas top marcas', span: 'Até 45% Off', image: '/assets/images/offer-3.webp', item: 'item-3', genero: 'feminino', categoria: 'roupas', tipo: 'moletons' },
      { h5: 'Acessórios de luxo', span: 'Até 30% Off', image: '/assets/images/offer-4.webp', item: 'item-4', genero: 'feminino', categoria: 'acessorios' },
      { h5: 'Camisetas à partir de R$49,99', span: 'Até 50% Off', image: '/assets/images/offer-5.webp', item: 'item-5', genero: 'masculino', categoria: 'roupas' }
    ]
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

  findProdutosGenero(genero: string[], order: string) {
    return new Promise(resolve => {
      this.produtoService.findProdutos(genero, [''], [''], [''], [''], [''], undefined, undefined, order).subscribe(
        data => {
          resolve(data)
        }
      )
    })
  }

  async findProdutosGeneroAsync() {
    this.produtosNewFeminino = <IProdutoModel[]>await this.findProdutosGenero(['feminino'], 'novos');
    this.produtosNewMasculino = <IProdutoModel[]>await this.findProdutosGenero(['masculino'], 'novos');
  }

}
