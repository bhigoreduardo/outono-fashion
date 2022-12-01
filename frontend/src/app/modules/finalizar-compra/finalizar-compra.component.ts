import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CarrinhoService } from '../carrinho/service/carrinho.service';
import { CheckoutService } from './service/checkout.service';
import { IProdutoCarrinho } from '../../model/IProduto';
import { IPedidoInput } from '../../model/IPedido';
import { IEnderecoInput, IEnderecoModel } from 'src/app/model/IEndereco';
import { ContaService } from '../conta/service/conta.service';
import { IUsuarioModel } from 'src/app/model/IUsuario';
import { ICupomModel } from 'src/app/model/ICupom';
import { IItemPedidoInput } from 'src/app/model/IItemPedido';
import { Router } from '@angular/router';

@Component({
  selector: 'app-finalizar-compra',
  templateUrl: './finalizar-compra.component.html',
  styleUrls: ['./finalizar-compra.component.scss']
})
export class FinalizarCompraComponent implements OnInit {

  // Usuario Vars
  usuarioModel!: IUsuarioModel;
  enderecosModel: IEnderecoModel[] = [];
  enderecoApelido!: string;
  addressApiRes!: any;
  addressAdd!: Boolean;
  formInsertEndereco!: FormGroup;

  // Produto Vars
  produtosCarrinho: IProdutoCarrinho[] = [];
  subTotal!: number;
  taxaEntrega: number = 10.9;
  total!: number;
  parcela: number = 12;

  // Pedido Vars
  formaPagamento!: number;
  formCartaoCredito!: FormGroup;
  meses!: number[];
  anos!: number[];
  parcelas!: number[];
  formCupom!: FormGroup;
  cupomModel!: ICupomModel;
  pedido!: IPedidoInput;

  // Message
  message: string | undefined;

  constructor(
    private checkoutService: CheckoutService,
    private contaService: ContaService,
    private carrinhoService: CarrinhoService,
    private formBuilder: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getUsuarioModel();
    this.getProdutosCarrinho();
    this.initializeVars();
    this.createFormInsertEndereco();
  }

  getUsuarioModel(): void {
    this.usuarioModel = JSON.parse(localStorage.getItem('usuarioModel')!);
    this.findEnderecosByUsuarioAsync();
  }

  findEnderecosByUsuario(usuarioId: number) {
    return new Promise(resolve => {
      this.contaService.findEnderecosByUsuario(usuarioId).subscribe(
        res => {
          resolve(res);
        }
      );
    });
  }

  async findEnderecosByUsuarioAsync() {
    this.enderecosModel = <IEnderecoModel[]>await this.findEnderecosByUsuario(this.usuarioModel.id);
  }

  activeEnderecoApelido(enderecoInput: IEnderecoInput) {
    return new Promise(resolve => {
      this.contaService.activeEndereco(enderecoInput).subscribe(
        res => {
          this.findEnderecosByUsuarioAsync();
        }, error => {
          this.message = error.userMessage;
        }
      );
    });
  }

  async activeEnderecoApelidoAsync(enderecoApelido: string) {
    const enderecoModel = this.enderecosModel.filter(endereco => endereco.id.enderecoApelido == enderecoApelido)[0];

    const enderecoInput: IEnderecoInput = {
      id: { enderecoApelido: enderecoModel.id.enderecoApelido },
      usuario: { id: this.usuarioModel.id },
      enderecoCep: enderecoModel.enderecoCep,
      enderecoBairro: enderecoModel.enderecoBairro,
      enderecoLogradouro: enderecoModel.enderecoLogradouro,
      enderecoNumero: enderecoModel.enderecoNumero,
      enderecoComplemento: enderecoModel.enderecoComplemento,
      enderecoReferencia: enderecoModel.enderecoReferencia,
      enderecoUf: enderecoModel.enderecoUf,
      enderecoCidade: enderecoModel.enderecoCidade,
      enderecoAtivo: enderecoModel.enderecoAtivo
    }

    await this.activeEnderecoApelido(enderecoInput);
  }

  getProdutosCarrinho(): void {
    this.produtosCarrinho = this.carrinhoService.getProdutosCarrinho();
    this.getSubTotal();
  }

  getSubTotal(): void {
    this.subTotal = this.produtosCarrinho.reduce(
      (prev, currProduto) => prev + currProduto.quantidade * currProduto.precoSelecionado, 0
    )
    this.total = this.subTotal + this.taxaEntrega;

    if (this.cupomModel != undefined) {
      if (this.cupomModel.tipoCupom == 'PORCENTAGEM') {
        this.total -= this.total * this.cupomModel.oferta / 100;
      } else if (this.cupomModel.tipoCupom == 'FIXO') {
        this.total -= this.cupomModel.oferta;
      }
    }

  }

  initializeVars(): void {
    this.meses = Array(12).fill(1).map((x, i) => i + 1);
    this.anos = Array(10).fill(1).map((x, i) => i + 22);
    this.parcelas = Array(12).fill(1).map((x, i) => i + 1);

    this.formCupom = this.formBuilder.group({
      cupom: new FormControl(null, [Validators.required, Validators.maxLength(30)])
    })
  }

  createFormCartaoCredito(): void {
    this.formCartaoCredito = this.formBuilder.group({
      numero: new FormControl(null, [Validators.required, Validators.minLength(16), Validators.maxLength(16)]),
      nomeImpresso: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(120)]),
      mesValidade: new FormControl('Mês', [Validators.required, Validators.minLength(1), Validators.maxLength(2)]),
      anoValidade: new FormControl('Ano', [Validators.required, Validators.minLength(1), Validators.maxLength(2)]),
      cvv: new FormControl(null, [Validators.required, Validators.minLength(3), Validators.maxLength(4)]),
      parcela: new FormControl('Parcelamento', Validators.required)
    });
  }

  createFormInsertEndereco(): void {
    this.formInsertEndereco = this.formBuilder.group({
      enderecoCep: new FormControl(null, [Validators.required, Validators.maxLength(8)]),
      enderecoBairro: new FormControl(),
      enderecoLogradouro: new FormControl(null, [Validators.required, Validators.maxLength(60)]),
      enderecoNumero: new FormControl(null, [Validators.required, Validators.maxLength(6)]),
      enderecoComplemento: new FormControl(null, Validators.maxLength(60)),
      enderecoReferencia: new FormControl(null, [Validators.maxLength(60)]),
      enderecoUf: new FormControl(null, [Validators.required, Validators.maxLength(2)]),
      enderecoCidade: new FormControl(null, [Validators.required, Validators.maxLength(120)]),
      enderecoApelido: new FormControl(null, [Validators.required, Validators.maxLength(60)]),
      enderecoAtivo: new FormControl(null)
    });
  }

  patchFormInsertEndereco(addressApiRes: any) {
    this.formInsertEndereco.patchValue({
      enderecoBairro: addressApiRes.bairro,
      enderecoLogradouro: addressApiRes.logradouro,
      enderecoUf: addressApiRes.uf,
      enderecoCidade: addressApiRes.localidade
    });
  }

  searchCep(values: any) {
    return new Promise(resolve => {
      this.contaService.searchCep(values.enderecoCep).subscribe(
        res => {
          this.addressApiRes = res;
          this.patchFormInsertEndereco(this.addressApiRes);
        }, error => {
          this.message = error.error.message;
        }
      )
    });
  }

  async searchCepAsync(values: any) {
    await this.searchCep(values);
  }

  insertEndereco(enderecoInput: IEnderecoInput) {
    return new Promise(resolve => {
      this.contaService.insertEndereco(enderecoInput).subscribe(
        res => {
          this.findEnderecosByUsuarioAsync();
          this.formInsertEndereco.reset();
          this.addressAdd = false;

          this.message = 'Endereço cadastrado com sucesso!';
        }, error => {
          this.message = error.userMessage;
        }
      );
    });
  }

  async insertEnderecoAsync(values: any) {
    const enderecoInput: IEnderecoInput = {
      id: { enderecoApelido: values.enderecoApelido },
      usuario: { id: this.usuarioModel.id },
      enderecoCep: values.enderecoCep,
      enderecoBairro: values.enderecoBairro,
      enderecoLogradouro: values.enderecoLogradouro,
      enderecoNumero: values.enderecoNumero,
      enderecoComplemento: values.enderecoComplemento,
      enderecoReferencia: values.enderecoReferencia,
      enderecoUf: values.enderecoUf,
      enderecoCidade: values.enderecoCidade,
      enderecoAtivo: values.enderecoAtivo
    }

    await this.insertEndereco(enderecoInput);
  }

  findCupomByNome(cupomNome: string, usuarioId: number) {
    return new Promise(resolve => {
      this.checkoutService.findCupomByNome(cupomNome, usuarioId).subscribe(
        res => {
          resolve(res);
        }, error => {
          this.message = error.error.userMessage;
        }
      );
    });
  }

  async findCupomByNomeAsync(values: any) {
    this.cupomModel = <ICupomModel>await this.findCupomByNome(values.cupom, this.usuarioModel.id);
    this.message = 'Cupom aplicado';
    this.getSubTotal();
  }

  clearMessage(value: any): void {
    this.message = undefined;
  }

  finalizarCompra() {
    let itensPedidoInput: IItemPedidoInput[] = [];

    this.produtosCarrinho.forEach((produto, index) => {
      itensPedidoInput.push(
        {
          produto: { id: produto.id },
          tamanho: { id: produto.tamanhoId },
          cor: { id: produto.corId },
          quantidade: produto.quantidade
        })
    });

    this.enderecosModel.forEach((endereco, index) => {
      if (endereco.enderecoAtivo) {
        this.enderecoApelido = endereco.id.enderecoApelido;
      }
    });

    let pedidoInput: IPedidoInput = {
      taxaEntrega: this.taxaEntrega,
      pagamento: { id: this.formaPagamento },
      enderecoApelido: this.enderecoApelido,
      usuario: { id: this.usuarioModel.id },
      itensPedido: itensPedidoInput,
    }

    if (this.cupomModel != undefined) {
      pedidoInput = {
        ...pedidoInput,
        cupom: { id: this.cupomModel.id }
      }
    }

    this.checkoutService.insertPedido(pedidoInput).subscribe(
      res => {
        localStorage.setItem('codigoPedido', res.codigoPedido);
        this.carrinhoService.clearProdutosCarrinho();

        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['finalizar-compra/confirmacao']);
        });
      }, error => {
        this.message = error.error.userMessage;
      }
    );
  }

}
