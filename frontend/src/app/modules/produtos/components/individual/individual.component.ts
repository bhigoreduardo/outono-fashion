import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SwiperOptions } from 'swiper';
import { ICor } from '../../model/ICor';
import { IProdutoDetalhe } from '../../model/IProdutoDetalhe';
import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-individual',
  templateUrl: './individual.component.html',
  styleUrls: ['./individual.component.scss']
})
export class IndividualComponent implements OnInit {
  // Produto Vars
  produto!: IProdutoDetalhe;
  colors: ICor[] = [];
  quantity!: number;

  // Produto Values Checked
  tamanhoSelected!: string;
  corSelected!: string;

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
    private router: Router
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
          console.log(data.comentarios.length)
        }
      )
    })
  }

  async getProdutoDescription() {
    // Get Produto
    this.produto = <IProdutoDetalhe>await this.getProduto();

    // Root Image
    this.pathImage += this.replaceAll(this.produto.genero.descricao, ' ', '-') + '/';
    this.pathImage += this.replaceAll(this.produto.categoria.descricao, ' ', '-') + '/';
    this.pathImage += this.replaceAll(this.produto.tipo.descricao, ' ', '-') + '/';

    // Name Image
    this.changeImageActive(this.produto.imagens[0].nome, this.produto.imagens[0].tipo);

    // Colors Produto
    this.produto.estoques.forEach((value, index) => {

      if (index == 0) {
        this.colors.push(value.cor);
      } else if (index < this.produto.estoques.length) {
        if (!(this.produto.estoques[index].cor.descricao == this.produto.estoques[index++].cor.descricao)) {
          this.colors.push(value.cor);
        }
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

  //Methods Choice Props Produto
  getQuantity() {
    if (this.tamanhoSelected != undefined && this.corSelected != undefined) {
      this.produto.estoques.forEach(estoque => {
        if (estoque.tamanho.descricao == this.tamanhoSelected && estoque.cor.descricao == this.corSelected) {
          this.quantity = estoque.quantidade;
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

  replaceAll(str: string, find: string, replace: string) {
    str = str.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    return str.replace(new RegExp(find, 'g'), replace);
  }

}
