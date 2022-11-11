export interface IComentario {
    id: number;
    classificacao: number;
    descricao: string;
    dataComentario: Date;
    usuario: {
        nome: string;
        sobrenome: string;
    }
}