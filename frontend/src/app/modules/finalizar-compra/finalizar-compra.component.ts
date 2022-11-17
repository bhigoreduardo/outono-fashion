import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CarrinhoService } from '../carrinho/service/carrinho.service';
import { IProdutoCarrinho } from '../produtos/model/IProduto';
import { IPedido } from './model/IPedido';

@Component({
  selector: 'app-finalizar-compra',
  templateUrl: './finalizar-compra.component.html',
  styleUrls: ['./finalizar-compra.component.scss']
})
export class FinalizarCompraComponent implements OnInit {
  formCreditCard: FormGroup = this.formBuilder.group({
    cardNumber: ['', [Validators.required, Validators.minLength(16)]],
    cardName: ['', [Validators.required, Validators.minLength(10)]],
    cardMonth: ['', [Validators.required]],
    cardYear: ['', [Validators.required]],
    cardCod: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(4)]],
    cardInstallment: ['', Validators.required]
  });

  // Payment Vars
  formaPagamento!: number;

  // Produto Vars
  produtosCarrinho: IProdutoCarrinho[] = [];
  subTotal!: number;
  frete: number = 10.9;
  total!: number;
  prestacao: number = 12;

  // Pedido Vars
  pedido!: IPedido;

  constructor(
    private carrinhoService: CarrinhoService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getProdutosCarrinho();
  }

  getProdutosCarrinho() {
    this.produtosCarrinho = this.carrinhoService.getProdutosCarrinho();
    this.getSubTotal();
    console.log(this.produtosCarrinho)
  }

  getSubTotal() {
    this.subTotal = this.produtosCarrinho.reduce(
      (prev, currProduto) => prev + currProduto.quantidade * currProduto.precoSelecionado, 0
    )
    this.total = this.subTotal + this.frete;
  }

  finalizarCompra() {
    this.pedido = {
      taxaEntrega: this.frete,
      pagamento: { id: this.formaPagamento },
      usuario: { id: 1 },
      itensPedido: []
    }
  }

}
