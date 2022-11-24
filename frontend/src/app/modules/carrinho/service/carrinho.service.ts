import { Injectable } from '@angular/core';
import { IItemPedidoInput } from 'src/app/model/IItemPedido';
import { IProdutoCarrinho } from '../../../model/IProduto';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {
  produtosCarrinho: IProdutoCarrinho[] = [];
  itensPedido: IItemPedidoInput[] = [];

  constructor() { }

  addItemPedido(itemPedidoInput: IItemPedidoInput): void {
    // Get Session
    if (localStorage.getItem('itensPedido')) {
      this.itensPedido = JSON.parse(localStorage.getItem('itensPedido')!);

      let index = -1;

      this.itensPedido.forEach((itemPedido, i) => {
        if (itemPedido.produto.id == itemPedidoInput.produto.id
          && itemPedido.tamanho.id == itemPedidoInput.tamanho.id
          && itemPedido.cor.id == itemPedidoInput.cor.id) {
          index = i;
        }
      });

      if (index != -1) {
        this.itensPedido[index].quantidade += itemPedidoInput.quantidade;
      } else {
        this.itensPedido.push(itemPedidoInput);
      }
    } else {
      this.itensPedido.push(itemPedidoInput);
    }

    // Set Session
    localStorage.setItem('itensPedido', JSON.stringify(this.itensPedido));
  }

  addProdutoCarrinho(produto: IProdutoCarrinho) {
    // Get Session
    if (localStorage.getItem('carrinho')) {
      this.produtosCarrinho = JSON.parse(localStorage.getItem('carrinho')!);
    }

    // Add Cart
    if (this.produtosCarrinho.find(
      (produtoCarrinho) => {
        produtoCarrinho.id == produto.id
          && produtoCarrinho.tamanhoSelecionado == produto.tamanhoSelecionado
          && produtoCarrinho.corSelecionado == produto.corSelecionado
      })) {

      this.produtosCarrinho.map(
        (produtoCarrinho) => {
          produtoCarrinho.id == produto.id
            && produtoCarrinho.tamanhoSelecionado == produto.tamanhoSelecionado
            && produtoCarrinho.corSelecionado == produto.corSelecionado
            ? produtoCarrinho.quantidade += produto.quantidade : '';
        });

    } else {
      this.produtosCarrinho.push(produto);
    }

    // Set Session
    localStorage.setItem('carrinho', JSON.stringify(this.produtosCarrinho));
  }

  getProdutosCarrinho(): IProdutoCarrinho[] {
    // Get Session
    if (localStorage.getItem('carrinho')) {
      this.produtosCarrinho = JSON.parse(localStorage.getItem('carrinho')!);
    }

    return this.produtosCarrinho;
  }

  refreshProdutosCarrinho(produtos: IProdutoCarrinho[]) {
    this.produtosCarrinho = produtos;

    localStorage.setItem('carrinho', JSON.stringify(this.produtosCarrinho));
  }

  deleteProdutoCarrinho(produto: IProdutoCarrinho) {
    if (localStorage.getItem('carrinho')) {
      this.produtosCarrinho = JSON.parse(localStorage.getItem('carrinho')!);
    }

    this.produtosCarrinho = this.produtosCarrinho.filter(
      (produtoCarrinho) => {
        return !(produtoCarrinho.id == produto.id
          && produtoCarrinho.tamanhoSelecionado == produto.tamanhoSelecionado
          && produtoCarrinho.corSelecionado == produto.corSelecionado)
      }
    );

    console.log(this.produtosCarrinho)

    localStorage.setItem('carrinho', JSON.stringify(this.produtosCarrinho));
  }

  getFrete(cep: string) {

  }

}
