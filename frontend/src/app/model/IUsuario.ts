import { IGeneroInput, IGeneroModel } from "./IGenero";

export interface IUsuario {
    nome: string;
    sobrenome?: string;
    email: string;
    cpfCnpj: string;
    rgIe: string;
    dataNascimento?: Date;
}

export interface IUsuarioInput extends IUsuario {
    genero: IGeneroInput
    newsletter?: Boolean;
}

export interface IUsuarioSenhaInput extends IUsuario {
    genero: IGeneroInput
    senha: string;
}

export interface IUsuarioIdInput {
    id: number;
}

export interface IUsuarioModel extends IUsuario {
    id: number;
    senha: string;
	dataCadastro: Date;
	dataAtualizacao: Date;
    genero: IGeneroModel;
    newsletter: Boolean;
}

export interface IUsuarioPublicoModel {
    nome: string;
    sobrenome: string | null;
}

export interface IUsuarioResumoModel {
    id: number;
    nome: string;
    sobrenome: string | null;
    email: string;
    cpfCnpj: string;
    
}

export interface IUsuarioLoginInput {
    email: string;
    senha: string;
}