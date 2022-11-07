import { ICategoria } from "./ICategoria";
import { IEstoque } from "./IEstoque";
import { IGenero } from "./IGenero";
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