import { ICategoria } from "./ICategoria";
import { IComentario } from "./IComentario";
import { IEstoque } from "./IEstoque";
import { IGenero } from "./IGenero";
import { IImagem } from "./IImagem";
import { IMarca } from "./IMarca";
import { ITipo } from "./ITipo";

export interface IProduto {
    id: number;
    nome: string;
    genero: IGenero;
    categoria: ICategoria;
    tipo: ITipo;
    estoques: IEstoque[];
    marca: IMarca
}

export interface IProdutoDetalhe extends IProduto {
    largura: number;
    altura: number;
    comprimento: number;
    peso: number;
    descricao: string;
    detalhe: string;
    imagens: IImagem[];
    comentarios: IComentario[];
}

export interface IProdutoCarrinho extends IProduto {
    largura: number;
    altura: number;
    comprimento: number;
    peso: number;
    quantidade: number;
    tamanhoSelecionado: string;
    corSelecionado: string;
    precoSelecionado: number
}