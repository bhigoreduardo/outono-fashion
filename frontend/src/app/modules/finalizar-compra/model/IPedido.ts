export interface IPedido {
    taxaEntrega: number;
    pagamento: IPagamentoInput,
    usuario: IUsuarioInput,
    itensPedido: IItemPedido[]
}

export interface IPagamentoInput {
    id: number;
}

export interface IUsuarioInput {
    id: number
}

export interface IItemPedido {
    quantidade: number,
    precoUnitario: number,
    produto: IProdutoInput
}

export interface IProdutoInput {
    id: number
}