import { ICorModel } from "./ICor";
import { ITamanhoModel } from "./ITamanho";

export interface IEstoqueModel {
    quantidade: number;
    preco: number;
    oferta: number;
    cor: ICorModel;
    tamanho: ITamanhoModel;
}