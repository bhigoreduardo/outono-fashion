import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, retry } from 'rxjs';

import SwiperCore, { SwiperOptions } from 'swiper';
import { ICategoria } from './model/ICategoria';
import { ICor } from './model/ICor';
import { IGenero } from './model/IGenero';
import { IMarca } from './model/IMarca';
import { IProduto } from './model/IProduto';
import { ITamanho } from './model/ITamanho';
import { ITipo } from './model/ITipo';
import { ProdutoService } from './service/produto.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {
  // Produtos
  produtos: IProduto[] = [];
  produtosList: IProduto[] = [];

  generos: IGenero[] = [];
  categorias: ICategoria[] = [];
  tipos: ITipo[] = [];
  cores: ICor[] = [];
  tamanhos: ITamanho[] = [];
  marcas: IMarca[] = [];

  order: string = 'filtrar';

  page!: number;
  size!: number;
  pages!: number;
  arrPages: number[] = [];

  begin!: number;
  end!: number;

  // Toggle Side bar
  sideGenero!: boolean;
  sideCategoria!: boolean;
  sideTipo!: boolean;
  sideTamanho!: boolean;
  sideCor!: boolean;
  sideMarca!: boolean;
  sidePreco!: boolean;

  // Open Side bar
  sideBars!: boolean;

  // Loaded Produtos
  loaderGif: string = '/assets/icons/loader.gif';
  loader!: boolean;

  // URL Values
  genero: string[] = [];
  categoria: string[] = [];
  tipo: string[] = [];
  cor: string[] = [];
  tamanho: string[] = [];
  marca: string[] = [];

  constructor(
    private produtoService: ProdutoService,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.initializeVars();

    const queryParams = this.activatedRoute.snapshot.queryParamMap;

    // Get URL and Replace Queries Values
    this.genero = queryParams.getAll('genero').map(value => this.replaceAll(value, '-', ' '));
    this.categoria = queryParams.getAll('categoria').map(value => this.replaceAll(value, '-', ' '));
    this.tipo = queryParams.getAll('tipo').map(value => this.replaceAll(value, '-', ' '));
    this.cor = queryParams.getAll('cor').map(value => this.replaceAll(value, '-', ' '));
    this.tamanho = queryParams.getAll('tamanho').map(value => this.replaceAll(value, '-', ' '));
    this.marca = queryParams.getAll('marca').map(value => this.replaceAll(value, '-', ' '));

    // Get URL Page Values
    this.page = (Number(queryParams.get('page')) == 0) ? 1 : Number(queryParams.get('page'));
    this.size = (Number(queryParams.get('size')) == 0) ? 4 : Number(queryParams.get('size'));

    this.begin = (this.page - 1) * this.size;
    this.end = this.size + this.begin;

    // Get All Produtos
    this.findProdutos(this.genero, this.categoria, this.tipo, this.cor, this.tamanho, this.marca);
  }

  public replaceAll(str: string, find: string, replace: string) {
    str = str.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    return str.replace(new RegExp(find, 'g'), replace);
  }

  initializeVars() {
    this.sideGenero = false;
    this.sideCategoria = false;
    this.sideTipo = false;
    this.sideTamanho = false;
    this.sideCor = false;
    this.sideMarca = false;
    this.sidePreco = false;

    this.sideBars = false;

    this.loader = true;
  }

  findProdutos(genero?: string[], categoria?: string[], tipo?: string[],
    cor?: string[], tamanho?: string[], marca?: string[]) {
    this.produtoService.findProdutos(genero, categoria, tipo, cor, tamanho, marca).subscribe(
      data => {

        this.produtos = data;
        this.pages = Math.ceil(this.produtos.length / this.size);

        for (let i = 1; i <= this.pages; i++) {
          this.arrPages.push(i);
        }

        this.produtosList = this.produtos.filter((value, key) => key >= this.begin && key < this.end);

      }, error => {
        console.log(error.error.message);
      }
    );

    setTimeout(() => {

      this.getCaracteristicaProdutos();
      this.loader = false;

    }, 1000);
  }

  getCaracteristicaProdutos() {
    for (let produto of this.produtos) {
      // Gênero
      if (!(this.generos.findIndex(genero => genero.descricao == produto.genero.descricao) != -1)) {
        this.generos.push(produto.genero);
      }

      // Categoria
      if (!(this.categorias.findIndex(categoria => categoria.descricao == produto.categoria.descricao) != -1)) {
        this.categorias.push(produto.categoria);
      }

      // Tipo
      if (!(this.tipos.findIndex(tipo => tipo.descricao == produto.tipo.descricao) != -1)) {
        this.tipos.push(produto.tipo);
      }

      // Marca
      if (!(this.marcas.findIndex(marca => marca.descricao == produto.marca.descricao) != -1)) {
        this.marcas.push(produto.marca);
      }

      for (let estoque of produto.estoques) {
        // Cor
        if (!(this.cores.findIndex(cor => cor.descricao == estoque.cor.descricao) != -1)) {
          this.cores.push(estoque.cor);
        }

        // Tamanho
        if (!(this.tamanhos.findIndex(tamanho => tamanho.descricao == estoque.tamanho.descricao) != -1)) {
          this.tamanhos.push(estoque.tamanho);
        }
      }
    }
  }

  clearQueries() {
    // Gênero
    this.generos = [];

    // Categoria
    this.categorias = [];

    // Tipo
    this.tipos = [];

    // Marca
    this.marcas = [];

    // Cor
    this.cores = [];

    // Tamanho
    this.tamanhos = [];
  }

  pageUp() {
    this.page++;

    if (this.page > this.pages) this.page = this.pages;

  }

  pageDown() {
    this.page--;
    // if (this.page > 1) {
    //   this.page--;
    // }
  }

  // findProdutosPromise() {
  //   return new Promise(resolve => {
  //     this.findProdutos();
  //     resolve(this.produtos);
  //   });
  // }

  // replaceValuesProdutosPromise() {
  //   this.findProdutosPromise().then(produtos => {
  //     this.replaceValuesProdutos();
  //     console.log(this.produtos);
  //   });
  //   console.log('I will not wait until promise is resolved');
  // }

  // replaceValuesProdutos() {
  //   this.produtos.map(produto => {
  //     produto.genero.descricao = produto.genero.descricao
  //       .toLowerCase()
  //       .normalize('NFD').replace(/[\u0300-\u036f]/g, '');

  //     produto.categoria.descricao = produto.categoria.descricao
  //       .toLowerCase()
  //       .normalize('NFD').replace(/[\u0300-\u036f]/g, '');

  //     produto.tipo.descricao = produto.tipo.descricao
  //       .toLowerCase()
  //       .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
  //   });

  //   console.log(this.produtos);
  // }



  // config: SwiperOptions = {
  //   slidesPerView: 3,
  //   spaceBetween: 50,
  //   navigation: true,
  //   pagination: { clickable: true },
  //   scrollbar: { draggable: true },
  // };
  // onSwiper(swiper: any) {
  //   console.log(swiper);
  // }
  // onSlideChange() {
  //   console.log('slide change');
  // }

}
