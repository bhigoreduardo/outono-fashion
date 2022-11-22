import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ITipoModel } from 'src/app/model/ITipo';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  constructor(private httpClient: HttpClient) { }

  findByGeneroAndCategoria(genero: string, categoria: string): Observable<ITipoModel[]> {
    let url = environment.domain + 'produtos/tipos';

    url += '?genero=' + genero + '&categoria=' + categoria;

    return this.httpClient.get<ITipoModel[]>(url).pipe(map(res => res));
  }
}
