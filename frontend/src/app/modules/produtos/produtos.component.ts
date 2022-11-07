import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

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
  produtos: IProduto[] = [];

  generos: IGenero[] = [];
  categorias: ICategoria[] = [];
  tipos: ITipo[] = [];
  cores: ICor[] = [];
  tamanhos: ITamanho[] = [];
  marcas: IMarca[] = [];

  sideGenero: boolean;
  sideCategoria: boolean;
  sideTipo: boolean;
  sideTamanho: boolean;
  sideCor: boolean;
  sideMarca: boolean;
  sidePreco: boolean;

  sideBars: boolean;

  loaderGif: string = '/assets/icons/loader.gif';
  loader: boolean;

  constructor(private produtoService: ProdutoService) {
    this.sideGenero = false;
    this.sideCategoria = false;
    this.sideTipo = false;
    this.sideTamanho = false;
    this.sideCor = false;
    this.sideMarca = false;
    this.sidePreco = false;

    this.sideBars = false;

    this.loader = true;

    this.findProdutos();
  }

  ngOnInit(): void {
  }

  findProdutos() {
    this.produtoService.findProdutos().subscribe(
      produtos => this.produtos = produtos
    );

    setTimeout(() => {

      this.getCaracteristicaProdutos();

      this.loader = false;
    }, 2000);
  }

  public replaceAll(str: string, find: string, replace: string) {
    return str.replace(new RegExp(find, 'g'), replace);
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

  config: SwiperOptions = {
    slidesPerView: 3,
    spaceBetween: 50,
    navigation: true,
    pagination: { clickable: true },
    scrollbar: { draggable: true },
  };
  onSwiper(swiper: any) {
    console.log(swiper);
  }
  onSlideChange() {
    console.log('slide change');
  }

}
