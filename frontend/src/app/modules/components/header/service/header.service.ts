import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ITipo } from 'src/app/modules/produtos/model/ITipo';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  constructor(private httpClient: HttpClient) { }

  findByGeneroAndCategoria(genero: string, categoria: string): Observable<ITipo[]> {
    let url = environment.domain + 'tipos';

    url += '?genero=' + genero + '&categoria=' + categoria;

    return this.httpClient.get<ITipo[]>(url).pipe(map(res => res));
  }
}
