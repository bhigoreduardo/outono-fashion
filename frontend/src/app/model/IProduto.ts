import { ICategoriaModel } from "./ICategoria";
import { IComentarioModel } from "./IComentario";
import { IEstoqueModel } from "./IEstoque";
import { IGeneroModel } from "./IGenero";
import { IImagemModel } from "./IImagem";
import { IMarcaModel } from "./IMarca";
import { ITipoModel } from "./ITipo";

export interface IProdutoIdInput {
    id: number;
}

// Model
export interface IProdutoModel {
    id: number;
    nome: string;
    genero: IGeneroModel;
    categoria: ICategoriaModel;
    tipo: ITipoModel;
    marca: IMarcaModel;
    estoques: IEstoqueModel[];
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

    tamanhoDescricao: string;
    corDescricao: string;
    precoSelecionado: number;
}