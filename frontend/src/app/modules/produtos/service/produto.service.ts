import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { IProdutoModel, IProdutoDetalheModel } from '../../../model/IProduto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private httpClient: HttpClient) { }

  findProdutos(genero?: string[], categoria?: string[], tipo?: string[],
    cor?: string[], tamanho?: string[], marca?: string[], precoMin?: number, precoMax?: number, order?: string): Observable<IProdutoModel[]> {
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

    if (precoMin != undefined && precoMax != undefined) {
      url += operator + 'precoMin=' + precoMin + '&precoMax=' + precoMax;

      if (operator == '?') {
        operator = '&';
      }
    }

    if (order != undefined) {
      url += operator + 'order=' + order;
    }

    return this.httpClient.get<IProdutoModel[]>(url).pipe(map(res => res));
  }

  findProdutoByNomeAndId(nome: string, produtoId: number): Observable<IProdutoDetalheModel> {
    let url = environment.domain + 'produtos/' + nome + '/' + produtoId;

    return this.httpClient.get<IProdutoDetalheModel>(url).pipe(map(res => res));
  }
}
