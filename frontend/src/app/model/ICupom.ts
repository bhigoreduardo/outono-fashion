export interface ICupomIdInput {
    id: number;
}

export interface ICupomModel {
	id: number;
    nome: string;
	descricao: string;
	oferta: number;
	tipoCupom: string;
	ativo: Boolean;
	dataOferta: Date;
	dataEncerramento: Date;
}