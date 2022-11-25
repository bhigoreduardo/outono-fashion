import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ICupomModel } from 'src/app/model/ICupom';
import { IPedidoInput, IPedidoModel } from 'src/app/model/IPedido';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(
    private httpClient: HttpClient
  ) { }

  findCupomByNome(cupomNome: string, usuarioId: number): Observable<ICupomModel> {
    let url = environment.domain + 'cupons/' + cupomNome + '/' + usuarioId;

    return this.httpClient
      .get<ICupomModel>(url)
      .pipe(map(res => res));
  }

  insertPedido(pedidoInput: IPedidoInput): Observable<IPedidoModel> {
    let url = environment.domain + 'pedidos';

    return this.httpClient
      .post<IPedidoModel>("http://127.0.0.1:8080/pedidos", pedidoInput, {
        headers: new HttpHeaders().set('Content-Type', 'application/json')
      })
      .pipe(map(res => res));
  }

}
