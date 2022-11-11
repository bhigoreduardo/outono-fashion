import { IComentario } from "./IComentario";
import { IImagem } from "./IImagem";
import { IProduto } from "./IProduto";

export interface IProdutoDetalhe extends IProduto {
    descricao: string;
    detalhe: string;
    largura: number;
    altura: number;
    comprimento: number;
    peso: number;
    imagens: IImagem[];
    comentarios: IComentario[];
}