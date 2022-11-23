import { IUsuarioIdInput } from "./IUsuario";

export interface IEnderecoInput {
    id: IEnderecoIdInput;
    usuario: IUsuarioIdInput;
    enderecoCep: string;
    enderecoBairro: string;
    enderecoLogradouro: string;
    enderecoNumero: string
    enderecoComplemento: string
    enderecoReferencia: string;
    enderecoUf: string;
    enderecoCidade: string;
    enderecoAtivo: Boolean;
}

export interface IEnderecoIdInput {
    enderecoApelido: string;
}

export interface IEnderecoModel {
    id: IEnderecoIdModel;
    enderecoCep: string;
    enderecoBairro: string;
    enderecoLogradouro: string;
    enderecoNumero: string;
    enderecoComplemento: string;
    enderecoReferencia: string;
    enderecoUf: string;
    enderecoCidade: string;
    enderecoAtivo: Boolean;
}

export interface IEnderecoIdModel {
    enderecoApelido: string;
}