import { ICor } from "./ICor";
import { ITamanho } from "./ITamanho";

export interface IEstoque {
    quantidade: number;
    preco: number;
    oferta: number;
    cor: ICor;
    tamanho: ITamanho;
}