import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, retry } from 'rxjs';

import SwiperCore, { SwiperOptions } from 'swiper';
import { ICategoriaModel } from '../../model/ICategoria';
import { ICorModel } from '../../model/ICor';
import { IGenero } from '../../model/IGenero';
import { IMarcaModel } from '../../model/IMarca';
import { IProdutoModel } from '../../model/IProduto';
import { ITamanhoModel } from '../../model/ITamanho';
import { ITipoModel } from '../../model/ITipo';
import { ProdutoService } from './service/produto.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {
  // Produtos
  produtos: IProdutoModel[] = [];
  produtosList: IProdutoModel[] = [];

  // Sidebar Check Vars
  generos: IGenero[] = [];
  categorias: ICategoriaModel[] = [];
  tipos: ITipoModel[] = [];
  cores: ICorModel[] = [];
  tamanhos: ITamanhoModel[] = [];
  marcas: IMarcaModel[] = [];
  minValue: number | undefined;
  maxValue: number | undefined;
  order!: string;

  // Navigation Vars
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
  generoURL: string[] = [];
  categoriaURL: string[] = [];
  tipoURL: string[] = [];
  corURL: string[] = [];
  tamanhoURL: string[] = [];
  marcaURL: string[] = [];

  constructor(
    private produtoService: ProdutoService,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.getProdutos();

    this.initializeVars();

    this.begin = (this.page - 1) * this.size;
    this.end = this.size + this.begin;
  }

  initializeVars() {
    this.sideGenero = false;
    this.sideCategoria = false;
    this.sideTipo = false;
    this.sideTamanho = false;
    this.sideCor = false;
    this.sideMarca = false;
    this.sidePreco = (this.minValue != undefined || this.maxValue != undefined);

    this.sideBars = false;

    this.loader = true;
  }

  getProdutos() {
    const queryParams = this.activatedRoute.snapshot.queryParamMap;

    // Get URL and Replace Queries Values
    this.generoURL = queryParams.getAll('genero').map(value => this.replaceAll(value, '-', ' '));
    this.categoriaURL = queryParams.getAll('categoria').map(value => this.replaceAll(value, '-', ' '));
    this.tipoURL = queryParams.getAll('tipo').map(value => this.replaceAll(value, '-', ' '));
    this.corURL = queryParams.getAll('cor').map(value => this.replaceAll(value, '-', ' '));
    this.tamanhoURL = queryParams.getAll('tamanho').map(value => this.replaceAll(value, '-', ' '));
    this.marcaURL = queryParams.getAll('marca').map(value => this.replaceAll(value, '-', ' '));

    if (queryParams.get('min') != null) {
      this.minValue = Number(queryParams.get('min'));
    }

    if (queryParams.get('max') != null) {
      this.maxValue = Number(queryParams.get('max'));
    }

    if (queryParams.get('order') != null) {
      this.order = queryParams.get('order')!;
    }

    // Get URL Page Values
    this.page = (Number(queryParams.get('page')) == 0) ? 1 : Number(queryParams.get('page'));
    this.size = (Number(queryParams.get('size')) == 0) ? 12 : Number(queryParams.get('size'));

    // Get All Produtos
    this.findProdutos(this.generoURL, this.categoriaURL, this.tipoURL, this.corURL, this.tamanhoURL, this.marcaURL, this.minValue, this.maxValue, this.order);
  }

  findProdutos(genero?: string[], categoria?: string[], tipo?: string[],
    cor?: string[], tamanho?: string[], marca?: string[], precoMin?: number, precoMax?: number, order?: string) {
    this.produtoService.findProdutos(genero, categoria, tipo, cor, tamanho, marca, precoMin, precoMax, order).subscribe(
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
      // G??nero
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
    // G??nero
    this.generoURL = [];

    // Categoria
    this.categoriaURL = [];

    // Tipo
    this.tipoURL = [];

    // Marca
    this.marcaURL = [];

    // Cor
    this.corURL = [];

    // Tamanho
    this.tamanhoURL = [];

    // Pre??o
    this.sidePreco = false;
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

  public replaceAll(str: string, find: string, replace: string) {
    str = str.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    return str.replace(new RegExp(find, 'g'), replace);
  }

}
