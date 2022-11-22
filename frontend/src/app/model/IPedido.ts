import { ICupomIdInput } from './ICupom';
import { IItemPedidoInput, IItemPedidoModel } from './IItemPedido';
import { IPagamentoIdInput, IPagamentoResumoModel } from './IPagamento';
import { IUsuarioIdInput, IUsuarioResumoModel } from './IUsuario';

export interface IPedidoInput {
    taxaEntrega: number;
    pagamento: IPagamentoIdInput;
    enderecoApelido: string;
    usuario: IUsuarioIdInput;
    itensPedido: IItemPedidoInput[];
    cupom: ICupomIdInput;
}

export interface IPedidoModel {
    codigoPedido: string;
    subTotal: number;
    taxaEntrega: number;
    valorTotal: number;
    dataPedido: Date;
	dataPagamento: Date;
	dataCancelamento: Date;
	dataEnvio: Date;
	dataEntrega: Date;
	dataDevolucao: Date;
    status: string;
    pagamento: IPagamentoResumoModel;
    enderecoApelido: string;
    usuario: IUsuarioResumoModel;
    itensPedido: IItemPedidoModel[];
    codigoRastreio: string;
}