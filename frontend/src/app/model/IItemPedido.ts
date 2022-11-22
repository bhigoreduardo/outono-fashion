import { ICorIdInput, ICorResumoModel } from "./ICor";
import { IProdutoIdInput, IProdutoResumoModel } from "./IProduto";
import { ITamanhoIdInput, ITamanhoModel } from "./ITamanho";

export interface IItemPedidoInput {
    produto: IProdutoIdInput,
    tamanho: ITamanhoIdInput,
    cor: ICorIdInput,
    quantidade: number,
    observacao: string
}

export interface IItemPedidoModel {
    produto: IProdutoResumoModel;
    tamanho: ITamanhoModel;
    cor: ICorResumoModel;
    quantidade: number;
    precoUnitario: number;
    precoTotal: number;
    observacao: string;
}