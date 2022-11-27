import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { IUsuarioLoginInput, IUsuarioModel, IUsuarioSenhaInput } from 'src/app/model/IUsuario';
import { environment } from 'src/environments/environment';
import { IGenero } from '../../../model/IGenero';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  login(usuarioLoginModel: IUsuarioLoginInput): Observable<IUsuarioModel> {
    let url = environment.domain + 'usuarios/login';

    return this.httpClient
      .post<IUsuarioModel>(url, usuarioLoginModel)
      .pipe(map(res => res));
  }

  islogin(): Boolean {
    return !!localStorage.getItem('usuarioModel');
  }

  loggout(): void {
    localStorage.removeItem('usuarioModel');
  }

  insert(usuarioInput: IUsuarioSenhaInput): Observable<IUsuarioModel> {
    let url = environment.domain + 'usuarios';

    return this.httpClient
      .post<IUsuarioModel>(url, usuarioInput)
      .pipe(map((res) => res));
  }

  findAllGenero(): Observable<IGenero[]> {
    let url = environment.domain + 'generos';

    return this.httpClient
      .get<IGenero[]>(url)
      .pipe(map(res => res));
  }

}