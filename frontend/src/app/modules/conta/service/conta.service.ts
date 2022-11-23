import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ISenhaInput } from 'src/app/model/ISenha';
import { ITelefoneInput, ITelefoneModel } from 'src/app/model/ITelefone';
import { IUserMessage } from 'src/app/model/IUserMessage';
import { IUsuarioInput, IUsuarioModel } from 'src/app/model/IUsuario';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(private httpClient: HttpClient) { }

  update(usuarioInput: IUsuarioInput, usuarioId: number): Observable<IUsuarioModel> {
    let url = environment.domain + 'usuarios/' + usuarioId;

    return this.httpClient
      .put<IUsuarioModel>(url, usuarioInput)
      .pipe(map(res => res));
  }

  setSenha(senhaInput: ISenhaInput, usuarioId: number): Observable<IUserMessage> {
    let url = environment.domain + 'usuarios/' + usuarioId + '/senha';

    return this.httpClient
      .put<IUserMessage>(url, senhaInput)
      .pipe(map(res => res));
  }

  insertTelefone(telefoneInput: ITelefoneInput): Observable<ITelefoneModel> {
    let url = environment.domain + 'telefones';

    return this.httpClient
      .post<ITelefoneModel>(url, telefoneInput)
      .pipe(map(res => res));
  }

  findTelefoneByUsuario(usuarioId: number): Observable<ITelefoneModel[]> {
    let url = environment.domain + 'telefones/' + usuarioId;

    return this.httpClient
      .get<ITelefoneModel[]>(url)
      .pipe(map(res => res));
  }

  deleteTelefoneByNumero(numero: string): Observable<IUserMessage> {
    let url = environment.domain + 'telefones/' + numero;

    return this.httpClient
      .delete<IUserMessage>(url)
      .pipe(map(res => res));
  }
}
