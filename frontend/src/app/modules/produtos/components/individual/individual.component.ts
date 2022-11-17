import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarrinhoService } from 'src/app/modules/carrinho/service/carrinho.service';

import { SwiperOptions } from 'swiper';
import { ICor } from '../../model/ICor';
import { IProduto, IProdutoCarrinho, IProdutoDetalhe } from '../../model/IProduto';

import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-individual',
  templateUrl: './individual.component.html',
  styleUrls: ['./individual.component.scss']
})
export class IndividualComponent implements OnInit {
  // Produto
  produto!: IProdutoDetalhe;

  // Produtos Slide
  produtosGenero: IProduto[] = [];
  produtosCategoria: IProduto[] = [];

  // Produto Vars
  price!: number;
  colors: ICor[] = [];
  stockQuantity!: number; // Stock Quantity
  installment: number = 12;

  // Produto Carrinho Vars
  tamanhoSelected!: string;
  corSelected!: string;
  precoSelected!: number;
  quantidade: number = 1;

  // Slides Vars
  thumbSlide!: SwiperOptions;
  commentaryGallery!: SwiperOptions;
  mostSaleSlide!: SwiperOptions;

  // Image Active Vars
  pathImage: string = 'assets/produtos/';
  imageActive!: string;
  fullPathImage!: string;

  // Toggle Info Produto Vars
  sideDescription?: boolean;
  sideDetail?: boolean;

  constructor(
    private activatedRoute: ActivatedRoute,
    private produtoService: ProdutoService,
    private router: Router,
    private carrinhoService: CarrinhoService
  ) { }

  ngOnInit(): void {
    this.getProdutoDescription();
    this.initializeSlides();

    this.sideDescription = false;
    this.sideDetail = false;
  }

  // Methods Get Produto Database
  getProduto() {
    const params = this.activatedRoute.snapshot.paramMap;
    const nome = params.get('nome')!;
    const id = Number(params.get('id'));

    return new Promise(resolve => {
      this.produtoService.findProdutoByNomeAndId(nome, id).subscribe(
        data => {
          resolve(data);
        }
      )
    })
  }

  getProdutoGenero(genero: string[]) {
    return new Promise(resolve => {
      this.produtoService.findProdutos(genero).subscribe(
        data => {
          resolve(data)
        }
      )
    })
  }

  getProdutosCategoria(genero: string[], categoria: string[]) {
    return new Promise(resolve => {
      this.produtoService.findProdutos(genero, categoria).subscribe(
        data => {
          resolve(data)
        }
      )
    })
  }

  async getProdutoDescription() {
    this.produto = <IProdutoDetalhe>await this.getProduto();
    this.produtosGenero = <IProduto[]>await this.getProdutoGenero([this.produto.genero.descricao]);
    this.produtosCategoria = <IProduto[]>await this.getProdutosCategoria([this.produto.genero.descricao], [this.produto.categoria.descricao]);

    // Root Image
    this.pathImage += this.replaceAll(this.produto.genero.descricao, ' ', '-') + '/';
    this.pathImage += this.replaceAll(this.produto.categoria.descricao, ' ', '-') + '/';
    this.pathImage += this.replaceAll(this.produto.tipo.descricao, ' ', '-') + '/';

    // Name Image
    this.changeImageActive(this.produto.imagens[0].nome, this.produto.imagens[0].tipo);
    
    // Colors Produto and Price Minimun Produto
    this.produto.estoques.forEach((value, index) => {
      
      if (index == 0) {
        this.colors.push(value.cor);
      } else if (index < this.produto.estoques.length) {
        if (!(this.produto.estoques[index].cor.descricao == this.produto.estoques[index++].cor.descricao)) {
          this.colors.push(value.cor);
        }
      }

      if (index == 0 || this.price < value.preco) {
        this.price = value.preco;
      }

    });
  }

  // Methods Manipulation Images
  changeImageActive(nome: string, tipo: string) {
    this.imageActive = nome + '.' + tipo;

    this.fullPathImage = this.pathImage + this.imageActive;
  }

  isImageActive(nome: string, tipo: string): boolean {
    return this.imageActive == (nome + '.' + tipo);
  }

  // Methods Slides
  initializeSlides() {
    // Slide Vars
    this.thumbSlide = {
      direction: 'horizontal',
      slidesPerView: 3,
      spaceBetween: 10,
      scrollbar: { draggable: true },

      breakpoints: {
        581: {
          direction: 'vertical',
          spaceBetween: 30,
        }
      }
    }

    this.commentaryGallery = {
      direction: 'horizontal',
      slidesPerView: 1,
      spaceBetween: 30,
      scrollbar: { draggable: true },

      breakpoints: {
        510: {
          slidesPerView: 2,
        }
      }
    }

    this.mostSaleSlide = {
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

  prevSlide(target: any) {
    target.swiperRef.slidePrev();
  }

  nextSlide(target: any) {
    target.swiperRef.slideNext();
  }

  // Methods Produto Carrinho Vars
  getQuantity() {
    if (this.tamanhoSelected != undefined && this.corSelected != undefined) {
      this.produto.estoques.forEach(estoque => {
        if (estoque.tamanho.descricao == this.tamanhoSelected && estoque.cor.descricao == this.corSelected) {
          this.stockQuantity = estoque.quantidade;
          this.precoSelected = estoque.preco;
        }
      })
    }
  }

  setTamanho(value: string) {
    this.tamanhoSelected = value;
  }

  setCor(value: string) {
    this.corSelected = value;
  }

  // Methods Breadcrumb
  goToProdutos(genero: string, categoria?: string, tipo?: string, marca?: string) {
    let query = {};
    genero = this.replaceAll(genero, ' ', '-');

    query = { ...query, genero: genero };

    if (categoria != undefined) {
      categoria = this.replaceAll(categoria, ' ', '-');
      query = { ...query, categoria: categoria }
    }

    if (tipo != undefined) {
      tipo = this.replaceAll(tipo, ' ', '-');
      query = { ...query, tipo: tipo };
    }

    if (marca != undefined) {
      marca = this.replaceAll(marca, ' ', '-');
      query = { ...query, marca: marca }
    }

    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['produtos'], { queryParams: query });
    });
  }

  // Methods Add Carrinho
  addProdutoCarrinho() {

    if (this.tamanhoSelected != undefined && this.corSelected != undefined) {
      const produtoCarrinho: IProdutoCarrinho = {
        id: this.produto.id,
        nome: this.produto.nome,
        genero: this.produto.genero,
        categoria: this.produto.categoria,
        tipo: this.produto.tipo,
        estoques: this.produto.estoques,
        marca: this.produto.marca,

        largura: this.produto.largura,
        altura: this.produto.altura,
        comprimento: this.produto.comprimento,
        peso: this.produto.peso,

        quantidade: this.quantidade,
        tamanhoSelecionado: this.tamanhoSelected,
        corSelecionado: this.corSelected,
        precoSelecionado: this.precoSelected
      }

      this.carrinhoService.addProdutoCarrinho(produtoCarrinho);
    }

  }

  replaceAll(str: string, find: string, replace: string) {
    str = str.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    return str.replace(new RegExp(find, 'g'), replace);
  }
}
