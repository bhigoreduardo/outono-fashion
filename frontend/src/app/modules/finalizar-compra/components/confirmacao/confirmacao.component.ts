import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-confirmacao',
  templateUrl: './confirmacao.component.html',
  styleUrls: ['./confirmacao.component.scss']
})
export class ConfirmacaoComponent implements OnInit {

  codigoPedido!: string;

  constructor() { }

  ngOnInit(): void {
    this.codigoPedido = localStorage.getItem('codigoPedido')!;
  }

}
