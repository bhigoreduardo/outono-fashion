import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { IProduto } from '../model/IProduto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private httpClient: HttpClient) { }

  findProdutos(): Observable<IProduto[]> {
    const url = environment.domain + 'produtos';
    return this.httpClient.get<IProduto[]>(url).pipe(map(res => res));
  }

  // findProdutos() {
  //   const url = environment.domain + 'produtos';
  //   return this.httpClient.get<IProduto[]>(url)
  //     .pipe(tap());
  // }
}
