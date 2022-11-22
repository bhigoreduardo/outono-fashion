import { IGeneroInput, IGeneroModel, IGeneroResumoModel } from "./IGenero";

export interface IUsuario {
    nome: string;
    sobrenome: string | null;
    email: string;
    cpfCnpj: string;
    rgIe: string;
    dataNascimento: Date | null;
}

export interface IUsuarioInput extends IUsuario {
    genero: IGeneroInput
}

export interface IUsuarioSenhaInput extends IUsuarioInput {
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

export interface IUsuarioLoginModel {
    nome: string;
	sobrenome: string;
	email: string;
	cpfCnpj: string;
	genero: IGeneroResumoModel;
	dataNascimento: Date;
	newsletter: Boolean;
	dataCadastro: Date;
	dataAtualizacao: Date;
}