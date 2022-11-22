import { Injectable } from '@angular/core';
import { IProdutoCarrinho } from '../../../model/IProduto';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {
  produtosCarrinho: IProdutoCarrinho[] = [];

  constructor() { }

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
