import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-radiolocation',
  templateUrl: './radiolocation.component.html',
  styleUrls: ['./radiolocation.component.scss']
})
export class RadiolocationComponent {

  @Input() enderecoApelido!: string;
  @Input() enderecoLogradouro!: string;
  @Input() enderecoNumero!: string;
  @Input() enderecoBairro!: string;
  @Input() enderecoComplemento!: string;
  @Input() enderecoReferencia!: string;
  @Input() enderecoUf!: string;
  @Input() enderecoCidade!: string;
  @Input() enderecoCep!: string;
  @Input() enderecoAtivo!: Boolean;

  @Output() active = new EventEmitter();

}
