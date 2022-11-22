import { ICategoriaModel } from "./ICategoria";
import { IComentarioModel } from "./IComentario";
import { IEstoqueModel } from "./IEstoque";
import { IGenero } from "./IGenero";
import { IImagemModel } from "./IImagem";
import { IMarcaModel } from "./IMarca";
import { ITipoModel } from "./ITipo";

export interface IProdutoIdInput {
    id: number;
}

export interface IProdutoModel {
    id: number;
    nome: string;
    genero: IGenero;
    categoria: ICategoriaModel;
    tipo: ITipoModel;
    estoques: IEstoqueModel[];
    marca: IMarcaModel;
}

export interface IProdutoDetalheModel extends IProdutoModel {
    descricao: string;
    detalhe: string;
    largura: number;
    altura: number;
    comprimento: number;
    peso: number;
    imagens: IImagemModel[];
    comentarios: IComentarioModel[];
}

export interface IProdutoResumoModel {
    id: number;
    nome: string;
}

export interface IProdutoCarrinho extends IProdutoModel {
    largura: number;
    altura: number;
    comprimento: number;
    peso: number;
    quantidade: number;
    tamanhoSelecionado: string;
    corSelecionado: string;
    precoSelecionado: number;
}