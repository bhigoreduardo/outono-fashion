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

  findProdutos(genero?: string[], categoria?: string[], tipo?: string[],
    cor?: string[], tamanho?: string[], marca?: string[]): Observable<IProduto[]> {
    let url = environment.domain + 'produtos';

    let operator: string = '?';

    if (genero?.length != 0) {

      genero?.forEach((value, key) => {

        url += operator + 'genero=' + value;

        if (key == 0 && operator == '?') {
          operator = '&';
        }

      });

    }

    if (categoria?.length != 0) {

      categoria?.forEach((value, key) => {

        url += operator + 'categoria=' + value;

        if (key == 0 && operator == '?') {
          operator = '&';
        }

      });

    }

    if (tipo?.length != 0) {

      tipo?.forEach((value, key) => {

        url += operator + 'tipo=' + value;

        if (key == 0 && operator == '?') {
          operator = '&';
        }

      });

    }

    if (cor?.length != 0) {

      cor?.forEach((value, key) => {

        url += operator + 'cor=' + value;

        if (key == 0 && operator == '?') {
          operator = '&';
        }

      });

    }

    if (tamanho?.length != 0) {

      tamanho?.forEach((value, key) => {

        url += operator + 'tamanho=' + value;

        if (key == 0 && operator == '?') {
          operator = '&';
        }

      });

    }

    if (marca?.length != 0) {

      marca?.forEach((value, key) => {

        url += operator + 'marca=' + value;

        if (key == 0 && operator == '?') {
          operator = '&';
        }

      });

    }

    console.log(url);

    return this.httpClient.get<IProduto[]>(url).pipe(map(res => res));
  }

  // findProdutos() {
  //   const url = environment.domain + 'produtos';
  //   return this.httpClient.get<IProduto[]>(url)
  //     .pipe(tap());
  // }
}
