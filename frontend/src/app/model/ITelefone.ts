import { IUsuarioIdInput } from "./IUsuario";

export interface ITelefoneInput {
    numero: string;
    usuario: IUsuarioIdInput
}

export interface ITelefoneModel {
    numero: string;
}