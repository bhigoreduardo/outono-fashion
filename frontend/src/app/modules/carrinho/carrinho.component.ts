import { Component, OnInit } from '@angular/core';
import { SwiperOptions } from 'swiper';
import { IProdutoCarrinho } from '../../model/IProduto';
import { CarrinhoService } from './service/carrinho.service';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.scss']
})
export class CarrinhoComponent implements OnInit {
  // Produtos Carrinho Vars
  produtosCarrinho: IProdutoCarrinho[] = [];
  subTotal!: number;
  frete!: number;
  total!: number;
  prestacao: number = 12;

  // Slides Vars
  imageSlide: SwiperOptions = {
    direction: 'horizontal',
    slidesPerView: 1,
    spaceBetween: 0,
    scrollbar: { draggable: true }
  }

  // CEP Vars
  cep!: string;

  constructor(
    private carrinhoService: CarrinhoService
  ) { }

  ngOnInit(): void {
    this.getProdutosCarrinho();
  }

  getProdutosCarrinho() {
    this.produtosCarrinho = this.carrinhoService.getProdutosCarrinho();
    this.getSubTotal();
  }

  getSubTotal() {
    this.subTotal = this.produtosCarrinho.reduce(
      (prev, currProduto) => prev + currProduto.quantidade * currProduto.precoSelecionado, 0
    )
  }

  deleteProdutoCarrinho(produto: IProdutoCarrinho) {
    this.carrinhoService.deleteProdutoCarrinho(produto);
    this.getProdutosCarrinho();
  }

  refreshProdutosCarrinho() {
    this.carrinhoService.refreshProdutosCarrinho(this.produtosCarrinho);
    this.getProdutosCarrinho();
  }

  upper(produtoId: number) {
    this.produtosCarrinho.filter((produtoCarrinho) => {
      produtoCarrinho.estoques.forEach(value => {
        if (value.tamanho.descricao == produtoCarrinho.tamanhoSelecionado && value.cor.descricao == produtoCarrinho.corSelecionado) {
          produtoCarrinho.id == produtoId
            ? produtoCarrinho.quantidade >= value.quantidade
              ? produtoCarrinho.quantidade = value.quantidade : produtoCarrinho.quantidade++
            : ''
        }
      })
    });

    this.refreshProdutosCarrinho();
  }

  down(produtoId: number) {
    this.produtosCarrinho.filter((produtoCarrinho) => {
      produtoCarrinho.id == produtoId
        ? produtoCarrinho.quantidade <= 1
          ? produtoCarrinho.quantidade = 1 : produtoCarrinho.quantidade--
        : '';
    });

    this.refreshProdutosCarrinho();
  }

  getFrete(cep: string) {
    // Implements Get Frete and Prazo API Correios
    this.frete = 10.9;
    this.total = this.subTotal + this.frete;
  }
}
