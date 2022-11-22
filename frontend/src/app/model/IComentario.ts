import { IUsuarioPublicoModel } from "src/app/model/IUsuario";

export interface IComentarioModel {
    classificacao: number;
    descricao: string;
    dataComentario: Date;
    usuario: IUsuarioPublicoModel
}