import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IComentarioModel } from 'src/app/model/IComentario';
import { ICorModel } from 'src/app/model/ICor';
import { IEstoqueModel } from 'src/app/model/IEstoque';
import { IImagemModel } from 'src/app/model/IImagem';
import { IItemPedidoInput } from 'src/app/model/IItemPedido';
import { IProdutoDetalheModel, IProdutoModel } from 'src/app/model/IProduto';
import { CarrinhoService } from 'src/app/modules/carrinho/service/carrinho.service';
import { SwiperOptions } from 'swiper';


import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-individual',
  templateUrl: './individual.component.html',
  styleUrls: ['./individual.component.scss']
})
export class IndividualComponent implements OnInit {

  // Produto Vars
  produto!: IProdutoDetalheModel;
  produtosGenero: IProdutoModel[] = [];
  produtosCategoria: IProdutoModel[] = [];

  // Produto Props
  genero!: string;
  categoria!: string;
  tipo!: string;
  marca!: string;
  imagens: IImagemModel[] = [];
  nome!: string;
  estoques: IEstoqueModel[] = [];
  descricao!: string;
  detalhe!: string;
  cores: ICorModel[] = [];
  quantidadeEstoque!: number;
  preco!: number;
  precoSelected!: number;
  parcela: number = 12;
  comentarios: IComentarioModel[] = [];

  // Image Vars
  pathImage: string = 'assets/produtos/';
  imageActive!: string;
  fullPathImage: string = '';

  // Item Pedido Vars
  tamanhoId!: number;
  corId!: number;
  quantidade: number = 1;
  message!: string;

  // Toggle Info Produto Vars
  sideDescription!: boolean;
  sideDetail!: boolean;

  // Slides Vars
  thumbSlide!: SwiperOptions;
  commentaryGallery!: SwiperOptions;
  mostSaleSlide!: SwiperOptions;

  constructor(
    private activatedRoute: ActivatedRoute,
    private produtoService: ProdutoService,
    private router: Router,
    private carrinhoService: CarrinhoService
  ) { }

  ngOnInit(): void {
    this.sideDescription = false;
    this.sideDetail = false;
    this.findProdutos();
    this.initializeSlides();
  }

  replaceAll(str: string, find: string, replace: string): string {
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

  findProduto() {
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

  findProdutosGenero(genero: string[]) {
    return new Promise(resolve => {
      this.produtoService.findProdutos(genero).subscribe(
        data => {
          resolve(data)
        }
      )
    })
  }

  findProdutosCategoria(genero: string[], categoria: string[]) {
    return new Promise(resolve => {
      this.produtoService.findProdutos(genero, categoria).subscribe(
        data => {
          resolve(data)
        }
      )
    })
  }

  async findProdutos() {
    this.produto = <IProdutoDetalheModel>await this.findProduto();
    this.produtosGenero = <IProdutoModel[]>await this.findProdutosGenero([this.produto.genero.descricao]);
    this.produtosCategoria = <IProdutoModel[]>await this.findProdutosCategoria([this.produto.genero.descricao], [this.produto.categoria.descricao]);

    this.initializeImage();
    this.initializeProdutoProps();
  }

  isImageActive(nome: string, tipo: string): boolean {
    return this.imageActive == (nome + '.' + tipo);
  }

  changeImageActive(nome: string, tipo: string): void {
    this.imageActive = nome + '.' + tipo;

    this.fullPathImage = this.pathImage + this.imageActive;
  }

  initializeImage(): void {
    // Root Image
    this.pathImage += this.replaceAll(this.produto.genero.descricao, ' ', '-') + '/';
    this.pathImage += this.replaceAll(this.produto.categoria.descricao, ' ', '-') + '/';
    this.pathImage += this.replaceAll(this.produto.tipo.descricao, ' ', '-') + '/';

    // Name Image
    this.changeImageActive(this.produto.imagens[0].nome, this.produto.imagens[0].tipo);
  }

  initializeProdutoProps(): void {
    this.produto!.estoques.forEach((value, index) => {

      if (index == 0) {
        this.cores.push(value.cor);
      } else if (index < this.produto.estoques.length) {
        if (!(this.produto.estoques[index].cor.descricao == this.produto.estoques[index++].cor.descricao)) {
          this.cores.push(value.cor);
        }
      }

      if (index == 0 || this.preco < value.preco) {
        this.preco = value.preco;
      }

    });

    this.genero = this.produto.genero.descricao;
    this.categoria = this.produto.categoria.descricao;
    this.tipo = this.produto.tipo.descricao;
    this.marca = this.produto.marca.descricao;
    this.imagens = this.produto.imagens;
    this.nome = this.produto.nome;
    this.estoques = this.produto.estoques;
    this.descricao = this.produto.descricao;
    this.detalhe = this.produto.detalhe;
    this.comentarios = this.produto.comentarios;
  }

  goToProdutos(genero: string, categoria?: string, tipo?: string, marca?: string): void {
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

  setTamanho(tamanhoId: number): void {
    this.tamanhoId = tamanhoId;
  }

  setCor(corId: number): void {
    this.corId = corId;
  }

  setQuantidadeEstoque(): void {
    if (this.tamanhoId != undefined && this.corId != undefined) {
      this.produto.estoques.forEach(estoque => {
        if (estoque.tamanho.id == this.tamanhoId && estoque.cor.id == this.corId) {
          this.quantidadeEstoque = estoque.quantidade;
          this.precoSelected = estoque.preco;
        }
      })
    }
  }

  addItemPedido(): void {

    if (this.tamanhoId != undefined && this.corId != undefined) {
      const itemPedidoInput: IItemPedidoInput = {
        produto: { id: this.produto.id },
        tamanho: { id: this.tamanhoId },
        cor: { id: this.corId },
        quantidade: this.quantidade,
        observacao: ''
      }

      this.carrinhoService.addItemPedido(itemPedidoInput);
      this.message = "Produto adicionado ao carrinho";
    }

  }

}
