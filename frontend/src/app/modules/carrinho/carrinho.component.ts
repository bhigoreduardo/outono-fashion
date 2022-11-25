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

  // Produto Vars
  produtosCarrinho: IProdutoCarrinho[] = [];

  // Carrinho Props
  quantidadeProdutos!: number;
  subTotal!: number;

  cep!: string;
  taxaEntrega!: number;
  total!: number;
  parcela: number = 12;

  // Slides Vars
  // imageSlide: SwiperOptions = {
  //   direction: 'horizontal',
  //   slidesPerView: 1,
  //   spaceBetween: 0,
  //   scrollbar: { draggable: true }
  // }

  constructor(
    private carrinhoService: CarrinhoService
  ) { }

  ngOnInit(): void {
    this.getProdutosCarrinho();
  }

  getQuantidadeProdutos(): number {
    return this.carrinhoService.getQuantidadeProdutos();
  }

  getProdutosCarrinho() {
    this.produtosCarrinho = this.carrinhoService.getProdutosCarrinho();
    this.getSubTotal();
  }

  getSubTotal() {
    this.subTotal = this.produtosCarrinho.reduce(
      (prev, currProduto) => prev + currProduto.quantidade * currProduto.precoSelecionado, 0
    )
    this.total = this.subTotal + this.taxaEntrega;
  }

  refreshProdutosCarrinho() {
    this.carrinhoService.refreshProdutosCarrinho(this.produtosCarrinho);
    this.getProdutosCarrinho();
  }

  upper(produtoId: number) {
    this.produtosCarrinho.filter((produtoCarrinho) => {
      produtoCarrinho.estoques.forEach(value => {
        if (value.tamanho.descricao == produtoCarrinho.tamanhoDescricao && value.cor.descricao == produtoCarrinho.corDescricao) {
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

  deleteProdutoCarrinho(produto: IProdutoCarrinho) {
    this.carrinhoService.deleteProdutoCarrinho(produto);
    this.getProdutosCarrinho();
  }

  setTaxaEntrega(cep: string) {
    // Implements Get Frete and Prazo API Correios
    this.taxaEntrega = 10.9;
    this.getSubTotal();
  }

}
